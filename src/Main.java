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
import exception.FunctionAlreadyExistsException;
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



	private static final int VARIABLE_TYPE = 34;
	private static final int BOOLEAN_TYPE = 7;
	private static final int NOT_OPERATOR_TYPE = 31;
	private static final String[] mathOperator = {"<", ">", ">=", "<=", "==", "!=", "*", "+", "-", "/", "%"};

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

					if(treeFlag) {
						System.out.println(toMap(p));
					}
					verify(new Context(), toMap(p));
				} catch(IOException e) {
					System.out.println("File " + args[i] + " does not exist");
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static void verify(Context context, Map<String, Object> map) throws CompilerException {
		String first = new ArrayList<>(map.keySet()).get(0);
		List<Map<String, Object>> prog = (List<Map<String, Object>>) map.get(first);
		for(Map<String, Object> p: prog) {
			List<String> setKeys = new ArrayList<>(p.keySet());
			String type = setKeys.get(0);
			if(type.equals(STATEMENT)) {
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
					String varType = (String) context.getType(name);
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
		for(Map<String, Object> condValues : values) {
			List<Map<String, Object>> c = (List<Map<String, Object>>) condValues.get(CONDITIONAL_VALUES);
			if(c != null) {
				String value = (String) c.get(0).get(COND_VALUE);
				String bType = (String) c.get(0).get("Type");
				if(bType.equals(VARIABLE)) {
					bType = (String) context.getType(value);
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
						if(!currentType.equals(INT) && !currentType.equals(DOUBLE) && !currentType.equals(FLOAT)) {
							throw new VariableException("Can't do math operations with type " + currentType);
						}
					} else {
						currentType = null;

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

	}

	private static boolean isMathOperator(String op) {
		for(String s : mathOperator) {
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
				String varCurrentType = (String) context.getType(name);
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
				if(type.equals(DOUBLE) || type.equals(FLOAT)) {
					if (!currentType.equals(type) && !currentType.equals(INT)) {
						throw new VariableException("Expected " + type +" and expression type is " + currentType);
					}
				}
				context.setType(name, currentType);
			} else if(type.equals(valueType)) {
				context.setType(name, type);
			} else if(valueType.equals(STRING_LIT)) {
				if(!type.substring(0, 5).equals(valueType.substring(0, 5))) {
					throw new TypeException("Expecting " + type + " and got " + valueType);
				} else {
					context.setType(name, type);
				}
			} else if(valueType.equals(NUMBER)) {
				String value = (String) statementValue.get(VALUE);
				if(value.contains(".")) {
					if(!type.equals(DOUBLE) && !type.equals(FLOAT)) {
						throw new VariableException("Expecting " + type + " and got " + DOUBLE);
					} else {
						context.setType(name, type);
					}
				} else {
					context.setType(name, type);
				}
			} else if(valueType.equals(BOOLEAN)) {
				if(!type.equals(valueType)) {
					throw new VariableException("Expecting " + type + " and got " + valueType);
				}
			} else {
				if(valueType.equals(VARIABLE)) {
					String varName = (String) statementValue.get("Value");
					if(context.hasVar(varName)) {
						String varType = (String) context.getType(varName);
						if((type.equals(STRING_LIT) || varType.equals(STRING_LIT))) {
							if(!type.substring(0, 5).equals(varType.substring(0, 5))) {
								throw new TypeException("Expecting " + type + " and got " + valueType);
							} else {
								context.setType(name, type);
							}
						} else if(type.equals(varType)) {
							context.setType(name, type);
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

				String type = tree.getChild(0).getClass().getSimpleName().replaceAll("Context$", "");

				if(type.equals(TERMINAL_NODE_IMPL)) {
					Token token = ((TerminalNodeImpl) tree.getChild(1)).getSymbol();
					if(token.getType() == VARIABLE_TYPE) {
						nested.put("returnType", "Variable");
					} else if(token.getType() == BOOLEAN_TYPE || token.getType() == BOOLEAN_TYPE + 1) {
						nested.put("returnType", BOOLEAN);
					}
				} else {
					nested.put("returnType", type);
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
						if(!(tree.getChild(2).getChild(j) instanceof TerminalNodeImpl)) {
							Map<String, String> p = new LinkedHashMap<>();
							String nameChild = tree.getChild(2).getChild(j).getClass().getSimpleName().replaceAll("Context$", "");
							String value = tree.getChild(2).getChild(j).getText();
							p.put("type", nameChild);
							p.put("value", value);
							parameters.add(p);
						}						
					}

				}

				values.put("parameters", parameters);
				function.add(values);
				map.put(FUNCTION_CALL, function);
			} else if(name.equals(EXPRESSION) && !(tree.getChild(0).getClass().getSimpleName().replaceAll("Context$", "").equals(FUNCTION_CALL) )) {
				map.put("value", tree.getChild(0).getText());
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
					for(int j = 0; j < tree.getChild(index).getChildCount(); j++) {
						String nameChildChild = tree.getChild(index).getChild(j).getClass().getSimpleName().replaceAll("Context$", "");
						if(nameChildChild.equals(POSITION)) {
							traverse(tree.getChild(index), expr_map);
						} else {
							expr_map.put(namedChild, tree.getChild(index).getChild(j).getText());
						}
					}
					expr_map.put("Variable", varName);
					children.add(expr_map);
					map.put(name, expr_map);
				}
			} else if(name.equals(POS)){
				map.put(name, tree.getChild(0).getText());
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
		if(!context.hasFunction(name)) {
			String returnType = (String) vnameType.get(0).get("type");
			List<Map<String, Object>> argsDef = (List<Map<String, Object>>) function.get(1).get(ARGS_DEF);
			List<String> argsType = new ArrayList<>();
			List<String> argsName = new ArrayList<>();
			if(argsDef != null) {
				for(Map<String, Object> arg : argsDef) {
					List<Map<String, Object>> argNameType = (List<Map<String, Object>>) arg.get(VNAME_TYPE);
					String argType = (String) argNameType.get(0).get("type");
					argsType.add(argType);
					String argName = (String) argNameType.get(0).get("name");
					argsName.add(argName);
				}
			}
			context.setFunction(name, new Pair<String, List<String>>(returnType, argsType));

			if(type.equals(FUNCTION)) {
				context.setCurrentFunction(name);
				context.enterScope();
				verify(context, p);
				context.exitScope();
				context.setCurrentFunction(null);
			}	
		} else {
			throw new FunctionAlreadyExistsException("Function with name " + name + " already exists");
		}
	}
}
