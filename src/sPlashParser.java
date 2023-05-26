import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

public class sPlashParser {

	@SuppressWarnings("unchecked")
	public Map<String, Object> toJson(ParseTree p, Map<String, Object> map) {
		String name = getClassName(p);

		List<Map<String, Object>> children = new ArrayList<>();
		if(!name.equals(Constant.DEFINITION) && !name.equals(Constant.PROG)) {
			String parent = getClassName(p.getParent());
			if(parent.equals(Constant.PROG) || parent.equals(Constant.DEFINITION)) {
				if(map.get(Constant.STATEMENT) != null) {
					if(!name.equals(Constant.STATEMENT)) {
						List<Map<String, Object>> statements = (List<Map<String, Object>>) map.get(Constant.STATEMENT);
						Optional<Integer> index = getIndexKey(statements, Constant.FUNCTION);
						if(index.isEmpty()) {
							Map<String, Object> functions = new LinkedHashMap<>();
							statements.add(functions);
							functions.put(name, children);
						}
					}					
				} else {
					if(name.equals(Constant.FUNCTION) || name.equals(Constant.DECLARATION)) {
						map.put(Constant.STATEMENT, children);
					} else {
						map.put(name, children);
					}
				}
			} else if(name.equals(Constant.COMMENT)) {
				List<Map<String, Object>> parentList = (List<Map<String, Object>>) map.get(parent);
				Optional<Integer> opt = getIndexKey(parentList, Constant.COMMENT);
				if(opt.isEmpty()) {
					Map<String, Object> comment = new LinkedHashMap<>();
					parentList.add(comment);
					comment.put(name, children);
				}
			}
		}
		if(name.equals(Constant.ELSE_STATEMENT)) {
			List<Map<String, Object>> statements = (List<Map<String, Object>>)map.get(Constant.STATEMENT);
			Map<String, Object> elseStatement = new LinkedHashMap<>();
			statements.add(elseStatement);
			List<Map<String, Object>> futureStatements = new ArrayList<>();
			elseStatement.put(Constant.ELSE_STATEMENT, futureStatements);
			Map<String, Object> mapFuturestatements = new LinkedHashMap<>();
			futureStatements.add(mapFuturestatements);
			nextStatements(mapFuturestatements, p);
		} else if(name.equals(Constant.DECLARATION)) {
			Optional<Integer> index = getIndexKey((List<Map<String, Object>>)map.get(Constant.STATEMENT), Constant.DECLARATION);
			if(index.isPresent()) {
				List<Map<String, Object>> l =(List<Map<String, Object>>) ((List<Map<String, Object>>)map.get(Constant.STATEMENT)).get(index.get()).get(Constant.DECLARATION);
				Map<String, Object> value = new LinkedHashMap<>();
				l.add(value);
				updateNameType(p, value);
			} else {
				List<Map<String, Object>> statements = (List<Map<String, Object>>)map.get(Constant.STATEMENT);
				List<Map<String, Object>> declarations = new ArrayList<>();
				Map<String, Object> declaration = new LinkedHashMap<>();
				statements.add(declaration);
				declaration.put(Constant.DECLARATION, declarations);
				Map<String, Object> value = new LinkedHashMap<>();
				declarations.add(value);
				updateNameType(p, value);
			}

		} else if(name.equals(Constant.IF_STATEMENT) || name.equals(Constant.WHILE_STATEMENT)) {
			booleanMethod(p, map, name);
		} else if(name.equals(Constant.COMMENT)) {
			Map<String, Object> m = new LinkedHashMap<>();
			noLoopsMethod(name, p, m);

			String parent = getClassName(p.getParent());
			if(parent.equals(Constant.PROG)) {
				List<String> c = (List<String>) map.get(Constant.COMMENT);
				c.add((String)m.get(Constant.COMMENT));
			} else {
				List<Map<String, Object>> c = (List<Map<String, Object>>) map.get(parent);
				for(Map<String, Object> child : c) {
					if(child.containsKey(Constant.COMMENT)) {
						List<String> comments = (List<String>) child.get(Constant.COMMENT);
						comments.add((String) m.get(Constant.COMMENT));
					}
				}
			}

		} else if(name.equals(Constant.FUNCTION)) {
			Optional<Integer> index = getIndexKey((List<Map<String, Object>>)map.get(Constant.STATEMENT), Constant.FUNCTION);
			List<Map<String, Object>> l;
			if(index.isEmpty()) {
				List<Map<String, Object>> functions = new ArrayList<>();
				Map<String, Object> function = new LinkedHashMap<>();
				function.put(Constant.FUNCTION, functions);
				((List<Map<String, Object>>)map.get(Constant.STATEMENT)).add(function);
				l = functions;
			} else {
				l =(List<Map<String, Object>>) ((List<Map<String, Object>>)map.get(Constant.STATEMENT)).get(index.get()).get(Constant.FUNCTION);
			}
			Map<String, Object> value = new LinkedHashMap<>();
			l.add(value);
			functionMethod(p, value);
		} else if(name.equals(Constant.VALUE)) {
			List<Map<String, Object>> l = (List<Map<String, Object>>) map.get(Constant.STATEMENT);
			Map<String, Object> value = new LinkedHashMap<>();
			l.add(value);
			noLoopsMethod(name, p, value);
		} else if(name.equals(Constant.EXPR) || name.equals(Constant.BOOLEAN_EXPRESSION) || name.equals(Constant.POSITION)) { 
			noLoopsMethod(name, p, map);
		} else {
			for(int i = 0; i < p.getChildCount(); i++) {
				String childName = getClassName(p.getChild(i));
				if(childName.equals(Constant.COMMENT)) {
					toJson(p.getChild(i), map);
				} else if(childName.equals(Constant.STATEMENT) || childName.equals(Constant.DEFINITION)) {
					toJson(p.getChild(i), map);
				} else if(childName.equals(Constant.FUNCTION)) {
					toJson(p.getChild(i), map);
				} else if(childName.equals(Constant.VALUE)) {
					toJson(p.getChild(i), map);
				} else if(childName.equals(Constant.IF_STATEMENT)) {
					toJson(p.getChild(i), map);
				} else if(childName.equals(Constant.DECLARATION)) {
					toJson(p.getChild(i), map);
				} else if(childName.equals(Constant.ELSE_STATEMENT)) {
					toJson(p.getChild(i), map);
				} else if(childName.equals(Constant.WHILE_STATEMENT)) {
					toJson(p.getChild(i), map);
				} else if(childName.equals(Constant.EXPRESSION)) {
					if(getClassName(p.getChild(i).getChild(0)).equals(Constant.FUNCTION_CALL)) {
						functionCall(p.getChild(i).getChild(0), map);
					}
				} else if(childName.equals(Constant.ARRAYS)) {
					updateArrays(p.getChild(i), map);
				}
			}
		}

		return map;
	}

