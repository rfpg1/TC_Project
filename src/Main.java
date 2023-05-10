import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import exception.BooleanException;
import exception.CompilerException;
import exception.FunctionException;
import exception.TypeException;
import exception.VariableException;
import utils.Pair;

public class Main {

	private static final String PROG = "Prog";
	private static final String NAME = "name";
	private static final String TYPE = "type";
	private static final String TERMINAL_NODE_IMPL = "TerminalNodeImpl";
	private static final String COMMENT = "Comment";
	private static final String DEFINITION = "Definition";
	private static final String FUNCTION = "Function";
	private static final String STATEMENT = "Statement";
	private static final String IF_STATEMENT = "If_statement";
	private static final String ELSE_STATEMENT = "Else_statement";
	private static final String RETURN_STATEMENT = "Return_statement";
	private static final String OPERATOR = "Operator";
	private static final String BOOLEAN_EXPRESSION = "Boolean_expression";
	private static final String WHILE_STATEMENT = "While_statement";
	private static final String DECLARATION = "Declaration";
	private static final String VALUE = "Value";
	private static final String STRING_LIT = "String_lit";
	private static final String EXPR = "Expr";
	private static final String EXPRESSION_VALUE = "ExprValue";
	private static final String FUNCTION_CALL = "Function_call";
	private static final String EXPRESSION = "Expression";
	private static final String ARRAYS = "Arrays";
	private static final String POSITION = "Position";
	private static final String VARIABLE = "Variable";
	private static final String NUMBER = "Number";
	private static final String INT = "Int";
	private static final String DOUBLE = "Double";
	private static final String STRING = "String";
	private static final String BOOLEAN = "Boolean";
	private static final String FLOAT = "Float";
	private static final String VOID = "Void";
	private static final String VALUE_TYPE = "valueType";
	private static final String IS_ARRAY = "isArray";
	private static final String RETURN_TYPE = "returnType";
	private static final String PARAMETERS = "Parameters";
	private static final String REFINEMENT = "Refinement";
	private static final String NOT = "Not";
	private static final String BOOLEAN_VALUE = "BooleanValue";
	private static final String POSITION_VALUE = "PositionValue";
	private static final String MATH = "Math";
	private static final String VALUE_FUNC = "ValueFunc";



	private static final int VARIABLE_TYPE = 36;
	private static final int BOOLEAN_TYPE = 8;
	private static final int NUMBER_INT_TYPE = 28;
	private static final int NUMBER_DOUBLE_TYPE = 29;
	private static final String[] MATH_OPERATOR = {"*", "+", "-", "/", "%", "<", ">", ">=", "<=", "=="};
	private static final String[] BOOLEAN_OPERATOR = {"&&", "||"};


	@SuppressWarnings("unchecked")
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
					Map<String, Object> tree = toJson(p, new LinkedHashMap<>());

