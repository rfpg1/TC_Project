
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.sosy_lab.common.configuration.InvalidConfigurationException;
import org.sosy_lab.java_smt.api.SolverException;

import exception.BooleanException;
import exception.CompilerException;
import exception.FunctionException;
import exception.InvalidReturnException;
import exception.RefinementException;
import exception.TypeException;
import exception.VariableException;
import utils.Constant;
import utils.Context;
import utils.SatSolver;
import utils.Triple;

public class Verifier {

	private SatSolver solver;

	public Verifier() throws CompilerException {
		try {
			solver = new SatSolver();
		} catch (InvalidConfigurationException e) {
			throw new RefinementException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public void verify(Context context, List<Map<String, Object>> statements) throws CompilerException {
		if(statements != null) { 
			for(Map<String, Object> statement : statements) {
				for(String key : statement.keySet()) {
					if(key.equals(Constant.DECLARATION)) {
						List<Map<String, Object>> declarations = (List<Map<String, Object>>) statement.get(Constant.DECLARATION);
						for(Map<String, Object> decl : declarations) {
							String name = (String) decl.get(Constant.NAME);
							context.insertFunction(name);
						}
					} else if(key.equals(Constant.VARIABLE)) {
						checkVariable(statement, key, context);
					} else if(key.equals(Constant.FUNCTION)) {
						List<Map<String, Object>> fs = (List<Map<String, Object>>) statement.get(key);
						for(Map<String, Object> f : fs) {
							context.enterScope();
							checkFunction(f, key, context);
							List<Map<String, Object>> returnList = (List<Map<String, Object>>)f.get(Constant.RETURN_STATEMENT);
							if(returnList != null) {
								checkReturnStatement(context, returnList.get(0));
							}
							context.setCurrentFunction(null);
							context.exitScope();
						}
					} else if(key.equals(Constant.IF_STATEMENT) || key.equals(Constant.WHILE_STATEMENT)) {
						if(context.getCurrentFunction() == null) {
							throw new FunctionException(key + " can only occur inside a function");
						}
						Map<String, Object> ifStatement = ((List<Map<String, Object>>) statement.get(key)).get(0);
						checkBooleanExpression(ifStatement, context, null);
						List<Map<String, Object>> states = (List<Map<String, Object>>) ifStatement.get(Constant.STATEMENT);
						verify(context, states);
						List<Map<String, Object>> returnList = (List<Map<String, Object>>)ifStatement.get(Constant.RETURN_STATEMENT);
						if(returnList != null) {
							checkReturnStatement(context, returnList.get(0));
						}
					} else if(key.equals(Constant.ELSE_STATEMENT)) {
						Map<String, Object> elseStatement = ((List<Map<String, Object>>) statement.get(key)).get(0);
						List<Map<String, Object>> states = (List<Map<String, Object>>) elseStatement.get(Constant.STATEMENT);
						verify(context, states);
					} else if(key.equals(Constant.FUNCTION_CALL)) {
						if(context.getCurrentFunction() == null) {
							throw new FunctionException("Function calls can only occur inside a function");
						}
						List<Map<String, Object>> funcCalls = (List<Map<String, Object>>) statement.get(key);
						for(Map<String, Object> call : funcCalls) {
							List<Map<String, Object>> function = new ArrayList<>();
							Map<String, Object> f = new LinkedHashMap<>();
							List<Map<String, Object>> f1 = new ArrayList<>();
							f1.add(call);
							f.put(Constant.FUNCTION, f1);
							function.add(f);
							checkParams(context, function, null);
						}
					}
				}
			}
		}

		for(Map<String, Object> statement : statements) {
			for(String key : statement.keySet()) {
				if(key.equals(Constant.FUNCTION_CALL)) {
					if(context.getCurrentFunction() == null) {
						throw new FunctionException("Function calls can only occur inside a function");
					}
					List<Map<String, Object>> funcCalls = (List<Map<String, Object>>) statement.get(key);
					for(Map<String, Object> call : funcCalls) {
						List<Map<String, Object>> function = new ArrayList<>();
						Map<String, Object> f = new LinkedHashMap<>();
						List<Map<String, Object>> f1 = new ArrayList<>();
						f1.add(call);
						f.put(Constant.FUNCTION, f1);
						function.add(f);
						checkParams(context, function, null);
					}
				}
			}
		}


	}

	@SuppressWarnings("unchecked")
	private void checkValidArray(Map<String, Object> array, Context context) throws CompilerException {
		String varName = (String) array.get(Constant.VARIABLE);
		if(!context.hasVar(varName)) {
			throw new VariableException("Variable: " + varName + " doesn't exist");
		}
		boolean isArray = ((Triple<Object, Boolean, Boolean>)context.getType(varName)).getSecond();
		boolean isMatrix = ((Triple<Object, Boolean, Boolean>)context.getType(varName)).getThird();

		if(!isArray && !isMatrix) {
			throw new TypeException("Variable: " + varName + " isn't an array");
		}
		String type = (String) ((Triple<List<String>, Map<String, Object>, Object>) context.getType(varName)).getFirst().get(0);
		array.put(Constant.ARRAY_TYPE, type);
		List<Map<String, Object>> position = (List<Map<String, Object>>) array.get(Constant.VALUE);
		checkPosition(position, context, varName);
	}

	@SuppressWarnings("unchecked")
	private void checkPosition(List<Map<String, Object>> position, Context context, String varName) throws CompilerException {
		String valueType = (String) position.get(0).get(Constant.VALUE_TYPE);
		String positionValue = (String) position.get(0).get(Constant.POSITION_VALUE);
		if(valueType.equals(Constant.VARIABLE)) {
			Triple<List<Object>, Boolean, Object> pair = (Triple<List<Object>, Boolean, Object>) context.getType(positionValue);
			if(pair == null) {
				throw new VariableException("Variable: " + positionValue + " doesn't exist");
			}
			String varType = (String)((List<Object>) pair.getFirst()).get(0);
			if(!varType.equals(Constant.INT)) {
				throw new TypeException("When acessing array: " + varName + " position value: " + positionValue + " must be an integer but it is: " + varType);
			}
		} else if(valueType.equals(Constant.FUNCTION_CALL)) {
			List<Map<String, Object>> func = (List<Map<String, Object>>) position.get(0).get(Constant.VALUE_FUNC);
			checkParams(context, func, varName);
			checkValidReturn(context, func, Constant.INT);
		}
		if(position.get(0).size() > 3) {
			List<Map<String, Object>> value = (List<Map<String, Object>>) ((List<Map<String, Object>>) position.get(0).get(Constant.VALUE)).get(0).get(Constant.VALUE);
			checkPosition(value, context, varName);
		}
	}

	@SuppressWarnings("unchecked")
	private void checkValidReturn(Context context, List<Map<String, Object>> list, String type) throws FunctionException {
		List<Map<String, Object>> function = (List<Map<String, Object>>) list.get(0).get(Constant.FUNCTION);
		String funcName = (String) function.get(0).get(Constant.VARIABLE);

		Triple<List<Map<String, Object>>, Map<String, Object>, Object> func = (Triple<List<Map<String, Object>>, Map<String, Object>, Object>)context.getFunction(funcName);
		Map<String, Object> returnMap = func.getSecond();
		String returnType = (String) returnMap.get(Constant.TYPE);
		if(!returnType.equals(type)) {
			throw new FunctionException("Function: "+ funcName + " has to return a integer");
		}
		boolean isArray = (Boolean) returnMap.get(Constant.IS_ARRAY);
		if(isArray) {
			throw new FunctionException("Function: " + funcName + " can't return an array");
		}
	}

	@SuppressWarnings("unchecked")
	private void checkReturnStatement(Context context, Map<String, Object> map) throws CompilerException {
		String currentFunction = context.getCurrentFunction();
		if(currentFunction == null) {
			throw new InvalidReturnException("Trying to return something without declaring a function");
		}
		Triple<List<Map<String, Object>>, Map<String, Object>, Object> pair = (Triple<List<Map<String, Object>>, Map<String, Object>, Object>) context.getFunction(currentFunction);
		boolean isArray = (boolean) pair.getSecond().get(Constant.IS_ARRAY);
		String valueType = getValueType(map, null, context, isArray);
		String actualType = (String) pair.getSecond().get(Constant.TYPE);
		if(valueType.equals(Constant.FUNCTION_CALL)) {
			List<Map<String, Object>> params = (List<Map<String, Object>>) map.get(Constant.VALUE_FUNC);
			String funcName = (String) ((List<Map<String, Object>>)params.get(0).get(Constant.FUNCTION)).get(0).get(Constant.VARIABLE);
			checkParams(context, params, null);
			Map<String, Object> returnStatement = ((Triple<List<Map<String, Object>>, Map<String, Object>, Object>)context.getFunction(funcName)).getSecond();
			valueType = (String) returnStatement.get(Constant.TYPE);
		}
		checkType(actualType, valueType, map, context);
		if(!map.isEmpty()) {
			checkArray(isArray, map, context);
		}
	}

	@SuppressWarnings("unchecked")
	private void checkBooleanExpression(Map<String, Object> ifStatement, Context context, String operator) throws CompilerException {
		String valueType = (String) ifStatement.get(Constant.VALUE_TYPE);
		String op = (String) ifStatement.get(Constant.OPERATOR);
		if(valueType.equals(Constant.VARIABLE)) {
			String varName = (String) ifStatement.get(Constant.BOOLEAN_VALUE);
			if(!context.hasVarInCurrentScope(varName)) {
				throw new VariableException("Variable: " + varName + " isn't defined in this scope");
			}
			Triple<List<Object>, Object, Object> pair = (Triple<List<Object>, Object, Object>) context.getType(varName);
			if(pair == null) {
				throw new VariableException("Variable: " + varName + " doesn't exist");
			} else {
				String vType = (String) ((List<Object>) pair.getFirst()).get(0);
				ifStatement.put(Constant.VALUE_ACTUAL_TYPE, vType);
				String bType = Constant.getOperatorType(op);
				if(vType.equals(Constant.DOUBLE) || vType.equals(Constant.INT)) {
					if(operator == null) {
						if(op == null) {
							throw new BooleanException("Condition isn't valid");
						}
						if(!bType.equals(Constant.MATH)) {
							throw new BooleanException("Condition isn't valid");
						} else {
							operator = bType;
						}
					} else {
						if(bType != null && bType.equals(Constant.MATH)) {
							throw new BooleanException("Condition isn't valid");
						} else {
							if(operator.equals(Constant.BOOLEAN)) {
								throw new BooleanException("Condition isn't valid");
							}
							operator = null;
						}
					}
				} else if(vType.equals(Constant.BOOLEAN)) {
					if(operator == null) {
						if(op != null && (op.equals("==") || op.equals("!="))) {
							operator = Constant.BOOLEAN;
						} else if(op != null && bType.equals(Constant.MATH)) {
							throw new BooleanException("Condition isn't valid");
						}
					} else if(!operator.equals(Constant.BOOLEAN)){
						throw new BooleanException("Condition isn't valid");
					}
				} else {
					throw new TypeException("Type: " + vType + " isn't valid in boolean expressions");
				}
			}
		} else if(valueType.equals(Constant.INT) || valueType.equals(Constant.DOUBLE)) {
			if(op == null) {
				if(operator == null) {
					throw new BooleanException("Condition isn't valid");
				} else {
					if(!operator.equals(Constant.MATH)) {
						throw new BooleanException("Condition isn't valid");
					} else {
						operator = null;
					}
				}
			} else {
				String bType = Constant.getOperatorType(op);
				if(operator == null) {
					if(!bType.equals(Constant.MATH)) {
						throw new BooleanException("Condition isn't valid");
					} else {
						operator = Constant.getOperatorType(op);
					}
				} else {
					if(bType.equals(Constant.MATH)) {
						throw new BooleanException("Condition isn't valid");
					} else {
						operator = null;
					}
				}
			}			
		} else if(valueType.equals(Constant.EXPR)) {
			checkExpr(context, (String)ifStatement.get(Constant.BOOLEAN_VALUE));
			if(op == null) {
				if(operator == null) {
					throw new BooleanException("Condition isn't valid");
				} else {
					operator = null;
				}
			} else {
				String bType = Constant.getOperatorType(op);
				if(operator == null) {
					if(!bType.equals(Constant.MATH)) {
						throw new BooleanException("Condition isn't valid");
					} else {
						operator = bType;
					}
				} else {
					if(bType.equals(Constant.MATH)) {
						throw new BooleanException("Condition isn't valid");
					} else {
						operator = null;
					}
				}

			}

		} else if(valueType.equals(Constant.BOOLEAN)) {
			if(operator != null) {
				if(!operator.equals(Constant.BOOLEAN)) {
					throw new BooleanException("Condition isn't valid");	
				}
				if(op != null) {
					String bType = Constant.getOperatorType(op);
					if(!bType.equals(Constant.BOOLEAN)) {
						throw new BooleanException("Condition isn't valid");	
					}
				}
			} else {
				String bType = Constant.getOperatorType(op);
				if(op != null && (op.equals("==") || op.equals("!="))) {
					operator = Constant.BOOLEAN;
				} else if(op != null && bType.equals(Constant.MATH)) {
					throw new BooleanException("Condition isn't valid");
				} else {
					operator = null;
				}
			}
		} else if(valueType.equals(Constant.FUNCTION_CALL)) {

			List<Map<String, Object>> params = (List<Map<String, Object>>) ifStatement.get(Constant.VALUE_FUNC);
			String funcName = (String) ((List<Map<String, Object>>)params.get(0).get(Constant.FUNCTION)).get(0).get(Constant.VARIABLE);
			checkParams(context, params, null);
			Map<String, Object> returnStatement = ((Triple<List<Map<String, Object>>, Map<String, Object>, Object>)context.getFunction(funcName)).getSecond();
			boolean isArray = (boolean)returnStatement.get(Constant.IS_ARRAY);
			if(isArray) {
				throw new TypeException("Function: " + funcName + " returns an array and they aren't allowed in boolean expressions");
			} else {
				String funcType = (String) returnStatement.get(Constant.TYPE);
				if(funcType.equals(Constant.INT) || funcType.equals(Constant.DOUBLE)) {
					if(op == null) {
						if(operator == null) {
							throw new BooleanException("Condition isn't valid");
						} else {
							if(!operator.equals(Constant.MATH)) {
								throw new BooleanException("Condition isn't valid");
							} else {
								operator = null;
							}
						}
					} else {
						String bType = Constant.getOperatorType(op);
						if(operator == null) {
							if(!bType.equals(Constant.MATH)) {
								throw new BooleanException("Condition isn't valid");
							} else {
								operator = Constant.getOperatorType(op);
							}
						} else {
							if(bType.equals(Constant.MATH)) {
								throw new BooleanException("Condition isn't valid");
							} else {
								operator = null;
							}
						}
					}			
				} else if(funcType.equals(Constant.BOOLEAN)) {
					if(operator != null) {
						if(!operator.equals(Constant.BOOLEAN)) {
							throw new BooleanException("Condition isn't valid");	
						}
						if(op != null) {
							String bType = Constant.getOperatorType(op);
							if(!bType.equals(Constant.BOOLEAN)) {
								throw new BooleanException("Condition isn't valid");	
							}
						}
					} else {
						String bType = Constant.getOperatorType(op);
						if(op != null && (op.equals("==") || op.equals("!="))) {
							operator = Constant.BOOLEAN;
						} else if(op != null && bType.equals(Constant.MATH)) {
							throw new BooleanException("Condition isn't valid");
						} else {
							operator = null;
						}
					}
				} else {
					throw new TypeException("Type: " + funcType + " isn't valid in boolean expressions");
				}
			}
		}
		if(ifStatement.get(Constant.VALUE_BOOLEAN) instanceof ArrayList && op != null) {
			Map<String, Object> v = ((List<Map<String, Object>>) ifStatement.get(Constant.VALUE_BOOLEAN)).get(0);
			List<Map<String, Object>> l = (List<Map<String, Object>>) v.get(Constant.VALUE_BOOLEAN);
			if(l != null && l.size() > 0) {
				Map<String, Object> value = l.get(0);
				checkBooleanExpression(value, context, operator);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void checkExpr(Context context, String expr) throws CompilerException {
		String[] exp = expr.split("\\+|\\*|-|/|%");
		Pattern pattern = Pattern.compile("\\d+", Pattern.CASE_INSENSITIVE);
		for(String s : exp) {
			Matcher matcher = pattern.matcher(s);
			if(!matcher.find()) {
				Triple<Object, Boolean, Object> pair = (Triple<Object, Boolean, Object>) context.getType(s);
				if(pair == null) {
					throw new VariableException("Variable: " + s + " doesn't exist");
				}
				String type = (String) pair.getFirst();
				if(!type.equals(Constant.INT) && !type.equals(Constant.DOUBLE)) {
					throw new TypeException("Variable: " + s + " has type: " + type + " and expected is int, double or float");
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void checkFunction(Map<String, Object> func, String key, Context context) throws CompilerException {
		String funcName = (String) func.get(Constant.NAME);
		if(funcName.equals(Constant.MAIN)) {
			verifiyParamsAreCorrect(func);
		}
		context.insertFunction(funcName);
		context.setCurrentFunction((String)func.get(Constant.NAME));
		for(Map<String, Object> param : (List<Map<String, Object>>) func.get(Constant.PARAMETERS)) {
			String name = (String)param.get(Constant.NAME);
			String type = (String) param.get(Constant.TYPE);
			boolean isArray = (boolean) param.get(Constant.IS_ARRAY);
			boolean isMatrix = (boolean) param.get(Constant.IS_MATRIX);
			if(context.hasVarInCurrentScope(name)) {
				throw new VariableException("Variable: " + name + " already exists in this scope");
			}
			if(context.hasVarInPreviousScopes(name)) {
				throw new VariableException("Variable: " + name + " already exists in outer functions");
			}
			if(context.getCurrentFunction() == null)
				context.setType(name, (String) param.get(Constant.TYPE), isArray, isMatrix, null, true);
			else
				context.setType(name, (String) param.get(Constant.TYPE), isArray, isMatrix, null, false);

			checkRefinement(name, type, param, context);
			try {
				solver.solve();
			} catch(CompilerException | SolverException | InterruptedException e) {
				throw new RefinementException(e.getMessage());
			}

		}
		verify(context, (List<Map<String, Object>>) func.get(Constant.STATEMENT));
	}

	@SuppressWarnings("unchecked")
	private void verifiyParamsAreCorrect(Map<String, Object> func) throws CompilerException {
		List<Map<String, Object>> params = (List<Map<String, Object>>) func.get(Constant.PARAMETERS);
		if(params.size() > 2) {
			throw new FunctionException("Function main only receives two parameters (Int and [String])");
		}
		String type = (String) params.get(0).get(Constant.TYPE);
		boolean isArray = (boolean) params.get(0).get(Constant.IS_ARRAY);
		if(!type.equals(Constant.INT) || isArray) {
			throw new FunctionException("First parameter has to be Int and can't be an array");
		}
		type = (String) params.get(1).get(Constant.TYPE);
		isArray = (boolean) params.get(1).get(Constant.IS_ARRAY);
		if(!type.equals(Constant.STRING) || !isArray) {
			throw new FunctionException("Second parameters has to be String and array");
		}
	}

	@SuppressWarnings("unchecked")
	private void checkRefinement(String paramName, String paramType, Map<String, Object> param, Context context) throws CompilerException {
		List<Map<String, Object>> refinement = (List<Map<String, Object>>) param.get(Constant.REFINEMENT);
		if(refinement != null) {
			Map<String, Object> ref = refinement.get(0);
			String refName = (String) ref.get(Constant.VARIABLE);
			if(!paramName.equals(refName)) {
				throw new RefinementException("Variable inside refinement: " + refName + " isn't the one expected: " + paramName);
			}
			String type = (String) ref.get(Constant.VALUE_TYPE);
			checkType(paramType, type, ref, context);
			boolean isArray = ((Triple<List<Object>, Boolean, Boolean>) context.getType(refName)).getThird();
			checkArray(isArray, ref, context);
			checkIfIsSat(context, ref, type, refName);
			if(ref.get(Constant.REFINEMENT_VALUE) != null)  {
				Map<String, Object> nRef = ((List<Map<String, Object>>) ref.get(Constant.REFINEMENT_VALUE)).get(0);
				String op = (String) nRef.get(Constant.OPERATOR);
				solver.addOperator(op);
				checkRefinement(paramName, paramType, nRef, context);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void checkIfIsSat(Context context, Map<String, Object> ref, String type, String refName) throws CompilerException {
		String op = (String) ref.get(Constant.OPERATOR);
		try {
			if(type.equals(Constant.INT)) {
				int value = Integer.parseInt((String) ref.get(Constant.VALUE));
				solver.add(refName, value, op);
			} else if(type.equals(Constant.DOUBLE)) {
				double value = Double.parseDouble((String) ref.get(Constant.VALUE));
				solver.add(refName, value, op);
			} else if(type.equals(Constant.VARIABLE)) {
				String var = (String) ref.get(Constant.VALUE);
				List<Object> l = ((Triple<List<Object>, Boolean, Boolean>)context.getType(var)).getFirst();
				String value = (String) l.get(1);
				if(value == null) {
					solver.addEveryPossibility(refName, var, op);
				} else {
					while(!value.startsWith("\"") && !value.matches("\\d+(.\\d+)?") && !value.matches("false") && !value.matches("true")) {
						l = ((Triple<List<Object>, Boolean, Boolean>)context.getType(value)).getFirst();
						value = (String) l.get(1);
					}
					if(value.startsWith("\"")) {
						throw new RefinementException("Variable: " + var + " is a String and Strings can't be refined");
					}
					ref.put(Constant.VALUE, value);
					if(value.matches("\\d+(.\\d+)?")) {
						checkIfIsSat(context, ref, Constant.DOUBLE, refName);
					} else if(value.matches("\\d+")) {
						checkIfIsSat(context, ref, Constant.INT, refName);
					} else {
						checkIfIsSat(context, ref, Constant.BOOLEAN, refName);
					}
				}
			} else if(type.equals(Constant.BOOLEAN)) {
				if(!op.equals("==") && !op.equals("!=")) {
					throw new RefinementException("Operator: " + op + " isn't valid for boolean types");
				}
				boolean value = Boolean.parseBoolean((String)ref.get(Constant.VALUE));
				solver.addBoolean(refName, value, op);
			} else {
				throw new RefinementException("Not implemented for type: " + type);
			}
		} catch(SolverException | InterruptedException e) {
			throw new RefinementException(e.getMessage());
		}		
	}

	@SuppressWarnings("unchecked")
	private void checkVariable(Map<String, Object> statements, String key, Context context) throws CompilerException {
		Map<String, Object> variable = ((List<Map<String, Object>>) statements.get(key)).get(0);
		String name = (String) variable.get(Constant.NAME);
		String type = (String) variable.get(Constant.TYPE);
		if(type == null) {
			if(!context.hasVar(name)) {
				throw new VariableException("Variable: " + name + " doesn't exist");
			} else {
				List<Map<String, Object>> position = (List<Map<String, Object>>) variable.get(Constant.POSITION);
				boolean isArray = ((Triple<Object, Boolean, Object>)context.getType(name)).getSecond();
				if(position != null && isArray) {
					position = (List<Map<String, Object>>) position.get(0).get(Constant.VALUE);
					checkPosition(position, context, name);
					String valueType = (String)variable.get(Constant.VALUE_TYPE);
					String actualType = (String) ((Triple<List<Object>, Boolean, Boolean>) context.getType(name)).getFirst().get(0);
					checkType(actualType, valueType, variable, context);
					
				} else {
					checkArray(isArray, variable, context);
				}
			}
		} else {
			boolean isArray = (boolean) variable.get(Constant.IS_ARRAY);
			boolean isMatrix = (boolean) variable.get(Constant.IS_MATRIX);
			if(context.hasVarInCurrentScope(name)) {
				throw new VariableException("Variable: " + name + " already exists");
			} else {
				Object v = variable.get(Constant.VALUE);
				if(v == null) {
					v = variable.get(Constant.VALUE_FUNC);
				}
				if(v instanceof ArrayList) {
					String varType = (String) variable.get(Constant.VALUE_TYPE);
					Map<String, Object> value = ((List<Map<String, Object>>) v).get(0);
					if(varType != null && varType.equals(Constant.ARRAYS)) {
						checkValidArray(value, context);
						if(context.getCurrentFunction() == null)
							context.setType(name, type, isArray, isMatrix, value, true);
						else
							context.setType(name, type, isArray, isMatrix, value, false);
					} else {
						
						String t = (String) variable.get(Constant.VALUE_TYPE);
						if(t.equals(Constant.EXPR) && context.getCurrentFunction() == null) {
							throw new VariableException("Variable: " + name + " can't be defined as an expression since there is no function defined yet");
						}
						String valueType = getValueType(value, null, context, isArray);
						checkType(type, valueType, variable, context);
						checkArray(isArray, variable, context);
						String variableValueType = (String) variable.get(Constant.VALUE_TYPE);
						if(variableValueType.equals(Constant.FUNCTION_CALL)) {
							if(context.getCurrentFunction() == null) {
								throw new FunctionException("Function calls aren't valid when declaring functions");
							}
							checkParams(context, (List<Map<String, Object>>)variable.get(Constant.VALUE_FUNC), name);
						}
						if(context.getCurrentFunction() == null)
							context.setType(name, type, isArray, isMatrix, value, true);
						else
							context.setType(name, type, isArray, isMatrix, value, false);
					}
					
				} else {
					String valueType = (String) variable.get(Constant.VALUE_TYPE);
					checkType(type, valueType, variable, context);
					checkArray(isArray, variable, context);
					if(context.getCurrentFunction() == null) {
						context.setType(name, type, isArray, isMatrix, v, true);
					} else {
						context.setType(name, type, isArray, isMatrix, v, false);
					}
				}
			}
		}
	}	

	@SuppressWarnings("unchecked")
	private void checkParams(Context context, List<Map<String, Object>> list, String varName) throws CompilerException {
		List<Map<String, Object>> function = (List<Map<String, Object>>) list.get(0).get(Constant.FUNCTION);
		String funcName = (String) function.get(0).get(Constant.VARIABLE);

		if(!context.functionInScope(funcName)) {
			throw new FunctionException("Function: " + funcName + " isn't defined in this scope");
		}

		List<Map<String, Object>> params = (List<Map<String, Object>>) function.get(0).get(Constant.PARAMETERS);

		if(funcName.equals(Constant.PRINT_F)) {
			checkPrintFunction(context, params);
		} else {
			Triple<List<Map<String, Object>>, Map<String, Boolean>, Object> func = (Triple<List<Map<String, Object>>, Map<String, Boolean>, Object>)context.getFunction(funcName);
			if(func == null) {
				throw new FunctionException("Function: " + funcName +  " doesn't exist");
			}
			List<Map<String, Object>> actualParams = func.getFirst();
			if(params.size() != actualParams.size()) {
				throw new FunctionException("Function: " + funcName + " receives: " + actualParams.size() + " params but got " + params.size());
			}
			for(int i = 0; i < actualParams.size(); i++) {
				Map<String, Object> mapParams = (Map<String, Object>)actualParams.get(i);
				String actualType = (String) mapParams.get(Constant.TYPE);
				String paramType = (String) params.get(i).get(Constant.VALUE_TYPE);
				if(paramType == null) {
					List<Map<String, Object>> functionCallParams = new ArrayList<>();
					functionCallParams.add(params.get(i));
					checkParams(context, functionCallParams, varName);
					Triple<List<Map<String, Object>>, Map<String, Object>, Object> pair = (Triple<List<Map<String, Object>>, Map<String, Object>, Object>) context.getFunction(funcName);
					String funcReturnType = (String) pair.getSecond().get(Constant.TYPE);
					if(varName != null) {
						checkType(actualType, funcReturnType, params.get(i), context);
					}
					boolean funcArray = (boolean) pair.getSecond().get(Constant.IS_ARRAY);
					if(funcArray) {
						if(!(boolean) actualParams.get(i).get(Constant.VALUE)) {
							throw new TypeException("Function: " + funcName + " isn't expected to receive an array at parameter: " + i);
						}
					} else {
						if((boolean) actualParams.get(i).get(Constant.IS_ARRAY)) {
							throw new TypeException("Function: " + funcName + " is expected to receive an array at parameter: " + i);
						}
					}
				} else {
					if(paramType.equals(Constant.VARIABLE)) {
						String value = (String) params.get(i).get(Constant.VALUE);
						Triple<List<Object>, Boolean, Object> pair = (Triple<List<Object>, Boolean, Object>) context.getType(value);
						if(pair == null) {
							throw new VariableException("Variable: " + value + " used to call function: " + funcName + " doesn't exist");
						} else {
							params.get(i).put(Constant.VALUE_ACTUAL_TYPE, (String) pair.getFirst().get(0));
							params.get(i).put(Constant.IS_ARRAY, (boolean)pair.getSecond());
							params.get(i).put(Constant.IS_MATRIX, (boolean)pair.getThird());
							boolean actualIsArray = (boolean) mapParams.get(Constant.IS_ARRAY);
							checkArray(actualIsArray, params.get(i), context);
						}
					} else {
						boolean actualIsArray = (boolean) mapParams.get(Constant.IS_ARRAY);

						if(actualIsArray) {
							String value = (String) params.get(i).get(Constant.VALUE);
							throw new VariableException("Value: " + value +" used to call function: " + funcName + " isn't an array and it is expected to be");
						}
					}
					checkType(actualType, paramType, params.get(i), context);
				}
			}	
		}


	}

	private void checkPrintFunction(Context context, List<Map<String, Object>> params) throws CompilerException {
		if(params.size() > 0) {
			String firstParamType = (String) params.get(0).get(Constant.VALUE_TYPE);
			if(!firstParamType.equals(Constant.STRING)) {
				throw new FunctionException("First parameter of printf has to be a function");
			} else {
				String firstParamValue = (String) params.get(0).get(Constant.VALUE);
				int paramsSize = 1;
				for(String placeHolder : Constant.PLACE_HOLDERS) {
					paramsSize += StringUtils.countMatches(firstParamValue, placeHolder);
				}
				if(paramsSize != params.size()) {
					throw new FunctionException("Printf function has invalid number of parameters");
				}
				List<Triple<String, Integer, Object>> positions = new ArrayList<>();
				for(String placeHolder : Constant.PLACE_HOLDERS) {
					int index = firstParamValue.indexOf(placeHolder);
					while (index >= 0) {
						positions.add(new Triple<>(placeHolder, index, null));
						index = firstParamValue.indexOf(placeHolder, index + 1);
					}
				}
				sortPositions(positions);
				checkPrintParams(context, params, positions);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void checkPrintParams(Context context, List<Map<String, Object>> params,
			List<Triple<String, Integer, Object>> positions) throws CompilerException {
		int i = 0;
		int index = 0;
		for(Map<String, Object> param : params) {
			if(i == 0) {
				i++;
				continue;
			} else {
				String expectedType = getType(positions.get(index));
				String actualType = (String) param.get(Constant.VALUE_TYPE);
				if(actualType == null) {
					String varName = (String) ((List<Map<String, Object>>)param.get(Constant.FUNCTION)).get(0).get(Constant.VARIABLE);
					Triple<List<Object>, Map<String, Object>,Object> triple = (Triple<List<Object>, Map<String, Object>,Object>) context.getFunction(varName);
					Map<String, Object> map = triple.getSecond();
					actualType = (String) map.get(Constant.TYPE);
					param.put(Constant.IS_ARRAY, (boolean) map.get(Constant.IS_ARRAY));
					param.put(Constant.IS_MATRIX, (boolean) map.get(Constant.IS_MATRIX));
				} else if(actualType.equals(Constant.EXPR)) {
					Map<String, Object> expr = ((List<Map<String, Object>>)param.get(Constant.VALUE)).get(0);
					actualType = getValueType(expr, null, null, false);
					param.put(Constant.IS_ARRAY, false);
					param.put(Constant.IS_MATRIX, false);
				} else if(actualType.equals(Constant.VARIABLE)) {
					Triple<List<Object>, Boolean, Boolean> triple = (Triple<List<Object>, Boolean, Boolean>) context.getType((String)param.get(Constant.VALUE));
					actualType = (String) triple.getFirst().get(0);
					boolean isArray = (boolean) triple.getSecond();
					boolean isMatrix = (boolean) triple.getThird();
					param.put(Constant.IS_ARRAY, isArray);
					param.put(Constant.IS_MATRIX, isMatrix);
				} else if(actualType.equals(Constant.ARRAYS)) {
					String varName = (String) ((List<Map<String, Object>>) param.get(Constant.VALUE)).get(0).get(Constant.VARIABLE);
					Triple<List<Object>, Boolean, Boolean> triple = (Triple<List<Object>, Boolean, Boolean>) context.getType(varName);
					actualType = (String) triple.getFirst().get(0);
					boolean isArray = (boolean) triple.getSecond();
					boolean isMatrix = (boolean) triple.getThird();
					param.put(Constant.IS_ARRAY, isArray);
					param.put(Constant.IS_MATRIX, isMatrix);
				}
				param.put(Constant.VALUE_ACTUAL_TYPE, actualType);

				if(!expectedType.equals(actualType)) {
					throw new TypeException(positions.get(index).getFirst() + " is trying to refer to a: " + expectedType + " and got: " + actualType);
				}
				index++;
			}
		}
	}

	private String getType(Triple<String, Integer, Object> pair) {
		String type = pair.getFirst();
		switch(type) {
		case "%s":
			return Constant.STRING;
		case "%i":
			return Constant.INT;
		case "%d":
			return Constant.DOUBLE;
		case "%b":
			return Constant.BOOLEAN;
		}
		return null;
	}

	private void sortPositions(List<Triple<String, Integer, Object>> positions) {
		Collections.sort(positions, new Comparator<Triple<String, Integer, Object>>() {
			@Override
			public int compare(Triple<String, Integer, Object> pair1, Triple<String, Integer, Object> pair2) {
				return pair1.getSecond().compareTo(pair2.getSecond());
			}
		});
	}

	@SuppressWarnings("unchecked")
	private void checkArray(boolean isArray, Map<String, Object> variable, Context context) throws CompilerException {
		String valueType = (String) variable.get(Constant.VALUE_TYPE);
		if(isArray) {
			if(!valueType.equals(Constant.VARIABLE) && !valueType.equals(Constant.FUNCTION_CALL)) {
				throw new VariableException("Can't assign a value to an array");
			} else {
				if(valueType.equals(Constant.VARIABLE)) {
					String varName = (String) variable.get(Constant.VALUE);
					boolean o = ((Triple<String, Boolean, Object>) context.getType(varName)).getSecond() ;
					if(!o) {
						throw new TypeException("Can't assign a value to an array");
					}
				} else if(valueType.equals(Constant.FUNCTION_CALL)) {
					List<Map<String, Object>> function = (List<Map<String, Object>>) ((List<Map<String, Object>>)variable.get(Constant.VALUE_FUNC)).get(0).get(Constant.FUNCTION);
					String funcName = (String) function.get(0).get(Constant.VARIABLE);
					Map<String, Object> rValue = ((Triple<List<String>, Map<String, Object>, Object>)context.getFunction(funcName)).getSecond();
					boolean funcArray = (boolean) rValue.get(Constant.IS_ARRAY);
					if(!funcArray) {
						throw new TypeException("Can't assign a value to an array");
					}
				}
			}
		} else {
			if(valueType.equals(Constant.VARIABLE)) {
				String varName = (String) variable.get(Constant.VALUE);
				boolean o = ((Triple<String, Boolean, Object>) context.getType(varName)).getSecond() ;
				if(o) {
					throw new TypeException("Can't assign a value to an array");
				}
			} else if(valueType.equals(Constant.FUNCTION_CALL)) {
				List<Map<String, Object>> function = (List<Map<String, Object>>) ((List<Map<String, Object>>)variable.get(Constant.VALUE_FUNC)).get(0).get(Constant.FUNCTION);
				String funcName = (String) function.get(0).get(Constant.VARIABLE);
				Map<String, Object> rValue = ((Triple<List<String>, Map<String, Object>, Object>)context.getFunction(funcName)).getSecond();
				boolean funcArray = (boolean) rValue.get(Constant.IS_ARRAY);
				if(funcArray) {
					throw new TypeException("Can't assign a value to an array");
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private String getValueType(Map<String, Object> value, String valueType, Context context, boolean isArray) throws CompilerException {
		if(value.isEmpty()) {
			return Constant.VOID;
		}
		if(valueType == null) {
			valueType = (String) value.get(Constant.VALUE_TYPE);
		} else {
			if(!valueType.equals(Constant.DOUBLE)) {
				valueType = (String) value.get(Constant.VALUE_TYPE);
			}
		}
		if(valueType == null) {
			String funcName = (String) ((List<Map<String, Object>>) value.get(Constant.FUNCTION)).get(0).get(Constant.VARIABLE);
			if(!context.hasFunction(funcName)) {
				throw new FunctionException("Function: " + funcName + " doesn't exist");
			}
			Map<String, Object> pair = ((Triple<List<String>, Map<String, Object>, Object>)context.getFunction(funcName)).getSecond();
			valueType = (String) pair.get(Constant.TYPE);
			boolean funcArray = (boolean) pair.get(Constant.IS_ARRAY);
			if(funcArray) {
				if(!isArray) {
					throw new VariableException("Function: " + funcName + " returns an array and it isn't expected"); 
				}
			} else {
				if(isArray) {
					throw new VariableException("Function: " + funcName + " doesn't return an array and it expected"); 
				}
			}
		}
		if(valueType.equals(Constant.FUNCTION_CALL)) {
			if(context.getCurrentFunction() == null) {
				throw new VariableException("Function calls aren't allowed outside of functions");
			}
			List<Map<String, Object>> f = (List<Map<String, Object>>) value.get(Constant.VALUE_FUNC);
			checkParams(context, f, null);
			String funcName = (String) ((List<Map<String, Object>>)f.get(0).get(Constant.FUNCTION)).get(0).get(Constant.VARIABLE);
			String rType = (String) ((Triple<List<Map<String, Object>>, Map<String, Object>, Object>) context.getFunction(funcName)).getSecond().get(Constant.TYPE);
			valueType = rType;
		} 
		if(valueType.equals(Constant.VARIABLE)) {
			Triple<List<Object>, Boolean, Object> type =(Triple<List<Object>, Boolean, Object>)context.getType((String)value.get(Constant.EXPRESSION_VALUE));
			if(type != null) {
				valueType = (String) ((List<Object>) type.getFirst()).get(0);
			} else {
				String v = (String) value.get(Constant.EXPRESSION_VALUE);
				if(v != null && !context.hasVar(v)) {
					throw new VariableException("Variable: " + v + " doesn't exist");
				}
				String varName = (String) value.get(Constant.VALUE);
				type = (Triple<List<Object>, Boolean, Object>)context.getType(varName);
				if(type == null) {
					throw new VariableException("Variable: " + varName + " doesn't exist");
				} else {
					boolean varArray = (boolean) type.getSecond();
					if(varArray) {
						if(!isArray) {
							throw new VariableException("Variable: " + varName + " is an array and it isn't expected");
						}
					} else {
						if(isArray) {
							throw new VariableException("Variable: " + varName + " isn't an array and it expected to be");
						}
					}
					valueType = (String) ((List<Object>) type.getFirst()).get(0);
				}
			}
		} 
		
		if(value.get(Constant.EXPRESSION_VALUE) != null &&  value.get(Constant.VALUE) != null) {
			Map<String, Object> values = ((List<Map<String, Object>>)((List<Map<String, Object>>)value.get(Constant.VALUE)).get(0).get(Constant.VALUE)).get(0);
			valueType = getValueType(values, valueType, context, isArray);
		}
		return valueType;
	}

	@SuppressWarnings("unchecked")
	private void checkType(String type, String valueType, Map<String, Object> map, Context context) throws CompilerException {
		if(!type.equals(valueType)) {
			if(type.equals(Constant.VOID)) {
				throw new TypeException("Expecting empty return and return type: " + valueType);
			} else if(valueType.equals(Constant.VARIABLE)) {
				String value = (String) map.get(Constant.VALUE);
				Triple<List<Object>, Boolean, Object> pair = (Triple<List<Object>, Boolean, Object>)context.getType(value);
				if(pair == null) {
					throw new VariableException("Variable: " + value + " doesn't exist");
				}
				String varType = (String) ( (List<Object>)pair.getFirst()).get(0);
				if(varType == null) {
					throw new VariableException("Variable: " + value + " doesn't exist");
				}
				if(!type.equals(varType)) {
					if(type.equals(Constant.STRING) || type.equals(Constant.INT)) {
						throw new TypeException("Excepting type: " + type + " and got: " + varType);
					} else if(type.equals(Constant.DOUBLE)) {
						if(!varType.equals(Constant.INT)) {
							throw new TypeException("Excepting type: " + type + " and got: " + varType);
						}
					}
				}
			} else if(type.equals(Constant.STRING) || type.equals(Constant.BOOLEAN)) {
				throw new TypeException("Excepting type: " + type + " and got: " + valueType);
			} else if(type.equals(Constant.DOUBLE)) {
				if(valueType.equals(Constant.EXPR)) {
					Map<String, Object> exprMap = ((List<Map<String, Object>>) map.get(Constant.VALUE)).get(0);
					String exprType = getValueType(exprMap, valueType, context, false);
					checkType(type, exprType, map, context);
				} else if(!valueType.equals(Constant.INT)) {
					throw new TypeException("Excepting type: " + type + " and got: " + valueType);
				}
			} else if(type.equals(Constant.INT)) {
				if(valueType.equals(Constant.EXPR)) {
					Map<String, Object> exprMap = ((List<Map<String, Object>>) map.get(Constant.VALUE)).get(0);
					String exprType = getValueType(exprMap, valueType, context, false);
					checkType(type, exprType, map, context);
				} else {
					throw new TypeException("Excepting type: " + type + " and got: " + valueType);	
				}
			}
		}
	}

}