	@SuppressWarnings("unchecked")
	private void updateArrays(ParseTree child, Map<String, Object> map) {
		List<Map<String, Object>> statements = (List<Map<String, Object>>) map.get(Constant.STATEMENT);
		Optional<Integer> index = getIndexKey(statements, Constant.ARRAYS);
		Map<String, Object> array;
		if(index.isPresent()) {
			List<Map<String, Object>> arrays = (List<Map<String, Object>>) statements.get(index.get()).get(Constant.ARRAYS);
			array = new LinkedHashMap<>();
			array.put(Constant.VARIABLE, child.getChild(0).getText());
			arrays.add(array);
		} else {
			Map<String, Object> arrays = new LinkedHashMap<>();
			statements.add(arrays);
			List<Map<String, Object>> arraysList = new ArrayList<>();
			arrays.put(Constant.ARRAYS, arraysList);
			array = new LinkedHashMap<>();
			arraysList.add(array);
			String varName = child.getChild(0).getText();
			array.put(Constant.VARIABLE, varName);
			
		}

		if(child.getChildCount() > 5) {
			insertTypeIntoMap(array, child.getChild(6));
		} else {
			insertTypeIntoMap(array, child.getChild(2));
		}

	}

	@SuppressWarnings("unchecked")
	private void functionCall(ParseTree child, Map<String, Object> map) {
		List<Map<String, Object>> statements = (List<Map<String, Object>>) map.get(Constant.STATEMENT);
		Optional<Integer> index = getIndexKey(statements, Constant.FUNCTION_CALL);
		if(index.isPresent()) {
			List<Map<String, Object>> functionCall = (List<Map<String, Object>>) statements.get(index.get()).get(Constant.FUNCTION_CALL);
			Map<String, Object> fCall = new LinkedHashMap<>();
			functionCall.add(fCall);
			fCall.put(Constant.VARIABLE, child.getChild(0).getText());
			updateArgsValue(child.getChild(2), fCall);
		} else {
			Map<String, Object> f = new LinkedHashMap<>();
			statements.add(f);
			List<Map<String, Object>> functionCalls = new ArrayList<>();
			f.put(Constant.FUNCTION_CALL, functionCalls);
			Map<String, Object> functionStuff = new LinkedHashMap<>();
			functionCalls.add(functionStuff);
			functionStuff.put(Constant.VARIABLE, child.getChild(0).getText());
			updateArgsValue(child.getChild(2), functionStuff);
		}
	}

