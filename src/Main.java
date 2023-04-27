import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import exception.CompilerException;
import exception.FunctionException;
import exception.PositionInvalidException;
import exception.TypeException;
import exception.VariableException;
import utils.Pair;

public class Main {

	private static final String TERMINAL_NODE_IMPL = "TerminalNodeImpl";
	private static final String COMMENT = "Comment";
	private static final String DEFINITION = "Definition";
	private static final String VNAME_TYPE = "Vname_type";
	private static final String VNAME_TYPE_OPTIONAL = "Vname_type_optional";
	private static final String FUNCTION = "Function";
	private static final String STATEMENT = "Statement";
	private static final String ARGS_DEF = "Args_def";
	private static final String IF_STATEMENT = "If_statement";
	private static final String ELSE_STATEMENT = "Else_statement";
	private static final String RETURN_STATEMENT = "Return_statement";
	private static final String CONDITIONAL_VALUES = "Conditions_values";
	private static final String COND_VALUE = "cond_value";
	private static final String OPERATOR = "Operator";
	private static final String RETURN = "Return_statement";
	private static final String BOOLEAN_EXPRESSION = "Boolean_expression";
	private static final String WHILE_STATEMENT = "While_statement";
	private static final String DECLARATION = "Declaration";
	private static final String VALUE = "Value";
	private static final String STRING_LIT = "String_lit";
	private static final String EXPR = "Expr";
	private static final String EXPRESSION_VALUE = "Expr_value";
	private static final String FUNCTION_CALL = "Function_call";
	private static final String EXPRESSION = "Expression";
	private static final String ARRAYS = "Arrays";
	private static final String POSITION = "Position";
	private static final String POS = "Pos";
	private static final String VARIABLE = "Variable";
	private static final String NUMBER = "Number";
	private static final String INT = "Int";
	private static final String DOUBLE = "Double";
	private static final String STRING = "String";
	private static final String BOOLEAN = "Boolean";
	private static final String FLOAT = "Float";
	private static final String CONDITIONS_VALUES_SINGLE = "Conditions_values_single";
	private static final String VOID = "Void";
	private static final String STATEMENT_VALUE = "Statement_value";



	private static final int VARIABLE_TYPE = 35;
	private static final int BOOLEAN_TYPE = 8;
	private static final int NOT_OPERATOR_TYPE = 32;
	private static final int NUMBER_INT_TYPE = 28;
	private static final String[] MATH_OPERATOR = {"*", "+", "-", "/", "%"};
	private static final String[] BOOLEAN_OPERATOR = {"&&", "||"};