					if(treeFlag) {
						System.out.println(tree);
					}
					Context c = new Context();
					addFunctions(c, tree);
					verify(c, (List<Map<String, Object>>) tree.get(STATEMENT));
				} catch(IOException e) {
					System.out.println("File " + args[i] + " does not exist");
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static Map<String, Object> toJson(ParseTree p, Map<String, Object> map) {
		String name = getClassName(p);

		List<Map<String, Object>> children = new ArrayList<>();
		if(!name.equals(DEFINITION) && !name.equals(PROG)) {
			String parent = getClassName(p.getParent());
			if(parent.equals(PROG) || parent.equals(DEFINITION)) {
				if(map.get(STATEMENT) != null) {
					if(!name.equals(STATEMENT)) {
						List<Map<String, Object>> statements = (List<Map<String, Object>>) map.get(STATEMENT);
						Optional<Integer> index = getIndexKey(statements, FUNCTION);
						if(index.isEmpty()) {
							Map<String, Object> functions = new LinkedHashMap<>();
							statements.add(functions);
							functions.put(name, children);
						}
					}					
				} else {
					if(name.equals(FUNCTION) || name.equals(DECLARATION)) {
						map.put(STATEMENT, children);
					} else {
						map.put(name, children);
					}
				}
			} else if(name.equals(COMMENT)) {
				List<Map<String, Object>> parentList = (List<Map<String, Object>>) map.get(parent);
				Optional<Integer> opt = getIndexKey(parentList, COMMENT);
				if(opt.isEmpty()) {
					Map<String, Object> comment = new LinkedHashMap<>();
					parentList.add(comment);
					comment.put(name, children);
				}
			}
		}
		if(name.equals(ELSE_STATEMENT)) {
			List<Map<String, Object>> statements = (List<Map<String, Object>>)map.get(STATEMENT);
			Map<String, Object> elseStatement = new LinkedHashMap<>();
			statements.add(elseStatement);
			List<Map<String, Object>> futureStatements = new ArrayList<>();
			elseStatement.put(ELSE_STATEMENT, futureStatements);
			Map<String, Object> mapFuturestatements = new LinkedHashMap<>();
			futureStatements.add(mapFuturestatements);
			nextStatements(mapFuturestatements, p);
		} else if(name.equals(DECLARATION)) {
			Optional<Integer> index = getIndexKey((List<Map<String, Object>>)map.get(STATEMENT), DECLARATION);
			if(index.isPresent()) {
				List<Map<String, Object>> l =(List<Map<String, Object>>) ((List<Map<String, Object>>)map.get(STATEMENT)).get(index.get()).get(DECLARATION);
				Map<String, Object> value = new LinkedHashMap<>();
				l.add(value);
				updateNameType(p, value);
			} else {
				List<Map<String, Object>> statements = (List<Map<String, Object>>)map.get(STATEMENT);
				List<Map<String, Object>> declarations = new ArrayList<>();
				Map<String, Object> declaration = new LinkedHashMap<>();
				statements.add(declaration);
				declaration.put(DECLARATION, declarations);
				Map<String, Object> value = new LinkedHashMap<>();
				declarations.add(value);
				updateNameType(p, value);
			}

		} else if(name.equals(IF_STATEMENT) || name.equals(WHILE_STATEMENT)) {
			booleanMethod(p, map, name);
		} else if(name.equals(COMMENT)) {
			Map<String, Object> m = new LinkedHashMap<>();
			noLoopsMethod(name, p, m);

			String parent = getClassName(p.getParent());
			if(parent.equals(PROG)) {
				List<String> c = (List<String>) map.get(COMMENT);
				c.add((String)m.get(COMMENT));
			} else {
				List<Map<String, Object>> c = (List<Map<String, Object>>) map.get(parent);
				for(Map<String, Object> child : c) {
					if(child.containsKey(COMMENT)) {
						List<String> comments = (List<String>) child.get(COMMENT);
						comments.add((String) m.get(COMMENT));
					}
				}
			}

		} else if(name.equals(FUNCTION)) {
			Optional<Integer> index = getIndexKey((List<Map<String, Object>>)map.get(STATEMENT), FUNCTION);
			List<Map<String, Object>> l;
			if(index.isEmpty()) {
				List<Map<String, Object>> functions = new ArrayList<>();
				Map<String, Object> function = new LinkedHashMap<>();
				function.put(FUNCTION, functions);
				((List<Map<String, Object>>)map.get(STATEMENT)).add(function);
				l = functions;
			} else {
				l =(List<Map<String, Object>>) ((List<Map<String, Object>>)map.get(STATEMENT)).get(index.get()).get(FUNCTION);
			}
			Map<String, Object> value = new LinkedHashMap<>();
			l.add(value);
			functionMethod(p, value);
		} else if(name.equals(VALUE)) {
			List<Map<String, Object>> l = (List<Map<String, Object>>) map.get(STATEMENT);
			Map<String, Object> value = new LinkedHashMap<>();
			l.add(value);
			noLoopsMethod(name, p, value);
		} else if(name.equals(EXPR) || name.equals(BOOLEAN_EXPRESSION) || name.equals(POSITION)) { 
			noLoopsMethod(name, p, map);
		} else {
			for(int i = 0; i < p.getChildCount(); i++) {
				String childName = getClassName(p.getChild(i));
				if(childName.equals(COMMENT)) {
					toJson(p.getChild(i), map);
				} else if(childName.equals(STATEMENT) || childName.equals(DEFINITION)) {
					toJson(p.getChild(i), map);
				} else if(childName.equals(FUNCTION)) {
					toJson(p.getChild(i), map);
				} else if(childName.equals(VALUE)) {
					toJson(p.getChild(i), map);
				} else if(childName.equals(IF_STATEMENT)) {
					toJson(p.getChild(i), map);
				} else if(childName.equals(DECLARATION)) {
					toJson(p.getChild(i), map);
				} else if(childName.equals(ELSE_STATEMENT)) {
					toJson(p.getChild(i), map);
				} else if(childName.equals(WHILE_STATEMENT)) {
					toJson(p.getChild(i), map);
				} else if(childName.equals(EXPRESSION)) {
					if(getClassName(p.getChild(i).getChild(0)).equals(FUNCTION_CALL)) {
						functionCall(p.getChild(i).getChild(0), map);
					}
				} else if(childName.equals(ARRAYS)) {
					updateArrays(p.getChild(i), map);
				}
			}
		}

		return map;
	}

	@SuppressWarnings("unchecked")
	private static void updateArrays(ParseTree child, Map<String, Object> map) {
		List<Map<String, Object>> statements = (List<Map<String, Object>>) map.get(STATEMENT);
		Optional<Integer> index = getIndexKey(statements, ARRAYS);
		Map<String, Object> array;
		if(index.isPresent()) {
			List<Map<String, Object>> arrays = (List<Map<String, Object>>) statements.get(index.get()).get(ARRAYS);
			array = new LinkedHashMap<>();
			array.put(VARIABLE, child.getChild(0).getText());
			arrays.add(array);
		} else {
			Map<String, Object> arrays = new LinkedHashMap<>();
			statements.add(arrays);
			List<Map<String, Object>> arraysList = new ArrayList<>();
			arrays.put(ARRAYS, arraysList);
			array = new LinkedHashMap<>();
			arraysList.add(array);
			array.put(VARIABLE, child.getChild(0).getText());
		}

		if(child.getChildCount() > 5) {
			insertTypeIntoMap(array, child.getChild(6));
		} else {
			insertTypeIntoMap(array, child.getChild(2));
		}

	}

	@SuppressWarnings("unchecked")
	private static void functionCall(ParseTree child, Map<String, Object> map) {
		List<Map<String, Object>> statements = (List<Map<String, Object>>) map.get(STATEMENT);
		Optional<Integer> index = getIndexKey(statements, FUNCTION_CALL);
		if(index.isPresent()) {
			List<Map<String, Object>> functionCall = (List<Map<String, Object>>) statements.get(index.get()).get(FUNCTION_CALL);
			Map<String, Object> fCall = new LinkedHashMap<>();
			functionCall.add(fCall);
			fCall.put(VARIABLE, child.getChild(0).getText());
			updateArgsValue(child.getChild(2), fCall);
		} else {
			Map<String, Object> f = new LinkedHashMap<>();
			statements.add(f);
			List<Map<String, Object>> functionCalls = new ArrayList<>();
			f.put(FUNCTION_CALL, functionCalls);
			Map<String, Object> functionStuff = new LinkedHashMap<>();
			functionCalls.add(functionStuff);
			functionStuff.put(VARIABLE, child.getChild(0).getText());
			updateArgsValue(child.getChild(2), functionStuff);
		}
	}

	private static void updateArgsValue(ParseTree child, Map<String, Object> map) {
		List<Map<String, Object>> params = new ArrayList<>();
		map.put(PARAMETERS, params);
		for(int i = 0; i < child.getChildCount(); i++) {
			Map<String, Object> param = new LinkedHashMap<>();
			params.add(param);
			String childName = getClassName(child.getChild(i));
			if(childName.equals(FUNCTION_CALL)) {
				paramFunctionCall(param, child.getChild(i));
			} else {
				insertTypeIntoMap(param, child.getChild(i));
				if(param.isEmpty()) {
					params.remove(param);
				}
			}
		}
	}

	private static void paramFunctionCall(Map<String, Object> param, ParseTree child) {
		List<Map<String, Object>> params = new ArrayList<>();
		param.put(FUNCTION, params);
		Map<String, Object> functionCallStuff = new LinkedHashMap<>();
		params.add(functionCallStuff);
		functionCallStuff.put(VARIABLE, child.getChild(0).getText());
		updateArgsValue(child.getChild(2), functionCallStuff);
	}

	private static Optional<Integer> getIndexKey(List<Map<String, Object>> parentList, String key) {
		int index = 0;
		for(Map<String, Object> p : parentList) {
			if(p.containsKey(key)) {
				return Optional.of(index);
			}
			index++;
		}
		return Optional.empty();
	}

	private static void functionMethod(ParseTree p, Map<String, Object> map) {
		updateNameType(p, map);
		nextStatements(map, p);
	}

	private static void updateNameType(ParseTree p, Map<String, Object> map) {
		String name = p.getChild(0).getChild(0).getText();

		map.put(NAME, name);
		List<Map<String, Object>> rType = new ArrayList<>();
		map.put(RETURN_TYPE, rType);
		Map<String, Object> rMap = new LinkedHashMap<>();
		rType.add(rMap);
		rMap.put(TYPE, p.getChild(0).getChild(2).getChild(0).getText());
		if(p.getChild(0).getChild(2).getChildCount() > 1) {
			rMap.put(IS_ARRAY, true);
		} else {
			rMap.put(IS_ARRAY, false);
		}
		if(p.getChild(0).getChildCount() > 3) {
			updateRefinement(p, map, 0);
		}
		updateArgs(p.getChild(2), map);
	};

	private static void updateArgs(ParseTree child, Map<String, Object> map) {
		List<Map<String, Object>> params = new ArrayList<>();
		map.put(PARAMETERS, params);
		for(int i = 0; i < child.getChildCount(); i++) {
			if(!(child.getChild(i) instanceof TerminalNodeImpl)) {
				Map<String, Object> param = new LinkedHashMap<>();
				params.add(param);
				param.put(NAME, child.getChild(i).getChild(0).getText());
				param.put(TYPE, child.getChild(i).getChild(2).getChild(0).getText());
				if(child.getChild(i).getChild(2).getChildCount() > 1) {
					param.put(IS_ARRAY, true);
				} else {
					param.put(IS_ARRAY, false);
				}
				if(child.getChild(i).getChildCount() > 3) {
					updateRefinement(child, param, i);
				}
			}		
		}
	}

	private static void updateRefinement(ParseTree child, Map<String, Object> map, int index) {
		List<Map<String, Object>> refinements = new ArrayList<>();
		map.put(REFINEMENT, refinements);
		Map<String, Object> refinement = new LinkedHashMap<>();
		refinements.add(refinement);
		refinement.put(VARIABLE, child.getChild(index).getChild(3).getChild(1).getText());
		refinement.put(OPERATOR, child.getChild(index).getChild(3).getChild(2).getChild(0).getText());
		insertTypeIntoMap(refinement, child.getChild(index).getChild(3).getChild(3));
	}

	private static void insertTypeIntoMap(Map<String, Object> map, ParseTree child) {
		String name = getClassName(child);
		if(name.equals(NUMBER)) {
			Token token = ((TerminalNodeImpl)(child.getChild(0))).getSymbol();
			updateMap(token, map);
			map.put(VALUE, child.getChild(0).getText());
		} else if(name.equals(TERMINAL_NODE_IMPL)) {
			Token token = ((TerminalNodeImpl) child).getSymbol();
			boolean b = updateMap(token, map);
			if(b)
				map.put(VALUE, child.getText());
		} else if(name.equals(STRING_LIT)) {
			StringBuilder bob = new StringBuilder();
			for(int j = 0; j < child.getChildCount(); j++) {
				bob.append(child.getChild(j).getText());
			}
			map.put(VALUE_TYPE, STRING);
			map.put(VALUE, bob.toString());
		} else if(name.equals(EXPR)) {
			map.put(VALUE_TYPE, EXPR);
			List<Map<String, Object>> exprValues = new ArrayList<>();
			map.put(VALUE, exprValues);
			Map<String, Object> expr = new LinkedHashMap<>();
			exprValues.add(expr);
			expr.put(EXPRESSION_VALUE, child.getChild(0).getChild(0).getText());
			String typeExprClass = getClassName(child.getChild(0).getChild(0));
			if(typeExprClass.equals(TERMINAL_NODE_IMPL)) {
				Token token = ((TerminalNodeImpl) child.getChild(0).getChild(0)).getSymbol();
				updateMap(token, expr);
			} else if(typeExprClass.equals(NUMBER)) {
				Token token = ((TerminalNodeImpl) child.getChild(0).getChild(0).getChild(0)).getSymbol();
				updateMap(token, expr);
			}
			if(child.getChildCount() > 1) {
				expr.put(OPERATOR, child.getChild(1).getText());
				expr.put(VALUE, new ArrayList<Map<String, Object>>());
				toJson(child.getChild(2), expr);
			}

		} else if(name.equals(BOOLEAN_EXPRESSION)) {
			String childName = getClassName(child.getChild(0));
			if(childName.equals(TERMINAL_NODE_IMPL)) {
				toJson(child.getChild(1), map);
			} else {
				map.put(BOOLEAN_VALUE, child.getChild(0).getChild(0).getText());
				insertTypeIntoMap(map, child.getChild(0).getChild(0));
				if(child.getChildCount() > 2) {
					List<Map<String, Object>> exprValues = new ArrayList<>();
					map.put(OPERATOR, child.getChild(1).getText());
					map.put(VALUE, exprValues);
					Map<String, Object> expr = new LinkedHashMap<>();
					exprValues.add(expr);
					expr.put(VALUE, new ArrayList<Map<String, Object>>());
					toJson(child.getChild(2), expr);

				}
			}
		} else if(name.equals(POSITION)) {
			map.put(VALUE_TYPE, POSITION);
			List<Map<String, Object>> exprValues = new ArrayList<>();
			map.put(VALUE, exprValues);
			Map<String, Object> expr = new LinkedHashMap<>();
			exprValues.add(expr);
			expr.put(POSITION_VALUE, child.getChild(0).getChild(0).getText());
			String typeExprClass = getClassName(child.getChild(0).getChild(0));
			if(typeExprClass.equals(TERMINAL_NODE_IMPL)) {
				Token token = ((TerminalNodeImpl) child.getChild(0).getChild(0)).getSymbol();
				updateMap(token, expr);
			} else if(typeExprClass.equals(NUMBER)) {
				Token token = ((TerminalNodeImpl) child.getChild(0).getChild(0).getChild(0)).getSymbol();
				updateMap(token, expr);
			}
			if(child.getChildCount() > 1) {
				expr.put(OPERATOR, child.getChild(1).getText());
				expr.put(VALUE, new ArrayList<Map<String, Object>>());
				toJson(child.getChild(2), expr);
			}
		} else if(name.equals(FUNCTION_CALL)) {
			map.put(VALUE_TYPE, FUNCTION_CALL);
			List<Map<String, Object>> exprValues = new ArrayList<>();
			map.put(VALUE_FUNC, exprValues);
			Map<String, Object> expr = new LinkedHashMap<>();
			exprValues.add(expr);
			List<Map<String, Object>> funcList = new ArrayList<>();
			Map<String, Object> funcMap = new LinkedHashMap<>();
			funcList.add(funcMap);
			expr.put(FUNCTION, funcList);
			funcMap.put(VARIABLE, child.getChild(0).getText());
			updateArgsValue(child.getChild(2), funcMap);
		}
	}

	@SuppressWarnings("unchecked")
	private static void noLoopsMethod(String name, ParseTree p, Map<String, Object> map) {
		if(name.equals(COMMENT)) {
			StringBuilder bob = new StringBuilder();
			for(int j = 0; j < p.getChildCount(); j++) {
				bob.append(p.getChild(j).getText());
			}
			map.put(COMMENT, bob.toString());

		} else if(name.equals(VALUE)) {
			String variableName = p.getChild(0).getChild(0).getText();
			String variableType;
			List<Map<String, Object>> values = new ArrayList<>();
			map.put(VARIABLE, values);
			Map<String, Object> variableValues = new LinkedHashMap<>();
			if(p.getChild(0).getChildCount() > 1) {
				variableType = p.getChild(0).getChild(2).getChild(0).getText();
				if(p.getChild(0).getChild(2).getChildCount() > 1) {
					variableValues.put(IS_ARRAY, true);
				} else {
					variableValues.put(IS_ARRAY, false);
				}
			} else {
				variableType = null;
			}

			values.add(variableValues);
			variableValues.put(NAME, variableName);
			variableValues.put(TYPE, variableType);

			insertTypeIntoMap(variableValues, p.getChild(2));

		} else if(name.equals(EXPR) || name.equals(BOOLEAN_EXPRESSION) || name.equals(POSITION)) {
			Map<String, Object> valueExpr = new LinkedHashMap<>();
			List<Map<String, Object>> l = ((List<Map<String,Object>>)map.get(VALUE));
			if(l!= null) {
				l.add(valueExpr);
				insertTypeIntoMap(valueExpr, p);
			} else {
				insertTypeIntoMap(map, p);
			}
		}
	}

	private static String getClassName(ParseTree child) {
		return child.getClass().getSimpleName().replaceAll("Context$", "");
	}

	private static boolean updateMap(Token token, Map<String, Object> variableValues) {
		if(token.getType() == VARIABLE_TYPE) {
			variableValues.put(VALUE_TYPE, VARIABLE);
			return true;
		} else if(token.getType() == BOOLEAN_TYPE || token.getType() == BOOLEAN_TYPE + 1) {
			variableValues.put(VALUE_TYPE, BOOLEAN);
			return true;
		}else if(token.getType() == NUMBER_INT_TYPE) {
			variableValues.put(VALUE_TYPE, INT);
			return true;
		} else if(token.getType() == NUMBER_DOUBLE_TYPE) {
			variableValues.put(VALUE_TYPE, DOUBLE);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private static void booleanMethod(ParseTree p, Map<String, Object> map, String key) {
		List<Map<String, Object>> statements = (List<Map<String, Object>>) map.get(STATEMENT);
		List<Map<String, Object>> ifStatements = new ArrayList<>();
		Map<String, Object> ifs = new LinkedHashMap<>();
		ifs.put(key, ifStatements);
		statements.add(ifs);
		Map<String, Object> actualIfStatement = new LinkedHashMap<>();
		ifStatements.add(actualIfStatement);
		String notName = getClassName(p.getChild(1));
		if(notName.equals(TERMINAL_NODE_IMPL)) {
			actualIfStatement.put(NOT, true);
			updateMapBooleanExpression(actualIfStatement, p.getChild(2));

		} else {
			actualIfStatement.put(NOT, false);
			updateMapBooleanExpression(actualIfStatement, p.getChild(1));
		}

		nextStatements(actualIfStatement, p);
	}

	private static void nextStatements(Map<String, Object> map, ParseTree p) {
		List<Map<String, Object>> states = new ArrayList<>();
		map.put(STATEMENT, states);
		for(int i = 0; i < p.getChildCount(); i++) {
			String childName = getClassName(p.getChild(i));
			if(childName.equals(DEFINITION) || childName.equals(STATEMENT) 
					|| childName.equals(ELSE_STATEMENT)) {
				toJson(p.getChild(i), map);
			} else if(childName.equals(RETURN_STATEMENT)) {
				List<Map<String, Object>> l = new ArrayList<>();
				map.put(RETURN_STATEMENT, l);
				Map<String, Object> m = new LinkedHashMap<>();
				l.add(m);
				insertTypeIntoMap(m, p.getChild(i).getChild(1));
			}
		}
	}

	private static void updateMapBooleanExpression(Map<String, Object> map, ParseTree p) {
		insertTypeIntoMap(map, p);
	}

	@SuppressWarnings("unchecked")
	private static void addFunctions(Context context, Map<String, Object> map) throws CompilerException {
		if(map.get(STATEMENT) != null) {
			for(Map<String, Object> m : (List<Map<String, Object>>)map.get(STATEMENT)) {
				List<Map<String, Object>> func = (List<Map<String, Object>>)m.get(FUNCTION);
				if(func != null) {
					for(Map<String, Object> function : func) {
						String name = (String)function.get(NAME);
						Map<String, Object> returnType = ((List<Map<String, Object>>) function.get(RETURN_TYPE)).get(0);
						List<Map<String, Object>> params = (List<Map<String, Object>>)function.get(PARAMETERS);
						List<Map<String, Object>> paramsType = new ArrayList<>();
						for(Map<String, Object> param : params) {
							String type = (String) param.get(TYPE);
							Map<String, Object> paramTypeArray = new LinkedHashMap<>();
							paramTypeArray.put(TYPE, type);
							paramTypeArray.put(IS_ARRAY, (boolean)param.get(IS_ARRAY));
							paramsType.add(paramTypeArray);
						}
						if(context.hasFunction(name)) {
							//TODO: Instead of checking name check also parameters
							throw new FunctionException("Function with name: " + name + " already exists");
						}
						context.setFunction(name, new Pair<>(paramsType, returnType));
						addFunctions(context, function);
					}
				} else {
					List<Map<String, Object>> decl = (List<Map<String, Object>>)m.get(DECLARATION);
					if(decl != null) {
						for(Map<String, Object> declaration : decl) {
							String name = (String)declaration.get(NAME);
							Map<String, Object> returnType = ((List<Map<String, Object>>) declaration.get(RETURN_TYPE)).get(0);
							List<Map<String, Object>> params = (List<Map<String, Object>>)declaration.get(PARAMETERS);
							List<Map<String, Object>> paramsType = new ArrayList<>();
							for(Map<String, Object> param : params) {
								String type = (String) param.get(TYPE);
								Map<String, Object> paramTypeArray = new LinkedHashMap<>();
								paramTypeArray.put(TYPE, type);
								paramTypeArray.put(IS_ARRAY, (boolean)param.get(IS_ARRAY));
								paramsType.add(paramTypeArray);
							}
							if(context.hasFunction(name)) {
								//TODO: Instead of checking name check also parameters
								throw new FunctionException("Function with name: " + name + " already exists");
							}
							context.setFunction(name, new Pair<>(paramsType, returnType));
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static void verify(Context context, List<Map<String, Object>> statements) throws CompilerException {
		if(statements != null) {
			for(Map<String, Object> statement : statements) {
				for(String key : statement.keySet()) {
					if(key.equals(VARIABLE)) {
						checkVariable(statement, key, context);
					} else if(key.equals(FUNCTION)) {
						List<Map<String, Object>> fs = (List<Map<String, Object>>) statement.get(key);
						for(Map<String, Object> f : fs) {
							context.enterScope();
							checkFunction(f, key, context);
							List<Map<String, Object>> returnList = (List<Map<String, Object>>)f.get(RETURN_STATEMENT);
							if(returnList != null) {
								checkReturnStatement(context, returnList.get(0));
							}
							context.setCurrentFunction(null);
							context.exitScope();
						}
					} else if(key.equals(FUNCTION_CALL)) {
						List<Map<String, Object>> funcCalls = (List<Map<String, Object>>) statement.get(key);
						for(Map<String, Object> call : funcCalls) {
							List<Map<String, Object>> function = new ArrayList<>();
							Map<String, Object> f = new LinkedHashMap<>();
							List<Map<String, Object>> f1 = new ArrayList<>();
							f1.add(call);
							f.put(FUNCTION, f1);
							function.add(f);
							checkParams(context, function, null);
						}
					} else if(key.equals(IF_STATEMENT) || key.equals(WHILE_STATEMENT)) {
						Map<String, Object> ifStatement = ((List<Map<String, Object>>) statement.get(key)).get(0);
						checkBooleanExpression(ifStatement, context, null);
						List<Map<String, Object>> states = (List<Map<String, Object>>) ifStatement.get(STATEMENT);
						context.enterScope();
						verify(context, states);
						List<Map<String, Object>> returnList = (List<Map<String, Object>>)ifStatement.get(RETURN_STATEMENT);
						if(returnList != null) {
							checkReturnStatement(context, returnList.get(0));
						}
						context.exitScope();
					} else if(key.equals(ELSE_STATEMENT)) {
						Map<String, Object> elseStatement = ((List<Map<String, Object>>) statement.get(key)).get(0);
						List<Map<String, Object>> states = (List<Map<String, Object>>) elseStatement.get(STATEMENT);
						context.enterScope();
						verify(context, states);
						context.exitScope();
					} else if(key.equals(ARRAYS)) {
						List<Map<String, Object>> arrays = (List<Map<String, Object>>) statement.get(key);
						for(Map<String, Object> array : arrays) {
							checkValidArray(array, context);
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static void checkValidArray(Map<String, Object> array, Context context) throws CompilerException {
		String varName = (String) array.get(VARIABLE);
		if(!context.hasVar(varName)) {
			throw new VariableException("Variable: " + varName + " doesn't exist");
		}
		boolean isArray = ((Pair<Object, Boolean>)context.getType(varName)).getSecond();
		if(!isArray) {
			throw new TypeException("Variable: " + varName + " isn't an array");
		}
		List<Map<String, Object>> position = (List<Map<String, Object>>) array.get(VALUE);
		checkPosition(position, context, varName);
	}

	@SuppressWarnings("unchecked")
	private static void checkPosition(List<Map<String, Object>> position, Context context, String varName) throws CompilerException {
		String valueType = (String) position.get(0).get(VALUE_TYPE);
		String positionValue = (String) position.get(0).get(POSITION_VALUE);
		if(valueType.equals(VARIABLE)) {
			Pair<Object, Boolean> pair = (Pair<Object, Boolean>) context.getType(positionValue);
			if(pair == null) {
				throw new VariableException("Variable: " + positionValue + " doesn't exist");
			}
			String varType = (String) pair.getFirst();
			if(!varType.equals(INT)) {
				throw new TypeException("When acessing array: " + varName + " position value: " + positionValue + " must be an integer but it is: " + varType);
			}
		}
		if(position.get(0).size() > 3) {
			List<Map<String, Object>> value = (List<Map<String, Object>>) ((List<Map<String, Object>>) position.get(0).get(VALUE)).get(0).get(VALUE);
			checkPosition(value, context, varName);
		}
	}

	@SuppressWarnings("unchecked")
	private static void checkReturnStatement(Context context, Map<String, Object> map) throws CompilerException {
		String currentFunction = context.getCurrentFunction();
		Pair<List<Map<String, Object>>, Map<String, Object>> pair = (Pair<List<Map<String, Object>>, Map<String, Object>>) context.getFunction(currentFunction);
		boolean isArray = (boolean) pair.getSecond().get(IS_ARRAY);
		String valueType = getValueType(map, null, context, isArray);
		String actualType = (String) pair.getSecond().get(TYPE);
		if(valueType.equals(FUNCTION_CALL)) {
			List<Map<String, Object>> params = (List<Map<String, Object>>) map.get(VALUE_FUNC);
			String funcName = (String) ((List<Map<String, Object>>)params.get(0).get(FUNCTION)).get(0).get(VARIABLE);
			checkParams(context, params, null);
			Map<String, Object> returnStatement = ((Pair<List<Map<String, Object>>, Map<String, Object>>)context.getFunction(funcName)).getSecond();
			valueType = (String) returnStatement.get(TYPE);
		}
		checkType(actualType, valueType, map, context);
		if(!map.isEmpty()) {
			checkArray(isArray, map, context);
		}
	}

	@SuppressWarnings("unchecked")
	private static void checkBooleanExpression(Map<String, Object> ifStatement, Context context, String operator) throws CompilerException {
		String valueType = (String) ifStatement.get(VALUE_TYPE);
		String op = (String) ifStatement.get(OPERATOR);
		if(valueType.equals(VARIABLE)) {
			String varName = (String) ifStatement.get(BOOLEAN_VALUE);
			Pair<String, Object> pair = (Pair<String, Object>) context.getType(varName);
			if(pair == null) {
				throw new VariableException("Variable: " + varName + " doesn't exist");
			} else {
				String vType = (String) pair.getFirst();
				String bType = getType(op);
				if(vType.equals(DOUBLE) || vType.equals(INT) || vType.equals(FLOAT)) {
					if(operator == null) {
						if(op == null) {
							throw new BooleanException("Condition isn't valid");
						}
						if(!bType.equals(MATH)) {
							throw new BooleanException("Condition isn't valid");
						} else {
							operator = bType;
						}
					} else {
						if(bType != null && bType.equals(MATH)) {
							throw new BooleanException("Condition isn't valid");
						} else {
							if(operator.equals(BOOLEAN)) {
								throw new BooleanException("Condition isn't valid");
							}
							operator = null;
						}
					}
				} else if(vType.equals(BOOLEAN)) {
					if(operator == null) {
						if(op != null && (op.equals("==") || op.equals("!="))) {
							operator = BOOLEAN;
						} else if(op != null && bType.equals(MATH)) {
							throw new BooleanException("Condition isn't valid");
						}
					} else if(!operator.equals(BOOLEAN)){
						throw new BooleanException("Condition isn't valid");
					}
				} else {
					throw new TypeException("Type: " + vType + " isn't valid in boolean expressions");
				}
			}
		} else if(valueType.equals(INT) || valueType.equals(DOUBLE) || valueType.equals(FLOAT)) {
			if(op == null) {
				if(operator == null) {
					throw new BooleanException("Condition isn't valid");
				} else {
					if(!operator.equals(MATH)) {
						throw new BooleanException("Condition isn't valid");
					} else {
						operator = null;
					}
				}
			} else {
				String bType = getType(op);
				if(operator == null) {
					if(!bType.equals(MATH)) {
						throw new BooleanException("Condition isn't valid");
					} else {
						operator = getType(op);
					}
				} else {
					if(bType.equals(MATH)) {
						throw new BooleanException("Condition isn't valid");
					} else {
						operator = null;
					}
				}
			}			
		} else if(valueType.equals(EXPR)) {
			checkExpr(context, (String)ifStatement.get(BOOLEAN_VALUE));
			if(op == null) {
				if(operator == null) {
					throw new BooleanException("Condition isn't valid");
				} else {
					operator = null;
				}
			} else {
				String bType = getType(op);
				if(operator == null) {
					if(!bType.equals(MATH)) {
						throw new BooleanException("Condition isn't valid");
					} else {
						operator = bType;
					}
				} else {
					if(bType.equals(MATH)) {
						throw new BooleanException("Condition isn't valid");
					} else {
						operator = null;
					}
				}

			}

		} else if(valueType.equals(BOOLEAN)) {
			if(operator != null) {
				if(!operator.equals(BOOLEAN)) {
					throw new BooleanException("Condition isn't valid");	
				}
				if(op != null) {
					String bType = getType(op);
					if(!bType.equals(BOOLEAN)) {
						throw new BooleanException("Condition isn't valid");	
					}
				}
			} else {
				String bType = getType(op);
				if(op != null && (op.equals("==") || op.equals("!="))) {
					operator = BOOLEAN;
				} else if(op != null && bType.equals(MATH)) {
					throw new BooleanException("Condition isn't valid");
				} else {
					operator = null;
				}
			}
		} else if(valueType.equals(FUNCTION_CALL)) {

			List<Map<String, Object>> params = (List<Map<String, Object>>) ifStatement.get(VALUE_FUNC);
			String funcName = (String) ((List<Map<String, Object>>)params.get(0).get(FUNCTION)).get(0).get(VARIABLE);
			checkParams(context, params, null);
			Map<String, Object> returnStatement = ((Pair<List<Map<String, Object>>, Map<String, Object>>)context.getFunction(funcName)).getSecond();
			boolean isArray = (boolean)returnStatement.get(IS_ARRAY);
			if(isArray) {
				throw new TypeException("Function: " + funcName + " returns an array and they aren't allowed in boolean expressions");
			} else {
				String funcType = (String) returnStatement.get(TYPE);
				if(funcType.equals(INT) || funcType.equals(DOUBLE) || funcType.equals(FLOAT)) {
					if(op == null) {
						if(operator == null) {
							throw new BooleanException("Condition isn't valid");
						} else {
							if(!operator.equals(MATH)) {
								throw new BooleanException("Condition isn't valid");
							} else {
								operator = null;
							}
						}
					} else {
						String bType = getType(op);
						if(operator == null) {
							if(!bType.equals(MATH)) {
								throw new BooleanException("Condition isn't valid");
							} else {
								operator = getType(op);
							}
						} else {
							if(bType.equals(MATH)) {
								throw new BooleanException("Condition isn't valid");
							} else {
								operator = null;
							}
						}
					}			
				} else if(funcType.equals(BOOLEAN)) {
					if(operator != null) {
						if(!operator.equals(BOOLEAN)) {
							throw new BooleanException("Condition isn't valid");	
						}
						if(op != null) {
							String bType = getType(op);
							if(!bType.equals(BOOLEAN)) {
								throw new BooleanException("Condition isn't valid");	
							}
						}
					} else {
						String bType = getType(op);
						if(op != null && (op.equals("==") || op.equals("!="))) {
							operator = BOOLEAN;
						} else if(op != null && bType.equals(MATH)) {
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
		if(ifStatement.get(VALUE) instanceof ArrayList) {
			Map<String, Object> v = ((List<Map<String, Object>>) ifStatement.get(VALUE)).get(0);
			List<Map<String, Object>> l = (List<Map<String, Object>>) v.get(VALUE);
			if(l != null) {
				Map<String, Object> value = l.get(0);
				checkBooleanExpression(value, context, operator);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static void checkExpr(Context context, String expr) throws CompilerException {
		String[] exp = expr.split("\\+|\\*|-|/|%");
		Pattern pattern = Pattern.compile("\\d+", Pattern.CASE_INSENSITIVE);
		for(String s : exp) {
			Matcher matcher = pattern.matcher(s);
			if(!matcher.find()) {
				Pair<Object, Boolean> pair = (Pair<Object, Boolean>) context.getType(s);
				if(pair == null) {
					throw new VariableException("Variable: " + s + " doesn't exist");
				}
				String type = (String) pair.getFirst();
				if(!type.equals(INT) && !type.equals(DOUBLE) && !type.equals(FLOAT)) {
					throw new TypeException("Variable: " + s + " has type: " + type + " and expected is int, double or float");
				}
			}
		}
	}

	private static String getType(String op) {
		List<String> mathOperator = new ArrayList<>(Arrays.asList(MATH_OPERATOR));
		List<String> booleanOperator = new ArrayList<>(Arrays.asList(BOOLEAN_OPERATOR));
		if(mathOperator.contains(op)) {
			return MATH;
		} else if(booleanOperator.contains(op)) {
			return BOOLEAN;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private static void checkFunction(Map<String, Object> func, String key, Context context) throws CompilerException {

		context.setCurrentFunction((String)func.get(NAME));
		for(Map<String, Object> param : (List<Map<String, Object>>) func.get(PARAMETERS)) {
			String name = (String)param.get(NAME);
			if(context.hasVarInCurrentScope(name)) {
				throw new VariableException("Variable: " + name + " already exists in this scope");
			}			
			context.setType(name, (String) param.get(TYPE), (boolean)param.get(IS_ARRAY));
		}
		verify(context, (List<Map<String, Object>>) func.get(STATEMENT));
	}

	@SuppressWarnings("unchecked")
	private static void checkVariable(Map<String, Object> statements, String key, Context context) throws CompilerException {
		Map<String, Object> variable = ((List<Map<String, Object>>) statements.get(key)).get(0);
		String name = (String) variable.get(NAME);
		String type = (String) variable.get(TYPE);
		if(type == null) {
			if(!context.hasVar(name)) {
				throw new VariableException("Variable: " + name + " doesn't exist");
			} else {
				boolean isArray = ((Pair<Object, Boolean>)context.getType(name)).getSecond();
				checkArray(isArray, variable, context);
			}
		} else {
			boolean isArray = (boolean) variable.get(IS_ARRAY);
			if(context.hasVarInCurrentScope(name)) {
				throw new VariableException("Variable: " + name + " already exists");
			} else {
				Object v = variable.get(VALUE);
				if(v == null) {
					v = variable.get(VALUE_FUNC);
				}
				if(v instanceof ArrayList) {
					Map<String, Object> value = ((List<Map<String, Object>>) v).get(0);
					String valueType = getValueType(value, null, context, isArray);
					checkType(type, valueType, variable, context);
					checkArray(isArray, variable, context);
					String variableValueType = (String) variable.get(VALUE_TYPE);
					if(variableValueType.equals(FUNCTION_CALL)) {
						checkParams(context, (List<Map<String, Object>>)variable.get(VALUE_FUNC), name);
					}
					context.setType(name, type, (boolean)variable.get(IS_ARRAY));
				} else {
					String valueType = (String) variable.get(VALUE_TYPE);
					checkType(type, valueType, variable, context);
					checkArray(isArray, variable, context);
					context.setType(name, type, (boolean)variable.get(IS_ARRAY));
				}
			}
		}
	}	

	@SuppressWarnings("unchecked")
	private static void checkParams(Context context, List<Map<String, Object>> list, String varName) throws CompilerException {
		List<Map<String, Object>> function = (List<Map<String, Object>>) list.get(0).get(FUNCTION);
		String funcName = (String) function.get(0).get(VARIABLE);

		List<Map<String, Object>> params = (List<Map<String, Object>>) function.get(0).get(PARAMETERS);
		Pair<List<Map<String, Object>>, Map<String, Boolean>> func = (Pair<List<Map<String, Object>>, Map<String, Boolean>>)context.getFunction(funcName);
		if(func == null) {
			throw new FunctionException("Function: " + funcName +  " doesn't exist");
		}
		List<Map<String, Object>> actualParams = func.getFirst();
		if(params.size() != actualParams.size()) {
			throw new FunctionException("Function: " + funcName + " receives: " + actualParams.size() + " params and not " + params.size());
		}
		for(int i = 0; i < actualParams.size(); i++) {
			Map<String, Object> mapParams = (Map<String, Object>)actualParams.get(i);
			String actualType = (String) mapParams.get(TYPE);
			String paramType = (String) params.get(i).get(VALUE_TYPE);
			if(paramType == null) {
				List<Map<String, Object>> functionCallParams = new ArrayList<>();
				functionCallParams.add(params.get(i));
				checkParams(context, functionCallParams, varName);
				Pair<List<Map<String, Object>>, Map<String, Object>> pair = (Pair<List<Map<String, Object>>, Map<String, Object>>) context.getFunction(funcName);
				String funcReturnType = (String) pair.getSecond().get(TYPE);
				if(varName != null) {
					checkType(actualType, funcReturnType, params.get(i), context);
				}
				boolean funcArray = (boolean) pair.getSecond().get(IS_ARRAY);
				if(funcArray) {
					if(!(boolean) actualParams.get(i).get(IS_ARRAY)) {
						throw new TypeException("Function: " + funcName + " isn't expected to receive an array at parameter: " + i);
					}
				} else {
					if((boolean) actualParams.get(i).get(IS_ARRAY)) {
						throw new TypeException("Function: " + funcName + " is expected to receive an array at parameter: " + i);
					}
				}
			} else {
				if(paramType.equals(VARIABLE)) {
					String value = (String) params.get(i).get(VALUE);
					Pair<Object, Boolean> pair = (Pair<Object, Boolean>) context.getType(value);
					if(pair == null) {
						throw new VariableException("Variable: " + value + " used to call function: " + funcName + " doesn't exist");
					} else {
						boolean actualIsArray = (boolean) mapParams.get(IS_ARRAY);
						checkArray(actualIsArray, params.get(i), context);
					}
				} else {
					boolean actualIsArray = (boolean) mapParams.get(IS_ARRAY);

					if(actualIsArray) {
						String value = (String) params.get(i).get(VALUE);
						throw new VariableException("Value: " + value +" used to call function: " + funcName + " isn't an array and it is expected to be");
					}
				}
				checkType(actualType, paramType, params.get(i), context);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static void checkArray(boolean isArray, Map<String, Object> variable, Context context) throws CompilerException {
		String valueType = (String) variable.get(VALUE_TYPE);
		if(isArray) {
			if(!valueType.equals(VARIABLE) && !valueType.equals(FUNCTION_CALL)) {
				throw new VariableException("Can't assign a value to an array");
			} else {
				if(valueType.equals(VARIABLE)) {
					String varName = (String) variable.get(VALUE);
					boolean o = ((Pair<String, Boolean>) context.getType(varName)).getSecond() ;
					if(!o) {
						throw new TypeException("Can't assign a value to an array");
					}
				} else if(valueType.equals(FUNCTION_CALL)) {
					List<Map<String, Object>> function = (List<Map<String, Object>>) ((List<Map<String, Object>>)variable.get(VALUE)).get(0).get(FUNCTION);
					String funcName = (String) function.get(0).get(VARIABLE);
					Map<String, Object> rValue = ((Pair<List<String>, Map<String, Object>>)context.getFunction(funcName)).getSecond();
					boolean funcArray = (boolean) rValue.get(IS_ARRAY);
					if(!funcArray) {
						throw new TypeException("Can't assign a value to an array");
					}
				}
			}
		} else {
			if(valueType.equals(VARIABLE)) {
				String varName = (String) variable.get(VALUE);
				boolean o = ((Pair<String, Boolean>) context.getType(varName)).getSecond() ;
				if(o) {
					throw new TypeException("Can't assign a value to an array");
				}
			} else if(valueType.equals(FUNCTION_CALL)) {
				List<Map<String, Object>> function = (List<Map<String, Object>>) ((List<Map<String, Object>>)variable.get(VALUE_FUNC)).get(0).get(FUNCTION);
				String funcName = (String) function.get(0).get(VARIABLE);
				Map<String, Object> rValue = ((Pair<List<String>, Map<String, Object>>)context.getFunction(funcName)).getSecond();
				boolean funcArray = (boolean) rValue.get(IS_ARRAY);
				if(funcArray) {
					throw new TypeException("Can't assign a value to an array");
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static String getValueType(Map<String, Object> value, String valueType, Context context, boolean isArray) throws CompilerException {
		if(value.isEmpty()) {
			return VOID;
		}
		if(valueType == null) {
			valueType = (String) value.get(VALUE_TYPE);
		} else {
			if(!valueType.equals(DOUBLE) && !valueType.equals(FLOAT)) {
				valueType = (String) value.get(VALUE_TYPE);
			}
		}
		if(valueType == null) {
			String funcName = (String) ((List<Map<String, Object>>) value.get(FUNCTION)).get(0).get(VARIABLE);
			if(!context.hasFunction(funcName)) {
				throw new FunctionException("Function: " + funcName + " doesn't exist");
			}
			Map<String, Object> pair = ((Pair<List<String>, Map<String, Object>>)context.getFunction(funcName)).getSecond();
			valueType = (String) pair.get(TYPE);
			boolean funcArray = (boolean) pair.get(IS_ARRAY);
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
		if(valueType.equals(VARIABLE)) {
			Pair<Object, Boolean> type =(Pair<Object, Boolean>)context.getType((String)value.get(EXPRESSION_VALUE));
			if(type != null) {
				valueType = (String) type.getFirst();
			} else {
				String varName = (String) value.get(VALUE);
				type =(Pair<Object, Boolean>)context.getType(varName);
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
					valueType = (String) type.getFirst();
				}
			}
		} else if(value.get(EXPRESSION_VALUE) != null &&  value.get(VALUE) != null) {
			Map<String, Object> values = ((List<Map<String, Object>>)((List<Map<String, Object>>)value.get(VALUE)).get(0).get(VALUE)).get(0);
			valueType = getValueType(values, valueType, context, isArray);
		}
		return valueType;
	}

	@SuppressWarnings("unchecked")
	private static void checkType(String type, String valueType, Map<String, Object> map, Context context) throws CompilerException {
		if(!type.equals(valueType)) {
			if(type.equals(VOID)) {
				throw new TypeException("Expecting empty return and return type: " + valueType);
			} else if(valueType.equals(VARIABLE)) {
				String value = (String) map.get(VALUE);
				Pair<String, Boolean> pair = (Pair<String, Boolean>)context.getType(value);
				if(pair == null) {
					throw new VariableException("Variable: " + value + " doesn't exist");
				}
				String varType = pair.getFirst();
				if(varType == null) {
					throw new VariableException("Variable: " + value + " doesn't exist");
				}
				if(!type.equals(varType)) {
					if(type.equals(STRING) || type.equals(INT)) {
						throw new TypeException("Excepting type: " + type + " and got: " + varType);
					} else if(type.equals(DOUBLE)) {
						if(!varType.equals(INT) && !varType.equals(FLOAT)) {
							throw new TypeException("Excepting type: " + type + " and got: " + varType);
						}
					}
				}
			} else if(type.equals(STRING) || type.equals(BOOLEAN)) {
				throw new TypeException("Excepting type: " + type + " and got: " + valueType);
			} else if(type.equals(DOUBLE) || type.equals(FLOAT)) {
				if(valueType.equals(EXPR)) {
					Map<String, Object> exprMap = ((List<Map<String, Object>>) map.get(VALUE)).get(0);
					String exprType = getValueType(exprMap, valueType, context, false);
					checkType(type, exprType, map, context);
				} else if(!valueType.equals(INT) && !valueType.equals(FLOAT)) {
					throw new TypeException("Excepting type: " + type + " and got: " + valueType);
				}
			} else if(type.equals(INT)) {
				if(valueType.equals(EXPR)) {
					Map<String, Object> exprMap = ((List<Map<String, Object>>) map.get(VALUE)).get(0);
					String exprType = getValueType(exprMap, valueType, context, false);
					checkType(type, exprType, map, context);
				} else {
					throw new TypeException("Excepting type: " + type + " and got: " + valueType);	
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
}