	private void updateArgsValue(ParseTree child, Map<String, Object> map) {
		List<Map<String, Object>> params = new ArrayList<>();
		map.put(Constant.PARAMETERS, params);
		for(int i = 0; i < child.getChildCount(); i++) {
			Map<String, Object> param = new LinkedHashMap<>();
			params.add(param);
			String childName = getClassName(child.getChild(i));
			if(childName.equals(Constant.FUNCTION_CALL)) {
				paramFunctionCall(param, child.getChild(i));
			} else {
				insertTypeIntoMap(param, child.getChild(i));
				if(param.isEmpty()) {
					params.remove(param);
				}
			}
		}
	}

	private void paramFunctionCall(Map<String, Object> param, ParseTree child) {
		List<Map<String, Object>> params = new ArrayList<>();
		param.put(Constant.FUNCTION, params);
		Map<String, Object> functionCallStuff = new LinkedHashMap<>();
		params.add(functionCallStuff);
		functionCallStuff.put(Constant.VARIABLE, child.getChild(0).getText());
		updateArgsValue(child.getChild(2), functionCallStuff);
	}

	private Optional<Integer> getIndexKey(List<Map<String, Object>> parentList, String key) {
		int index = 0;
		for(Map<String, Object> p : parentList) {
			if(p.containsKey(key)) {
				return Optional.of(index);
			}
			index++;
		}
		return Optional.empty();
	}

	private void functionMethod(ParseTree p, Map<String, Object> map) {
		updateNameType(p, map);
		nextStatements(map, p);
	}

	private void updateNameType(ParseTree p, Map<String, Object> map) {
		String name = p.getChild(0).getChild(0).getText();

		map.put(Constant.NAME, name);
		List<Map<String, Object>> rType = new ArrayList<>();
		map.put(Constant.RETURN_TYPE, rType);
		Map<String, Object> rMap = new LinkedHashMap<>();
		rType.add(rMap);
		rMap.put(Constant.TYPE, p.getChild(0).getChild(2).getChild(0).getText());
		if(p.getChild(0).getChild(2).getChildCount() > 1) {
			rMap.put(Constant.IS_ARRAY, true);
		} else {
			rMap.put(Constant.IS_ARRAY, false);
		}
		if(p.getChild(0).getChildCount() > 3) {
			updateRefinement(p, map, 0);
		}
		updateArgs(p.getChild(2), map);
	};