	public static void main(String[] args) throws IOException, CompilerException {
		boolean treeFlag = containsFlag(args, "--tree");
		for(int i = 0; i < args.length; i++) {
			if(!args[i].equals("--tree" )) {
				try {
					CharStream input = CharStreams.fromFileName(args[i]);
					GrammarLexer lexer = new GrammarLexer(input);

					CommonTokenStream tokens = new CommonTokenStream(lexer);
					GrammarParser parser = new GrammarParser(tokens);
					parser.setBuildParseTree(true);
					ParseTree p = parser.prog();
					Map<String, Object> tree = toMap(p);

					if(treeFlag) {
						System.out.println(tree);
					}
					Context c = new Context();
					addFunctions(c, tree);
					verify(c, tree);
				} catch(IOException e) {
					System.out.println("File " + args[i] + " does not exist");
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static void addFunctions(Context context, Map<String, Object> map) throws CompilerException {
		String first = new ArrayList<>(map.keySet()).get(0);
		List<Map<String, Object>> prog = (List<Map<String, Object>>) map.get(first);
		for(Map<String, Object> p: prog) {
			List<String> setKeys = new ArrayList<>(p.keySet());
			String type = setKeys.get(0);
			if(type.equals(STATEMENT) || type.equals(DEFINITION) ||
					type.equals(IF_STATEMENT) || type.equals(WHILE_STATEMENT) || type.equals(ELSE_STATEMENT)) {
				addFunctions(context, p);
			} else if(type.equals(FUNCTION)) {
				addNameFunction(context, p, type);
			} else if(type.equals(DECLARATION)) {
				addNameFunction(context, p, type);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static void addNameFunction(Context context, Map<String, Object> p, String type) throws CompilerException {
		List<Map<String, Object>> function = (List<Map<String, Object>>) p.get(type);
		Map<String, Object> f = (Map<String, Object>) function.get(0);
		List<Map<String, Object>> vnameType = (List<Map<String, Object>>) f.get(VNAME_TYPE);
		String name = (String) vnameType.get(0).get("name");
		if(!context.hasFunction(name)) {
			String returnType = (String) vnameType.get(0).get("type");
			List<String> argsType = new ArrayList<>();
			if(function.size() > 1) {
				List<Map<String, Object>> argsDef = (List<Map<String, Object>>) function.get(1).get(ARGS_DEF);
				List<String> argsName = new ArrayList<>();
				List<Boolean> argsArray = new ArrayList<>();
				if(argsDef != null) {
					for(Map<String, Object> arg : argsDef) {
						List<Map<String, Object>> argNameType = (List<Map<String, Object>>) arg.get(VNAME_TYPE);
						String argType = (String) argNameType.get(0).get("type");
						argsType.add(argType);
						String argName = (String) argNameType.get(0).get("name");
						argsName.add(argName);
						boolean argArray = Boolean.parseBoolean(((String) argNameType.get(0).get("Array")));
						argsArray.add(argArray);
					}
				}
			}
			
			context.setFunction(name, new Pair<String, List<String>>(returnType, argsType));
		} else {
			throw new FunctionException("Function with name " + name + " already exists");
		}
		if(type.equals(FUNCTION)) {
			addFunctions(context, p);
		}
	}

	@SuppressWarnings("unchecked")
	private static void verify(Context context, Map<String, Object> map) throws CompilerException {
		String first = new ArrayList<>(map.keySet()).get(0);
		List<Map<String, Object>> prog = (List<Map<String, Object>>) map.get(first);
		for(Map<String, Object> p: prog) {
			List<String> setKeys = new ArrayList<>(p.keySet());
			String type = setKeys.get(0);
			if(type.equals(STATEMENT) || type.equals(EXPRESSION)) {
				verify(context, p);
			} else if(type.equals(DEFINITION)) {
				context.enterScope();
				verify(context, p);
				context.exitScope();
			} else if(type.equals(FUNCTION) || type.equals(DECLARATION)) {
				function(context, p, type);
			} else if(type.equals(VALUE)) {
				value(context, p);
			} else if(type.equals(IF_STATEMENT) || type.equals(WHILE_STATEMENT)) {
				List<Map<String, Object>> l = (List<Map<String, Object>>) p.get(type);
				for(Map<String, Object> b : l) {
					if(b.get(BOOLEAN_EXPRESSION) != null)
						checkConditionValue(context, b, null);
				}				
				context.enterScope();
				verify(context, p);
				context.exitScope();
			} else if(type.equals(ELSE_STATEMENT)) {
				context.enterScope();
				verify(context, p);
				context.exitScope();
			} else if(type.equals(RETURN_STATEMENT)) {
				checkReturn(context, p);
			} else if(type.equals(STATEMENT_VALUE)) {
				checkStatementValue(context, p);
			} else if(type.equals(FUNCTION_CALL)) {
				checkFunctionCall(context, p);
			} else if(type.equals(ARRAYS)) {
				checkIndexArrays(context, (Map<String, Object>) p.get(ARRAYS));
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static void checkIndexArrays(Context context, Map<String, Object> p) throws CompilerException {
		String vName = (String) p.get(VARIABLE);
		if(vName != null && context.getType(vName) == null) {
			throw new VariableException("Variable " + vName  + " doesnt exist");
		}
		if(vName != null && !context.isArray(vName)) {
			throw new VariableException("Variable " + vName + " isn't an array");
		}
		List<Map<String, Object>> positions = (List<Map<String, Object>>)p.get(POSITION);
		for(Map<String, Object> pos : positions) {
			List<Map<String, Object>> listPositions = (List<Map<String, Object>>) pos.get(POS);
			if(listPositions != null) {
				Map<String, Object> pValues = listPositions.get(0);
				String pType = (String) pValues.get("Type");
				if(pType.equals(VARIABLE)) {
					String pName = (String) pValues.get("Value");
					Pair<String, Boolean> pair = (Pair<String, Boolean>) context.getType(pName);
					if(pair == null) {
						throw new VariableException("Variable " + pName + " doesn't exist");
					}
					String definedType = pair.getFirst();
					
					if(!definedType.equals(INT)) {
						throw new PositionInvalidException("Position has to be an int");
					}
				} else {
					if(!pType.equals(INT)) {
						throw new PositionInvalidException("Position has to be an int");
					}
				}
			} else {
				List<Map<String, Object>> lPosition = (List<Map<String, Object>>) pos.get(POSITION);
				if(lPosition != null) {
					checkIndexArrays(context, pos);
				} else {
					String type = (String) pos.get("Type");
					if(type != null) {
						if(type.equals(VARIABLE)) {
							String varName = (String) pos.get(VALUE);
							Pair<String, List<String>> pair = (Pair<String, List<String>>) context.getType(varName);
							if(pair == null) {
								throw new VariableException("Variable " + varName + " doesn't exist");
							}
							String varType = pair.getFirst();
							if(varType == null) {
								throw new VariableException("Variable " + varName + " doesn't exist");
							} else {
								if(!varType.equals(INT)) {
									throw new PositionInvalidException("Position has to be an int not a " + varType);
								}
							}
						}
					}
				}
			}
		}	
	}

	@SuppressWarnings("unchecked")
	private static void checkFunctionCall(Context context, Map<String, Object> p) throws CompilerException {
		Map<String, Object> function = ((List<Map<String, Object>>) p.get(FUNCTION_CALL)).get(0);
		String fname = (String) function.get("function_name");
		if(context.hasFunction(fname)) {
			List<Map<String, Object>> parameters = (List<Map<String, Object>>) function.get("parameters");
			List<String> expectedParameters = ((Pair<String, List<String>>) context.getFunction(fname)).getSecond();
			if(parameters.size() != expectedParameters.size()) {
				throw new FunctionException("Function is receiving incorrect number of parameters");
			} else {
				for(int i = 0; i < parameters.size(); i++) {
					String pa = (String) ((Map<String, Object>) parameters.get(i)).get("pType");
					String expectedPa = expectedParameters.get(i);
					if(!pa.equals(expectedPa)) {
						if(pa.equals(NUMBER)) {
							String value = (String) ((Map<String, Object>) parameters.get(i)).get("pValue");
							if(value.contains(".") && expectedPa.equals(INT)) {
								throw new FunctionException("Function with name " + fname 
										+ " expected paramater " + (i+1) + " with type " + expectedPa 
										+ " and got " + DOUBLE);

							}
						} else if(pa.equals(STRING_LIT)) {
							if(!expectedPa.equals(STRING)) {
								throw new FunctionException("Function with name " + fname 
										+ " expected paramater " + (i+1) + " with type " + expectedPa 
										+ " and got " + pa);
							}
						} else if(pa.equals(VARIABLE)) { 
							String value = (String) ((Map<String, Object>) parameters.get(i)).get("pValue");
							Pair<String, Boolean> varType = (Pair<String, Boolean>) context.getType(value);
							if(varType == null) {
								throw new VariableException("Variable " + value + " doesn't exist");
							} else {
								if(!expectedPa.equals(varType.getFirst())) {
									throw new FunctionException("Function with name " + fname 
											+ " expected paramater " + (i+1) + " with type " + expectedPa 
											+ " and got " + varType);
								}
							}
						} else {
							throw new FunctionException("Function with name " + fname 
									+ " expected paramater " + (i+1) + " with type " + expectedPa 
									+ " and got " + pa);
						}
					}
				}
			}
		} else {
			throw new FunctionException("Function with name " + fname + " does not exist");
		}
	}

	@SuppressWarnings("unchecked")
	private static void checkStatementValue(Context context, Map<String, Object> p) throws CompilerException {
		Map<String, Object> statementeValue = ((List<Map<String, Object>>) p.get(STATEMENT_VALUE)).get(0);
		String type = (String) statementeValue.get("Type");
		if(type != null && type.equals(VARIABLE)) {
			String name = (String) statementeValue.get(VALUE);
			if(context.getType(name) == null) {
				throw new VariableException("Variable " + name + " does not exist");
			}
		}

	}

	@SuppressWarnings("unchecked")
	private static void checkReturn(Context context, Map<String, Object> p) throws CompilerException {
		String currentFunction = context.getCurrentFunction();
		String expectedReturn = ((Pair<String, List<String>>) context.getFunction(currentFunction)).getFirst();
		Map<String, Object> rTypeValue = ((List<Map<String, Object>>) p.get(RETURN_STATEMENT)).get(0);
		String rType = (String) rTypeValue.get("returnType");
		if(rType != null) {
			if(!expectedReturn.equals(rType)) {
				if(rType.equals(VARIABLE)) {
					String name = (String) rTypeValue.get("returnValue");
					String varType = ((Pair<String, Boolean>) context.getType(name)).getFirst();
					if(varType == null) {
						throw new VariableException("Variable " + name + " does not exist");
					}
					if(!expectedReturn.equals(varType)) {
						if(checkIntNumber(expectedReturn, varType)) {
							throw new VariableException("Return type expected " + expectedReturn + " and got " + varType);
						}
					}
				} else {
					throw new VariableException("Return type expected " + expectedReturn + " and got " + rType);
				}
			}
		} else {
			if(checkIntNumber(expectedReturn, rType)) {
				throw new VariableException("Return type expected " + expectedReturn + " and got " + rType);
			} else if(!expectedReturn.equals(VOID)) {
				throw new VariableException("Return is empty");
			}
		}
	}

	private static boolean checkIntNumber(String expectedReturn, String rType) throws CompilerException {
		if(expectedReturn.equals(INT)) {
			return true;
		}
		return false;
	}


	@SuppressWarnings("unchecked")
	private static void checkConditionValue(Context context, Map<String, Object> p, String current) throws CompilerException {
		//List<Map<String, Object>> bExpression = (List<Map<String, Object>>) p.get(type);
		List<Map<String, Object>> values = (List<Map<String, Object>>) p.get(BOOLEAN_EXPRESSION);
		String currentType = current;
		boolean mFound = false;
		for(Map<String, Object> condValues : values) {
			List<Map<String, Object>> c = (List<Map<String, Object>>) condValues.get(CONDITIONAL_VALUES);
			if(c != null) {
				String value = (String) c.get(0).get(COND_VALUE);
				String bType = (String) c.get(0).get("Type");
				if(bType.equals(VARIABLE)) {
					Pair<String, Boolean> pair = (Pair<String, Boolean>) context.getType(value);
					if(pair == null) {
						throw new VariableException("Variable with name " + value + " doesnt' exist");
					}
					bType = pair.getFirst();
				}
				if(currentType == null) {
					currentType = bType;
				} else {
					if(!currentType.equals(bType)) {
						if(currentType.equals(STRING) || bType.equals(STRING)
								|| currentType.equals(BOOLEAN) || bType.equals(BOOLEAN)) {
							throw new VariableException("Expecting " + currentType + " and got " + bType);
						}
					}
				}
			} else {
				List<Map<String, Object>> opList = (List<Map<String, Object>>) condValues.get(OPERATOR);
				if(opList != null) {
					//OPERATOR 
					Map<String, Object> op = opList.get(0);
					if(isMathOperator((String)op.get("operator")) ) {
						if(currentType != null && !currentType.equals(INT) && !currentType.equals(DOUBLE) && !currentType.equals(FLOAT)) {
							throw new VariableException("Can't do math operations with type " + currentType);
						}
					} else if(isBooleanOperator((String)op.get("operator"))) {
						currentType = null;

					} else {
						mFound = !mFound;
					}
				} else {
					//BOOLEAN EXPR
					String typeSingle = (String) condValues.get("Type");
					if(typeSingle != null) {
						if(!typeSingle.equals(BOOLEAN)) {
							String name = (String) condValues.get(COND_VALUE);
							String varType = (String) context.getType(name);
							if(!varType.equals(BOOLEAN)) {
								throw new VariableException("Expecting " + BOOLEAN + " got "+ varType);
							}
						}
					} else {
						checkConditionValue(context, condValues, currentType);	
					}
				}

			}
		}
		if(!mFound && currentType != null && !currentType.equals(BOOLEAN)) {
			throw new VariableException(currentType + " can't be evaluated as a boolean expression");
		}
	}

	private static boolean isBooleanOperator(String op) {
		for(String s : BOOLEAN_OPERATOR) {
			if(s.equals(op)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isMathOperator(String op) {
		for(String s : MATH_OPERATOR) {
			if(s.equals(op)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private static void value(Context context, Map<String, Object> p) throws CompilerException {
		Map<String, Object> statementValue = ((List<Map<String, Object>>) p.get(VALUE)).get(0);
		String type = (String) statementValue.get("type");
		String name = (String) statementValue.get("name");

		if(type == null) {
			if(context.hasVar(name)) {
				String varCurrentType = ((Pair<String, Boolean>) context.getType(name)).getFirst();
				String valueType = (String) statementValue.get("valueType");
				if(valueType == null) { //EXPR
					List<Map<String, Object>> temp = (List<Map<String, Object>>) p.get(VALUE);
					Map<String,Object> values = temp.get(1);
					List<Map<String, Object>> exprValues = (List<Map<String, Object>>) values.get(EXPR);
					String currentType = null;
					for(Map<String, Object> expr : exprValues) {
						List<Map<String, Object>> valuesType = (List<Map<String, Object>>) expr.get(EXPRESSION_VALUE);
						if(valuesType != null) {
							String exprType = (String) valuesType.get(0).get("Type");
							if(exprType.equals(VARIABLE)) {
								String exprName = (String) valuesType.get(0).get(VALUE);
								exprType = (String) context.getType(exprName);
							}
							if(currentType == null) {
								currentType = exprType;
							} else {
								if(exprType.equals(STRING) || exprType.equals(INT) 
										|| exprType.equals(DOUBLE) || exprType.equals(BOOLEAN)) {
									if(!currentType.equals(exprType)) {
										throw new VariableException("Expression has multiple types");
									}
								} else if(exprType.equals(VARIABLE)) {
									String varName = (String) valuesType.get(0).get(VALUE);
									String varType = (String) context.getType(varName);
									if(!currentType.equals(varType)) {
										throw new VariableException("Expression has multiple types");
									}
								} else if(exprType.equals(NUMBER)) {
									String value = (String) valuesType.get(0).get(VALUE);
									if(value.contains(".")) {
										if(!currentType.equals(DOUBLE)) {
											throw new VariableException("Expression has multiple types");
										}
									} else {
										if(!currentType.equals(INT)) {
											throw new VariableException("Expression has multiple types");
										}
									}
								}
							}
						}
					}
					if(!varCurrentType.equals(currentType)){
						throw new VariableException("Expected " + varCurrentType +" and expression type is " + currentType);
					}
				} else if(valueType.equals(NUMBER)) {
					String value = (String)statementValue.get(VALUE);
					if(value.contains(".")) {
						if(!varCurrentType.equals(DOUBLE)) {
							throw new VariableException("Expected " + varCurrentType + " and got " + DOUBLE);
						}
					} else {
						if(!varCurrentType.equals(INT)) {
							throw new VariableException("Expected " + varCurrentType + " and got " + INT);
						}
					}
				} else if(valueType.equals(STRING_LIT)) {
					if(!varCurrentType.equals(STRING)) {
						throw new VariableException("Expected " + varCurrentType + " and got " + STRING);
					}
				}
			} else {
				throw new VariableException("Trying to assign value to variable that hasn't been defined before");
			}
		} else {
			if(context.hasVarInCurrentScope(name)) {
				throw new VariableException("Variable with name " + name + " already exists in this scope");
			}
			String valueType = (String) statementValue.get("valueType");
			if(valueType == null) { //EXPR
				List<Map<String, Object>> temp = (List<Map<String, Object>>) p.get(VALUE);
				Map<String,Object> values = temp.get(1);
				List<Map<String, Object>> exprValues = (List<Map<String, Object>>) values.get(EXPR);
				String currentType = null;
				for(Map<String, Object> expr : exprValues) {
					List<Map<String, Object>> valuesType = (List<Map<String, Object>>) expr.get(EXPRESSION_VALUE);
					if(valuesType != null) {
						String exprType = (String) valuesType.get(0).get("Type");
						if(exprType.equals(VARIABLE)) {
							String exprName = (String) valuesType.get(0).get(VALUE);
							exprType = ((Pair<String, Boolean>) context.getType(exprName)).getFirst();
						}
						if(currentType == null) {
							currentType = exprType;
						} else {
							if(exprType.equals(STRING) || exprType.equals(INT) 
									|| exprType.equals(DOUBLE) || exprType.equals(BOOLEAN)) {
								if(!currentType.equals(exprType)) {
									throw new VariableException("Expression has multiple types");
								}
							} else if(exprType.equals(VARIABLE)) {
								String varName = (String) valuesType.get(0).get(VALUE);
								String varType = (String) context.getType(varName);
								if(!currentType.equals(varType)) {
									throw new VariableException("Expression has multiple types");
								}
							} else if(exprType.equals(NUMBER)) {
								String value = (String) valuesType.get(0).get(VALUE);
								if(value.contains(".")) {
									if(!currentType.equals(DOUBLE)) {
										throw new VariableException("Expression has multiple types");
									}
								} else {
									if(!currentType.equals(INT)) {
										throw new VariableException("Expression has multiple types");
									}
								}
							}
						}
					}
				}
				if(type.equals(DOUBLE) || type.equals(FLOAT)) {
					if (!currentType.equals(type) && !currentType.equals(INT)) {
						throw new VariableException("Expected " + type +" and expression type is " + currentType);
					}
				}
				boolean array = Boolean.parseBoolean((String)statementValue.get("Array"));
				context.setType(name, currentType, array);
			} else if(type.equals(valueType)) {
				boolean array = Boolean.parseBoolean((String)statementValue.get("Array"));
				context.setType(name, type, array);
			} else if(valueType.equals(STRING_LIT)) {
				if(type.length() < 5) {
					throw new TypeException("Expecting " + type + " and got " + STRING);
				}
				if(!type.substring(0, 5).equals(valueType.substring(0, 5))) {
					throw new TypeException("Expecting " + type + " and got " + STRING);
				} else {
					boolean array = Boolean.parseBoolean((String)statementValue.get("Array"));
					context.setType(name, type, array);
				}
			} else if(valueType.equals(NUMBER)) {
				String value = (String) statementValue.get(VALUE);
				if(value.contains(".")) {
					if(!type.equals(DOUBLE) && !type.equals(FLOAT)) {
						throw new VariableException("Expecting " + type + " and got " + DOUBLE);
					} else {
						boolean array = Boolean.parseBoolean((String)statementValue.get("Array"));
						context.setType(name, type, array);
					}
				} else {
					boolean array = Boolean.parseBoolean((String)statementValue.get("Array"));
					context.setType(name, type, array);
				}
			} else if(valueType.equals(BOOLEAN)) {
				if(!type.equals(valueType)) {
					throw new VariableException("Expecting " + type + " and got " + valueType);
				}
			} else {
				if(valueType.equals(VARIABLE)) {
					String varName = (String) statementValue.get("Value");
					if(context.hasVar(varName)) {
						String varType = ((Pair<String, List<String>>) context.getType(varName)).getFirst();
						if((type.equals(STRING_LIT) || varType.equals(STRING_LIT))) {
							if(!type.substring(0, 5).equals(varType.substring(0, 5))) {
								throw new TypeException("Expecting " + type + " and got " + valueType);
							} else {
								boolean array = Boolean.parseBoolean((String)statementValue.get("Array"));
								context.setType(name, type, array);
							}
						} else if(type.equals(varType)) {
							boolean array = Boolean.parseBoolean((String)statementValue.get("Array"));
							context.setType(name, type, array);
						} else {
							throw new VariableException("Variable " + name + 
									" was assigned to be equal to a varialbe that as a type " 
									+ varType + " and the type expected was " + type);
						}
					} else {
						throw new VariableException("Variable " + varName + " doesn't exist");
					}
				}
			}
		}
	}

	private static Map<String, Object> toMap(ParseTree p) {
		Map<String, Object> map = new LinkedHashMap<>();
		traverse(p, map);
		return map;
	}

	@SuppressWarnings("unchecked")
	private static void traverse(ParseTree tree, Map<String, Object> map) {
		boolean b = true;
		if(tree.getParent() != null) {
			String parent = tree.getParent().getClass().getSimpleName().replaceAll("Context$", "");
			if(parent.equals(EXPR) || parent.equals(POSITION)) {
				if(tree instanceof TerminalNodeImpl) {
					Token token = ((TerminalNodeImpl) tree).getSymbol();
					map.put(OPERATOR, token.getText());
					b = false;
				}
			}
		} 
		if(b) {
			List<Map<String, Object>> children = new ArrayList<>();
			String name = tree.getClass().getSimpleName().replaceAll("Context$", "");
			if(name.equals(COMMENT)) {
				StringBuilder bob = new StringBuilder();
				for (int i = 0; i < tree.getChildCount(); i++) {
					bob.append(tree.getChild(i).getText());
				}
				map.put(name, bob.toString());

			} else if(name.equals(VNAME_TYPE)) {
				map.put(name, children);
				Map<String, Object> nested = new LinkedHashMap<>();
				children.add(nested);

				nested.put("name", tree.getChild(0).getText());
				nested.put("type", tree.getChild(2).getChild(0).getText());
				if(tree.getChild(2).getChildCount() > 1) {
					nested.put("Array", "true");
				} else {
					nested.put("Array", "false");
				}
				if(tree.getChildCount() > 2) {
					if(tree.getChild(3) != null) {
						List<Map<String, Object>> refinement = new ArrayList<>();
						Map<String, Object> values = new LinkedHashMap<>();
						values.put("Variable", tree.getChild(3).getChild(1).getText());
						values.put("Operator", tree.getChild(3).getChild(2).getText());
						values.put("Value", tree.getChild(3).getChild(3).getText());
						String type = tree.getChild(3).getChild(3).getClass().getSimpleName().replaceAll("Context$", "");
						if(type.equals(TERMINAL_NODE_IMPL)) {
							Token token = ((TerminalNodeImpl) tree.getChild(3).getChild(3)).getSymbol();
							if(token.getType() == VARIABLE_TYPE) {
								values.put("Type", "Variable");
							} else if(token.getType() == BOOLEAN_TYPE || token.getType() == BOOLEAN_TYPE + 1) {
								values.put("Type", BOOLEAN);
							}
						} else {
							values.put("Type", type);
						}

						refinement.add(values);
						nested.put("Refinement", refinement);						
					}
				}

			} else if(name.equals(RETURN)) {
				map.put(name, children);
				Map<String, Object> nested = new LinkedHashMap<>();
				children.add(nested);

				String type = tree.getChild(1).getClass().getSimpleName().replaceAll("Context$", "");

				if(type.equals(TERMINAL_NODE_IMPL)) {
					Token token = ((TerminalNodeImpl) tree.getChild(1)).getSymbol();
					if(token.getType() == VARIABLE_TYPE) {
						nested.put("returnType", "Variable");
					} else if(token.getType() == BOOLEAN_TYPE || token.getType() == BOOLEAN_TYPE + 1) {
						nested.put("returnType", BOOLEAN);
					}
				} else {
					if(type.equals(NUMBER)) {
						if(tree.getChild(1).getText().contains(".")) {
							nested.put("returnType", DOUBLE);
						} else {
							nested.put("returnType", INT);
						}
					} else {
						nested.put("returnType", STRING);
					}
				}

				nested.put("returnValue", tree.getChild(1).getText());

			} else if(name.equals(EXPRESSION_VALUE)) {
				String nameChild = tree.getChild(0).getClass().getSimpleName().replaceAll("Context$", "");
				List<Map<String, Object>> exprValues = new ArrayList<>();
				map.put(name, exprValues);
				Map<String, Object> exprValue = new LinkedHashMap<>();
				if(nameChild.equals(STRING_LIT)) {
					StringBuilder bob = new StringBuilder();
					for(int i = 0; i < tree.getChild(0).getChildCount(); i++) {
						bob.append(tree.getChild(0).getChild(i));
					}
					exprValue.put(VALUE, bob.toString());
					exprValue.put("Type", nameChild);
				} else if(nameChild.equals(TERMINAL_NODE_IMPL)) {
					Token token = ((TerminalNodeImpl) tree.getChild(0)).getSymbol();
					if(token.getType() == VARIABLE_TYPE) {
						exprValue.put(VALUE, tree.getChild(0).getText());
						exprValue.put("Type", VARIABLE);
					} else if(token.getType() == BOOLEAN_TYPE || token.getType() == BOOLEAN_TYPE + 1) {
						exprValue.put(VALUE, tree.getChild(0).getText());
						exprValue.put("Type", BOOLEAN);
					}
				} else {
					exprValue.put(VALUE, tree.getChild(0).getChild(0).getText());
					exprValue.put("Type", nameChild);
				}

				exprValues.add(exprValue);
			} else if(name.equals(FUNCTION_CALL)) {
				List<Map<String, Object>> function = new ArrayList<>();
				Map<String, Object> values = new LinkedHashMap<>();
				values.put("function_name", tree.getChild(0).getText());
				List<Map<String, String>> parameters = new ArrayList<>();
				if(tree.getChildCount() > 4) {
					for(int j = 0; j < tree.getChild(2).getChildCount(); j++) {
						String nameChild = tree.getChild(2).getChild(j).getClass().getSimpleName().replaceAll("Context$", "");
						String value = tree.getChild(2).getChild(j).getText();
						Map<String, String> p = new LinkedHashMap<>();
						if(!(tree.getChild(2).getChild(j) instanceof TerminalNodeImpl)) {
							if(nameChild.equals(NUMBER)) {
								if(value.contains(".")) {
									p.put("pType", DOUBLE);
								} else {
									p.put("pType", INT);
								}
							} else {
								if(nameChild.equals(STRING_LIT)) {
									p.put("pType", STRING);
								} else {
									p.put("pType", nameChild);
								}
							}
							p.put("pValue", value);
							parameters.add(p);
						} else {
							Token token = ((TerminalNodeImpl) tree.getChild(2).getChild(j)).getSymbol();
							if(token.getType() == VARIABLE_TYPE) {
								p.put("pType", VARIABLE);
								p.put("pValue", value);
								parameters.add(p);
							} else if(token.getType() == BOOLEAN_TYPE || token.getType() == BOOLEAN_TYPE + 1) {
								p.put("pType", BOOLEAN);
								p.put("pValue", value);
								parameters.add(p);
							}

						}
					}

				}

				values.put("parameters", parameters);
				function.add(values);
				map.put(FUNCTION_CALL, function);
			} else if(name.equals(EXPRESSION) && !(tree.getChild(0).getClass().getSimpleName().replaceAll("Context$", "").equals(FUNCTION_CALL) )) {
				List<Map<String, Object>> statementValue = new ArrayList<>();
				Map<String, Object> values = new LinkedHashMap<>();
				statementValue.add(values);
				values.put(VALUE, tree.getChild(0).getText());
				String childName = tree.getChild(0).getClass().getSimpleName().replaceAll("Context$", "");
				if(childName.equals(TERMINAL_NODE_IMPL)) {
					Token token = ((TerminalNodeImpl) tree.getChild(0)).getSymbol();
					if(token.getType() == VARIABLE_TYPE) {
						values.put("Type", VARIABLE);
					} else if(token.getType() == BOOLEAN_TYPE || token.getType() == BOOLEAN_TYPE + 1) {
						values.put("Type", BOOLEAN);
					}
				}
				map.put(STATEMENT_VALUE, statementValue);
			} else if (name.equals(ARRAYS)) {
				String varName = tree.getChild(0).getText();
				int index = -1;
				if(tree.getChildCount() == 5) {
					index = 2;
				} else {
					index = 6;
				}
				String namedChild = tree.getChild(index).getClass().getSimpleName().replaceAll("Context$", "");
				if(namedChild.equals(POSITION)) {
					Map<String, Object> expr_map = new LinkedHashMap<>();
					List<Map<String, Object>> positions = new ArrayList<>();
					expr_map.put(namedChild, positions);
					for(int j = 0; j < tree.getChild(index).getChildCount(); j++) {
						String nameChildChild = tree.getChild(index).getChild(j).getClass().getSimpleName().replaceAll("Context$", "");
						if(nameChildChild.equals(POSITION)) {
							traverse(tree.getChild(index), expr_map);
						} else {
							List<Map<String, Object>> pos = new ArrayList<>();
							Map<String, Object> posValues = new LinkedHashMap<>();
							pos.add(posValues);
							List<Map<String, Object>> typeValuePos = new ArrayList<>();
							posValues.put(nameChildChild, typeValuePos);
							Map<String, Object> mapTypeValues = new LinkedHashMap<>();
							typeValuePos.add(mapTypeValues);
							ParseTree p = tree.getChild(index).getChild(j);
							String nameP = p.getClass().getSimpleName().replaceAll("Context$", "");
							if(nameP.equals(TERMINAL_NODE_IMPL)) {
								Token token = ((TerminalNodeImpl) p).getSymbol();
								if(token.getType() == VARIABLE_TYPE) {
									mapTypeValues.put("Type", VARIABLE);
								} else if(token.getType() == NUMBER_INT_TYPE) {
									mapTypeValues.put("Type", INT);
								}
								mapTypeValues.put(VALUE, p.getText());
							} else if (nameP.equals(POS)) {
								String namePP = p.getChild(0).getClass().getSimpleName().replaceAll("Context$", "");
								if(namePP.equals(TERMINAL_NODE_IMPL)) {
									Token token = ((TerminalNodeImpl) p.getChild(0)).getSymbol();
									if(token.getType() == VARIABLE_TYPE) {
										mapTypeValues.put("Type", VARIABLE);
									} else if(token.getType() == NUMBER_INT_TYPE) {
										mapTypeValues.put("Type", INT);
									}
									mapTypeValues.put(VALUE, p.getText());
								}
								positions.add(mapTypeValues);
							}
						}
					}
					expr_map.put("Variable", varName);
					children.add(expr_map);
					map.put(name, expr_map);
				}
			} else if(name.equals(POS)){
				List<Map<String, Object>> posValues = new ArrayList<>();
				Map<String, Object> v = new LinkedHashMap<>();
				posValues.add(v);
				if(tree.getChild(0) instanceof TerminalNodeImpl) {
					Token token = ((TerminalNodeImpl) tree.getChild(0)).getSymbol();
					if(token.getType() == VARIABLE_TYPE) {
						v.put("Type", VARIABLE);
					} else if(token.getType() == BOOLEAN_TYPE || token.getType() == BOOLEAN_TYPE + 1) {
						v.put("Type", BOOLEAN);
					} else if(token.getType() == NUMBER_INT_TYPE) {
						v.put("Type", INT);
					}
				}
				v.put("Value", tree.getChild(0).getText());
				map.put(name, posValues);
			} else {
				map.put(name, children);
				for (int i = 0; i < tree.getChildCount(); i++) {
					if(name.equals(FUNCTION) || name.equals(ARGS_DEF)) {
						if(tree.getChild(i).getText().length() > 1) {
							Map<String, Object> nested = new LinkedHashMap<>();
							children.add(nested);
							traverse(tree.getChild(i), nested);
						}
					} else if(name.equals(DECLARATION)) {
						if(!(tree.getChild(i) instanceof TerminalNodeImpl)) {
							Map<String, Object> nested = new LinkedHashMap<>();
							children.add(nested);
							traverse(tree.getChild(i), nested);
						}

					} else if(name.equals(BOOLEAN_EXPRESSION)) {
						if (!(tree instanceof TerminalNodeImpl)) {
							String nameChild = tree.getChild(i).getClass().getSimpleName().replaceAll("Context$", "");
							if(nameChild.equals(BOOLEAN_EXPRESSION) || nameChild.equals(CONDITIONAL_VALUES) || nameChild.equals(OPERATOR)) {
								Map<String, Object> nested = new LinkedHashMap<>();
								children.add(nested);
								traverse(tree.getChild(i), nested);
							} else if(nameChild.equals(CONDITIONS_VALUES_SINGLE)) {
								List<Map<String, Object>> bExpr = (List<Map<String, Object>>) map.get(BOOLEAN_EXPRESSION);
								Map<String, Object> bMap = new LinkedHashMap<>();
								bMap.put(COND_VALUE, tree.getChild(i).getText());
								Token token = ((TerminalNodeImpl) tree.getChild(i).getChild(0)).getSymbol();
								if(token.getType() == VARIABLE_TYPE) {
									bMap.put("Type", VARIABLE);
								} else if(token.getType() == BOOLEAN_TYPE || token.getType() == BOOLEAN_TYPE + 1) {
									bMap.put("Type", BOOLEAN);
								}
								bExpr.add(bMap);
							}
						}

					} else if(name.equals(IF_STATEMENT) || name.equals(WHILE_STATEMENT) || name.equals(ELSE_STATEMENT)) {
						if(!(tree.getChild(i) instanceof TerminalNodeImpl)) {
							Map<String, Object> nested = new LinkedHashMap<>();
							children.add(nested);
							traverse(tree.getChild(i), nested);
						} else {
							Token token = ((TerminalNodeImpl) tree.getChild(i)).getSymbol();
							if(token.getType() == NOT_OPERATOR_TYPE) {
								List<Map<String, Object>> booleanV = (List<Map<String, Object>>) map.get(name);
								Map<String, Object> not = new LinkedHashMap<>();
								List<Map<String, Object>> notOp = new ArrayList<>();
								Map<String, Object> notOpMap = new LinkedHashMap<>();
								notOp.add(notOpMap);
								notOpMap.put(COND_VALUE, "Not");
								notOpMap.put("Type", BOOLEAN);
								not.put(BOOLEAN_EXPRESSION, notOp);
								booleanV.add(not);
							}
						}

					} else if(name.equals(CONDITIONAL_VALUES)) {

						Map<String, Object> nested = new LinkedHashMap<>();
						children.add(nested);
						nested.put("cond_value", tree.getText());
						if(tree.getChild(i) instanceof TerminalNodeImpl) {
							Token token = ((TerminalNodeImpl) tree.getChild(i)).getSymbol();
							if(token.getType() == BOOLEAN_TYPE || token.getType() == BOOLEAN_TYPE + 1) {
								nested.put("Type", BOOLEAN);
							} else if(token.getType() == VARIABLE_TYPE) {
								nested.put("Type", VARIABLE);
							}
						} else {
							String childName = tree.getChild(i).getClass().getSimpleName().replaceAll("Context$", "");
							if(childName.equals(NUMBER)) {
								String value = tree.getChild(i).getText();
								if(value.contains(".")) {
									nested.put("Type", DOUBLE);
								} else {
									nested.put("Type", INT);
								}
							} else if(childName.equals(STRING_LIT)) {
								nested.put("Type", STRING);
							}
						}
					} else if(name.equals(OPERATOR)) {
						Map<String, Object> nested = new LinkedHashMap<>();
						children.add(nested);
						nested.put("operator", tree.getText());

					} else if(name.equals(VALUE)) {
						if(!(tree.getChild(i) instanceof TerminalNodeImpl)) {
							String nameChild = tree.getChild(i).getClass().getSimpleName().replaceAll("Context$", "");
							if(nameChild.equals(STRING_LIT)) {
								StringBuilder bob = new StringBuilder();
								for (int j = 0; j < tree.getChild(i).getChildCount(); j++) {
									bob.append(tree.getChild(i).getChild(j).getText());
								}
								List<Map<String, Object>> value = (List<Map<String, Object>>) map.get(VALUE);
								Map<String, Object> values = value.get(0);

								values.put("Value", bob.toString());
								values.put("valueType", nameChild);

							} else if(nameChild.equals(EXPR)){
								Map<String, Object> expr_map = new LinkedHashMap<>();
								for(int j = 0; j < tree.getChild(i).getChildCount(); j++) {
									String nameChildChild = tree.getChild(i).getChild(j).getClass().getSimpleName().replaceAll("Context$", "");
									if(nameChildChild.equals(EXPR)) {
										traverse(tree.getChild(i), expr_map);
									}
								}
								children.add(expr_map);

							} else if(nameChild.equals(VNAME_TYPE_OPTIONAL)) {
								map.put(name, children);
								Map<String, Object> nested = new LinkedHashMap<>();
								children.add(nested);

								nested.put("name", tree.getChild(i).getChild(0).getText());
								if(tree.getChild(0).getChildCount() > 2) {
									nested.put("type", tree.getChild(0).getChild(2).getText());
									if(tree.getChild(0).getChild(2).getChildCount() > 1) {
										nested.put("Array", "true");
									} else {
										nested.put("Array", "false");
									}
								}

							} else {
								String className = tree.getChild(i).getClass().getSimpleName().replaceAll("Context$", "");

								List<Map<String, Object>> value = (List<Map<String, Object>>) map.get(VALUE);
								Map<String, Object> values = value.get(0);

								values.put("Value", tree.getChild(i).getText());
								values.put("valueType", className);
								value.add(values);
							}

						} else {
							List<Map<String, Object>> value = (List<Map<String, Object>>) map.get(VALUE);
							Map<String, Object> values = value.get(0);

							Token token = ((TerminalNodeImpl) tree.getChild(i)).getSymbol();
							if(token.getType() == VARIABLE_TYPE) {
								values.put("valueType", "Variable");
								values.put("Value", tree.getChild(i).getText());
							} else if(token.getType() == BOOLEAN_TYPE || token.getType() == BOOLEAN_TYPE + 1) {
								values.put("valueType", BOOLEAN);
								values.put("Value", tree.getChild(i).getText());
							}			
						}
					} else {
						Map<String, Object> nested = new LinkedHashMap<>();
						children.add(nested);
						traverse(tree.getChild(i), nested);
					}
				}	
			}
		}
	}

	private static boolean containsFlag(String[] args, String flag) {
		for (int i = 0; i < args.length; i++) {
			if(args[i].equals(flag)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private static void function(Context context, Map<String, Object> p, String type) throws CompilerException {
		List<Map<String, Object>> function = (List<Map<String, Object>>) p.get(type);
		Map<String, Object> f = (Map<String, Object>) function.get(0);
		List<Map<String, Object>> vnameType = (List<Map<String, Object>>) f.get(VNAME_TYPE);
		String name = (String) vnameType.get(0).get("name");
		List<String> argsType = new ArrayList<>();
		List<String> argsName = new ArrayList<>();
		List<Boolean> argsArray = new ArrayList<>();
		if(function.size() > 1) {
			List<Map<String, Object>> argsDef = (List<Map<String, Object>>) function.get(1).get(ARGS_DEF);
			if(argsDef != null) {
				for(Map<String, Object> arg : argsDef) {
					List<Map<String, Object>> argNameType = (List<Map<String, Object>>) arg.get(VNAME_TYPE);
					String argType = (String) argNameType.get(0).get("type");
					argsType.add(argType);
					String argName = (String) argNameType.get(0).get("name");
					argsName.add(argName);
					boolean argArray = Boolean.parseBoolean(((String) argNameType.get(0).get("Array")));
					argsArray.add(argArray);
				}
			}
		}
		

		if(type.equals(FUNCTION)) {
			context.setCurrentFunction(name);
			for(int i = 0; i < argsType.size(); i++ ) {
				context.setType(argsName.get(i), argsType.get(i), argsArray.get(i));
			}
			context.enterScope();
			verify(context, p);
			context.exitScope();
			context.setCurrentFunction(null);
		}	
	}
}