	private void updateArgs(ParseTree child, Map<String, Object> map) {
		List<Map<String, Object>> params = new ArrayList<>();
		map.put(Constant.PARAMETERS, params);
		for(int i = 0; i < child.getChildCount(); i++) {
			if(!(child.getChild(i) instanceof TerminalNodeImpl)) {
				Map<String, Object> param = new LinkedHashMap<>();
				params.add(param);
				param.put(Constant.NAME, child.getChild(i).getChild(0).getText());
				param.put(Constant.TYPE, child.getChild(i).getChild(2).getChild(0).getText());
				if(child.getChild(i).getChild(2).getChildCount() > 1) {
					param.put(Constant.IS_ARRAY, true);
				} else {
					param.put(Constant.IS_ARRAY, false);
				}
				if(child.getChild(i).getChildCount() > 3) {
					updateRefinement(child, param, i);
				}
			}		
		}
	}

	private void updateRefinement(ParseTree child, Map<String, Object> map, int index) {
		List<Map<String, Object>> refinements = new ArrayList<>();
		map.put(Constant.REFINEMENT, refinements);
		Map<String, Object> refinement = new LinkedHashMap<>();
		refinements.add(refinement);
		refinement.put(Constant.VARIABLE, child.getChild(index).getChild(3).getChild(1).getText());
		refinement.put(Constant.OPERATOR, child.getChild(index).getChild(3).getChild(2).getChild(0).getText());
		insertTypeIntoMap(refinement, child.getChild(index).getChild(3).getChild(3));
	}

	private void insertTypeIntoMap(Map<String, Object> map, ParseTree child) {
		String name = getClassName(child);
		if(name.equals(Constant.NUMBER)) {
			Token token = ((TerminalNodeImpl)(child.getChild(0))).getSymbol();
			updateMap(token, map);
			map.put(Constant.VALUE, child.getChild(0).getText());
		} else if(name.equals(Constant.TERMINAL_NODE_IMPL)) {
			Token token = ((TerminalNodeImpl) child).getSymbol();
			boolean b = updateMap(token, map);
			if(b)
				map.put(Constant.VALUE, child.getText());
		} else if(name.equals(Constant.STRING_LIT)) {
			StringBuilder bob = new StringBuilder();
			for(int j = 0; j < child.getChildCount(); j++) {
				bob.append(child.getChild(j).getText());
			}
			map.put(Constant.VALUE_TYPE, Constant.STRING);
			map.put(Constant.VALUE, bob.toString());
		} else if(name.equals(Constant.EXPR)) {
			map.put(Constant.VALUE_TYPE, Constant.EXPR);
			List<Map<String, Object>> exprValues = new ArrayList<>();
			map.put(Constant.VALUE, exprValues);
			Map<String, Object> expr = new LinkedHashMap<>();
			exprValues.add(expr);
			expr.put(Constant.EXPRESSION_VALUE, child.getChild(0).getChild(0).getText());
			String typeExprClass = getClassName(child.getChild(0).getChild(0));
			if(typeExprClass.equals(Constant.TERMINAL_NODE_IMPL)) {
				Token token = ((TerminalNodeImpl) child.getChild(0).getChild(0)).getSymbol();
				updateMap(token, expr);
			} else if(typeExprClass.equals(Constant.NUMBER)) {
				Token token = ((TerminalNodeImpl) child.getChild(0).getChild(0).getChild(0)).getSymbol();
				updateMap(token, expr);
			} else if(typeExprClass.equals(Constant.FUNCTION_CALL)) {
				expr.put(Constant.VALUE_TYPE, Constant.FUNCTION_CALL);
				List<Map<String, Object>> funcValues = new ArrayList<>();
				expr.put(Constant.VALUE_FUNC, funcValues);
				Map<String, Object> funcExpr = new LinkedHashMap<>();
				funcValues.add(funcExpr);
				List<Map<String, Object>> funcList = new ArrayList<>();
				Map<String, Object> funcMap = new LinkedHashMap<>();
				funcList.add(funcMap);
				funcExpr.put(Constant.FUNCTION, funcList);
				funcMap.put(Constant.VARIABLE, child.getChild(0).getChild(0).getChild(0).getText());
				updateArgsValue(child.getChild(0).getChild(0).getChild(2), funcMap);
			}
			if(child.getChildCount() > 1) {
				expr.put(Constant.OPERATOR, child.getChild(1).getText());
				expr.put(Constant.VALUE, new ArrayList<Map<String, Object>>());
				toJson(child.getChild(2), expr);
			}

		} else if(name.equals(Constant.BOOLEAN_EXPRESSION)) {
			String childName = getClassName(child.getChild(0));
			if(childName.equals(Constant.TERMINAL_NODE_IMPL)) {
				toJson(child.getChild(1), map);
			} else {
				map.put(Constant.BOOLEAN_VALUE, child.getChild(0).getChild(0).getText());
				insertTypeIntoMap(map, child.getChild(0).getChild(0));
				if(child.getChildCount() > 2) {
					List<Map<String, Object>> exprValues = new ArrayList<>();
					map.put(Constant.OPERATOR, child.getChild(1).getText());
					map.put(Constant.VALUE_BOOLEAN, exprValues);
					Map<String, Object> expr = new LinkedHashMap<>();
					exprValues.add(expr);
					expr.put(Constant.VALUE_BOOLEAN, new ArrayList<Map<String, Object>>());
					toJson(child.getChild(2), expr);

				}
			}
		} else if(name.equals(Constant.POSITION)) {
			map.put(Constant.VALUE_TYPE, Constant.POSITION);
			List<Map<String, Object>> exprValues = new ArrayList<>();
			map.put(Constant.VALUE, exprValues);
			Map<String, Object> expr = new LinkedHashMap<>();
			exprValues.add(expr);
			expr.put(Constant.POSITION_VALUE, child.getChild(0).getChild(0).getText());
			String typeExprClass = getClassName(child.getChild(0).getChild(0));
			if(typeExprClass.equals(Constant.TERMINAL_NODE_IMPL)) {
				Token token = ((TerminalNodeImpl) child.getChild(0).getChild(0)).getSymbol();
				updateMap(token, expr);
			} else if(typeExprClass.equals(Constant.NUMBER)) {
				Token token = ((TerminalNodeImpl) child.getChild(0).getChild(0).getChild(0)).getSymbol();
				updateMap(token, expr);
			}
			if(child.getChildCount() > 1) {
				expr.put(Constant.OPERATOR, child.getChild(1).getText());
				expr.put(Constant.VALUE, new ArrayList<Map<String, Object>>());
				toJson(child.getChild(2), expr);
			}
		} else if(name.equals(Constant.FUNCTION_CALL)) {
			map.put(Constant.VALUE_TYPE, Constant.FUNCTION_CALL);
			List<Map<String, Object>> exprValues = new ArrayList<>();
			map.put(Constant.VALUE_FUNC, exprValues);
			Map<String, Object> expr = new LinkedHashMap<>();
			exprValues.add(expr);
			List<Map<String, Object>> funcList = new ArrayList<>();
			Map<String, Object> funcMap = new LinkedHashMap<>();
			funcList.add(funcMap);
			expr.put(Constant.FUNCTION, funcList);
			funcMap.put(Constant.VARIABLE, child.getChild(0).getText());
			updateArgsValue(child.getChild(2), funcMap);
		}
	}

	@SuppressWarnings("unchecked")
	private void noLoopsMethod(String name, ParseTree p, Map<String, Object> map) {
		if(name.equals(Constant.COMMENT)) {
			StringBuilder bob = new StringBuilder();
			for(int j = 0; j < p.getChildCount(); j++) {
				bob.append(p.getChild(j).getText());
			}
			map.put(Constant.COMMENT, bob.toString());

		} else if(name.equals(Constant.VALUE)) {
			String variableName = p.getChild(0).getChild(0).getText();
			String variableType;
			List<Map<String, Object>> values = new ArrayList<>();
			map.put(Constant.VARIABLE, values);
			Map<String, Object> variableValues = new LinkedHashMap<>();
			if(p.getChild(0).getChildCount() > 1) {
				variableType = p.getChild(0).getChild(2).getChild(0).getText();
				if(p.getChild(0).getChild(2).getChildCount() > 1) {
					variableValues.put(Constant.IS_ARRAY, true);
				} else {
					variableValues.put(Constant.IS_ARRAY, false);
				}
			} else {
				variableType = null;
			}

			values.add(variableValues);
			variableValues.put(Constant.NAME, variableName);
			variableValues.put(Constant.TYPE, variableType);

			insertTypeIntoMap(variableValues, p.getChild(2));

		} else if(name.equals(Constant.EXPR) || name.equals(Constant.BOOLEAN_EXPRESSION) || name.equals(Constant.POSITION)) {
			Map<String, Object> valueExpr = new LinkedHashMap<>();
			List<Map<String, Object>> l = ((List<Map<String,Object>>)map.get(Constant.VALUE));
			if(l!= null) {
				l.add(valueExpr);
				insertTypeIntoMap(valueExpr, p);
			} else {
				insertTypeIntoMap(map, p);
			}
		}
	}

	private String getClassName(ParseTree child) {
		return child.getClass().getSimpleName().replaceAll("Context$", "");
	}

	private boolean updateMap(Token token, Map<String, Object> variableValues) {
		if(token.getType() == Constant.VARIABLE_TYPE) {
			variableValues.put(Constant.VALUE_TYPE, Constant.VARIABLE);
			return true;
		} else if(token.getType() == Constant.BOOLEAN_TYPE || token.getType() == Constant.BOOLEAN_TYPE + 1) {
			variableValues.put(Constant.VALUE_TYPE, Constant.BOOLEAN);
			return true;
		}else if(token.getType() == Constant.NUMBER_INT_TYPE) {
			variableValues.put(Constant.VALUE_TYPE, Constant.INT);
			return true;
		} else if(token.getType() == Constant.NUMBER_DOUBLE_TYPE) {
			variableValues.put(Constant.VALUE_TYPE, Constant.DOUBLE);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private void booleanMethod(ParseTree p, Map<String, Object> map, String key) {
		List<Map<String, Object>> statements = (List<Map<String, Object>>) map.get(Constant.STATEMENT);
		List<Map<String, Object>> ifStatements = new ArrayList<>();
		Map<String, Object> ifs = new LinkedHashMap<>();
		ifs.put(key, ifStatements);
		statements.add(ifs);
		Map<String, Object> actualIfStatement = new LinkedHashMap<>();
		ifStatements.add(actualIfStatement);
		String notName = getClassName(p.getChild(1));
		if(notName.equals(Constant.TERMINAL_NODE_IMPL)) {
			actualIfStatement.put(Constant.NOT, true);
			updateMapBooleanExpression(actualIfStatement, p.getChild(2));

		} else {
			actualIfStatement.put(Constant.NOT, false);
			updateMapBooleanExpression(actualIfStatement, p.getChild(1));
		}

		nextStatements(actualIfStatement, p);
	}

	private void nextStatements(Map<String, Object> map, ParseTree p) {
		List<Map<String, Object>> states = new ArrayList<>();
		map.put(Constant.STATEMENT, states);
		for(int i = 0; i < p.getChildCount(); i++) {
			String childName = getClassName(p.getChild(i));
			if(childName.equals(Constant.DEFINITION) || childName.equals(Constant.STATEMENT) 
					|| childName.equals(Constant.ELSE_STATEMENT)) {
				toJson(p.getChild(i), map);
			} else if(childName.equals(Constant.RETURN_STATEMENT)) {
				List<Map<String, Object>> l = new ArrayList<>();
				map.put(Constant.RETURN_STATEMENT, l);
				Map<String, Object> m = new LinkedHashMap<>();
				l.add(m);
				insertTypeIntoMap(m, p.getChild(i).getChild(1));
			}
		}
	}

	private void updateMapBooleanExpression(Map<String, Object> map, ParseTree p) {
		insertTypeIntoMap(map, p);
	}
}
