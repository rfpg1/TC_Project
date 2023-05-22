import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import utils.Pair;

public class Compiler {

	private Emitter emitter;
	private List<Map<String, Object>> tree;
	private Context context;

	public Compiler(Emitter e, Context c, List<Map<String, Object>> tree) {
		this.emitter = e;
		this.context = c;
		this.tree = tree;
	}

	@SuppressWarnings("unchecked")
	public void compileToLLVM() throws FileNotFoundException {
		if(tree != null) {
			addConstants();
			for(Map<String, Object> func : tree) {
				if(func.containsKey(Constant.FUNCTION)) {
					for(Map<String, Object> map : (List<Map<String, Object>>) func.get(Constant.FUNCTION)) {
						emitter.incrementCount();
						addFunctions(map);
					}
				}
			}

		}
		emitter.generateFile();
	}

	@SuppressWarnings("unchecked")
	private void addFunctions(Map<String, Object> func) {
		String name = (String) func.get(Constant.NAME);
		String params = getParams((List<Map<String, Object>>) func.get(Constant.PARAMETERS));
		int fCount = emitter.getFunctionCount();
		emitter.incrementFuntionCount();
		String type = (String) ((List<Map<String, Object>>)func.get(Constant.RETURN_TYPE)).get(0).get(Constant.TYPE);
		String size = getSize(type);
		emitter.insert("define " + size  + " @" + name + "(" + params + ") #" + fCount + " {");
		allocateParams((List<Map<String, Object>>) func.get(Constant.PARAMETERS));
		int index = emitter.getCount();
		List<Map<String, Object>> statements = (List<Map<String, Object>>) func.get(Constant.STATEMENT);
		addStatements(statements, index);

		Map<String, Object> rValue = ((List<Map<String, Object>>) func.get(Constant.RETURN_STATEMENT)).get(0);
		setReturnStatement(rValue, size, index);
		emitter.resetVars();
	}

	private void allocateParams(List<Map<String, Object>> params) {
		for(int i = 0; i < params.size(); i++) {
			Map<String, Object> param = params.get(i);
			String name = (String) param.get(Constant.NAME);
			String pointerName = emitter.getPointerName(name);
			String type = (String) param.get(Constant.TYPE);
			String size = getSize(type);
			boolean isArray = (boolean) param.get(Constant.IS_ARRAY);
			if(isArray) {
				size += "*";
			}
			emitter.insert(pointerName + " = alloca " + size);
			name = "%" + name;
			emitter.insert("store " + size + "" + name + ", " + size + "* " + pointerName);			
		}
	}

	private String getSize(String type) {	
		switch(type) {
		case Constant.INT:
		case Constant.DOUBLE:
		case Constant.FLOAT:
			return "i32";
		case Constant.STRING:
			return "i8*";
		case Constant.BOOLEAN:
			return "i1";
		case Constant.VOID:
			return "void";
		}

		return null;
	}

	private void setReturnStatement(Map<String, Object> map, String size, int index) {
		if(!map.isEmpty()) {
			String value = getValue(map, null, size, index);
			if(value.matches("\\d+\\.?\\d*")) { //Number || boolean
				emitter.insert("ret " + size +  " " + value + "\n}", index);
			} else if(value.matches("\"(.+?)\"")) { //String
				String strName = "@.str." + emitter.getStringCount();
				emitter.incrementStringCount();
				value = value.substring(1, value.length() - 1);
				emitter.insert( strName + " = private unnamed_addr constant [" + (value.length() + 1) 
						+ " x i8] c\"" + value + "\\00\"", 0);
				emitter.insert("ret " + size + " getelementptr inbounds ([" + (value.length() + 1) 
						+ " x i8], [" + (value.length() + 1) + " x i8]* " + strName + " , i64 0, i64 0)", index);
				emitter.insert("\n}", index);
			} else if(value.contains("%")) { //FuntionCall
				String temp = "%temp" + emitter.getCountVars();
				emitter.incrementCountVars();
				emitter.insert(temp + " = alloca " + size);
				emitter.insert("store " + size + " " + value + ", " + size + "* " + temp);
				String newVar = "%ret_" + emitter.getCountVars();
				emitter.incrementCountVars();

				emitter.insert(newVar + " = load " + size + ", " + size + "* " + temp, index);
				emitter.insert("ret " + size + " " + newVar + "\n}", index);
			} else { //Variable
				String varName;
				if(!context.hasVar(value)) {
					varName = emitter.getPointerName(value);
				} else {
					varName = emitter.getConst(value);
				}

				String newVar = "%ret_" + emitter.getCountVars();
				emitter.incrementCountVars();

				emitter.insert(newVar + " = load " + size + ", " + size + "* " + varName, index);
				emitter.insert("ret " + size + " " + newVar + "\n}", index);
			}
		} else {
			emitter.insert("ret void\n}", index);
		}
	}

	@SuppressWarnings("unchecked")
	private void addStatements(List<Map<String, Object>> statements, int index) {
		for(Map<String, Object> statement : statements) {
			for(String key : statement.keySet()) {
				if(key.equals(Constant.FUNCTION)) {
					for(Map<String, Object> map : (List<Map<String, Object>>) statement.get(Constant.FUNCTION)) {
						emitter.incrementCount();
						addFunctions(map);
					}
				} else if(key.equals(Constant.VARIABLE)) {
					addVariable(((List<Map<String, Object>>)statement.get(Constant.VARIABLE)).get(0), index);
				} else if(key.equals(Constant.IF_STATEMENT) || key.equals(Constant.WHILE_STATEMENT)) {
					Map<String, Object> states = ((List<Map<String, Object>>) statement.get(key)).get(0);
					addBooleanStatements(states, index, key);
				}
			}
			
		}
	}

	@SuppressWarnings("unchecked")
	private void addBooleanStatements(Map<String, Object> state, int index, String type) {
		String bExpr = "%bExpr_" + emitter.getCountVars();
		emitter.insert("br label " + bExpr, index);
		emitter.incrementCountVars();
		emitter.insert(bExpr.substring(1, bExpr.length()) + ":", index);
		String endLabel = "%end_label" + emitter.getCountVars();
		emitter.incrementCountVars();
		String ifLabel = "%if_label" + emitter.getCountVars();
		emitter.incrementCountVars();
		String elseLabel = "%else_label" + emitter.getCountVars();
		emitter.incrementCountVars();
		String var = getBooleanExpr(state, index, null, 0);
		if(hasIfStatements(state)) {
			if(hasElseStatement(state)) {
				emitter.insert("br i1 " + var + ", label " + ifLabel + ", label " + elseLabel, index);
				emitter.insert(ifLabel.substring(1, ifLabel.length()) + ":", index);
				List<Map<String, Object>> statesIf = (List<Map<String, Object>>) state.get(Constant.STATEMENT); 
				addStatements(statesIf, index);
				emitter.insert("br label " + endLabel);
				emitter.insert(elseLabel.substring(1, elseLabel.length()) + ":", index);
				List<Map<String, Object>> eStaments = (List<Map<String, Object>>) state.get(Constant.STATEMENT);
				for(Map<String, Object> eState : eStaments) {
					if(eState.containsKey(Constant.ELSE_STATEMENT)) {
						List<Map<String, Object>> elStates = (List<Map<String, Object>>) eState.get(Constant.ELSE_STATEMENT);
						elStates = (List<Map<String, Object>>) elStates.get(0).get(Constant.STATEMENT);
						addStatements(elStates, index);
						emitter.insert("br label " + endLabel);
					}
				}
			} else {
				emitter.insert("br i1 " + var + ", label " + ifLabel + ", label " + endLabel, index);
				emitter.insert(ifLabel.substring(1, ifLabel.length()) + ":", index);
				List<Map<String, Object>> statesIf = (List<Map<String, Object>>) state.get(Constant.STATEMENT); 
				addStatements(statesIf, index);
				if(type.equals(Constant.IF_STATEMENT)) {
					emitter.insert("br label " + endLabel);
				} else {
					emitter.insert("br label " + bExpr, index);
				}
			}
		} else {
			if(hasElseStatement(state)) {
				emitter.insert("br i1 " + var + ", label " + endLabel + ", label " + elseLabel, index);
				emitter.insert(elseLabel.substring(1, elseLabel.length()) + ":", index);
				List<Map<String, Object>> eStaments = (List<Map<String, Object>>) state.get(Constant.STATEMENT);
				for(Map<String, Object> eState : eStaments) {
					if(eState.containsKey(Constant.ELSE_STATEMENT)) {
						List<Map<String, Object>> elStates = (List<Map<String, Object>>) eState.get(Constant.ELSE_STATEMENT);
						elStates = (List<Map<String, Object>>) elStates.get(0).get(Constant.STATEMENT);
						addStatements(elStates, index);
						emitter.insert("br label " + endLabel);
					}
				}
			} else {
				emitter.insert("br i1 " + var + ", label " + endLabel + ", label " + endLabel, index);
			}
		}	
		
		emitter.insert(endLabel.substring(1, endLabel.length()) + ":", index);

	}

	@SuppressWarnings("unchecked")
	private boolean hasIfStatements(Map<String, Object> state) {
		List<Map<String, Object>> statements = (List<Map<String, Object>>) state.get(Constant.STATEMENT);
		for(Map<String, Object> statement : statements) {
			for(String key : statement.keySet()) {
				if(!key.equals(Constant.COMMENT) && !key.equals(Constant.ELSE_STATEMENT)) {
					return true;
				}
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private String getBooleanExpr(Map<String, Object> state, int index, String exprVar, int flag) {
		String type = (String) state.get(Constant.VALUE_TYPE);
		String value = "";
		String tempVar = "%temp_var" + emitter.getCountVars();
		emitter.incrementCountVars();
		String op = (String) state.get(Constant.OPERATOR);
		if(type.equals(Constant.BOOLEAN)) {
			tempVar = booleanTypeExpr(value, tempVar, index, state);
		} else if(type.equals(Constant.INT) || type.equals(Constant.DOUBLE) || type.equals(Constant.FLOAT)) {
			integerTypeExpr(value, tempVar, state, index);
		} else if(type.equals(Constant.VARIABLE)) {
			value = (String) state.get(Constant.BOOLEAN_VALUE);
			if(context.hasVar(value)) {
				value = emitter.getConst(value);
			} else {
				value = emitter.getPointerName(value);
			}
			emitter.insert(tempVar + " = load i32, i32* " + value, index);
		} else if(type.equals(Constant.FUNCTION_CALL)) {
			tempVar = getValue(state, null, null, index);
			String size = getFunctionSize(state);
			if(size.equals("i1")) {
				String temp3 = "%temp_var" + emitter.getCountVars();
				emitter.incrementCountVars();
				emitter.insert(temp3 + " = zext i1 " + tempVar + " to i32", index);
				tempVar = temp3;
			}
		} else if(type.equals(Constant.EXPR)) {
			tempVar  = getValue(state, null, null, index);
			
		}
		if(op != null) {
			Map<String, Object> nMap = ((List<Map<String, Object>>) state.get(Constant.VALUE_BOOLEAN)).get(0);
			String opType = Constant.getOperatorType(op);
			String v2;
			if(opType.equals(Constant.MATH)) {
				v2 = getValue(nMap, null, null, 0);
			} else {
				v2 = getBooleanExpr(nMap, index, exprVar, 1);
			}
			String fTemp = "%result_boolean" + emitter.getCountVars();
			emitter.incrementCountVars();
			switchCase(fTemp, tempVar, v2, index, op);
			List<Map<String, Object>> nnMap = (List<Map<String, Object>>) nMap.get(Constant.VALUE_BOOLEAN);
			if(nnMap != null && nnMap.size() > 0 && flag == 1) {
				Map<String, Object> b = nnMap.get(0);
				String s = getBooleanExpr(b, index, exprVar, 1);
				op = (String) nMap.get(Constant.OPERATOR);
				String nTemp = "%temp_var" + emitter.getCountVars();
				emitter.incrementCountVars();
				switchCase(nTemp, fTemp, s, index, op);
				fTemp = nTemp;
			}
			return fTemp;
		} else {
			return tempVar;
		}
	}

	private void switchCase(String fTemp, String tempVar, String v2, int index, String op) {
		switch(op) {
		case "||":
			emitter.insert(fTemp + " = or i1 " + tempVar + ", " + v2, index);
			break;
		case "&&":
			emitter.insert(fTemp + " = and i32 " + tempVar + ", " + v2, index);

			break;
		case ">":
			emitter.insert(fTemp + " = icmp sgt i32 " + tempVar + ", " + v2, index);
			break;
		case ">=":
			emitter.insert(fTemp + " = icmp sge i32 " + tempVar + ", " + v2, index);
			break;
		case "<":
			emitter.insert(fTemp + " = icmp slt i32 " + tempVar + ", " + v2, index);
			break;
		case "<=":
			emitter.insert(fTemp + " = icmp sle i32 " + tempVar + ", " + v2, index);
			break;
		case "==":
			emitter.insert(fTemp + " = icmp eq i32 " + tempVar + ", " + v2, index);
			break;
		case "!=":
			emitter.insert(fTemp + " = icmp ne i32 " + tempVar + ", " + v2, index);
			break;
		}
	}

	@SuppressWarnings("unchecked")
	private String getFunctionSize(Map<String, Object> child) {
		Map<String, Object> func = ((List<Map<String, Object>>)((List<Map<String, Object>>) child.get(Constant.VALUE_FUNC)).get(0)
				.get(Constant.FUNCTION)).get(0);
		String funcName = (String) func.get(Constant.VARIABLE);
		String rType = (String) ((Pair<List<?>, Map<String, Object>>)context.getFunction(funcName)).getSecond().get(Constant.TYPE);
		String size = getSize(rType);
		return size;
	}

	private void integerTypeExpr(String value, String tempVar, Map<String, Object> state, int index) {
		value = (String) state.get(Constant.BOOLEAN_VALUE);
		emitter.insert(tempVar + " = add i32 0, " + value, index);
	}

	private String booleanTypeExpr(String value, String tempVar, int index, Map<String, Object> state) {
		value = getBooleanValue((String)state.get(Constant.BOOLEAN_VALUE));
		emitter.insert(tempVar + " = icmp eq i32 " + value + ", 1", index);
		
		return tempVar;
		
	}

	@SuppressWarnings("unchecked")
	private boolean hasElseStatement(Map<String, Object> states) {
		List<Map<String, Object>> statements = (List<Map<String, Object>>) states.get(Constant.STATEMENT);
		for(Map<String, Object> state : statements) {
			if(state.containsKey(Constant.ELSE_STATEMENT)) {
				System.out.println("");
				List<Map<String, Object>> s = (List<Map<String, Object>>)((List<Map<String, Object>>) state.get(Constant.ELSE_STATEMENT)).get(0).get(Constant.STATEMENT);
				return !s.isEmpty();
			}
		}
		return false;
	}

	private void addVariable(Map<String, Object> statement, int index) {
		String size = getSize((String) statement.get(Constant.TYPE));
		boolean isArray = (boolean) statement.get(Constant.IS_ARRAY);
		if(isArray) {
			size += "*";
		}
		String name = (String) statement.get(Constant.NAME);
		String v = emitter.getPointerName(name);
		String value = getValue(statement, name, size, index);
		if(value != null && value.matches("\"(.+?)\"")) {
			emitter.insert(v + " = alloca " + size, index);
			String strName = "@.str." + emitter.getStringCount();
			emitter.incrementStringCount();
			value = value.substring(1, value.length() - 1);
			emitter.insert( strName + " = private unnamed_addr constant [" + (value.length() + 1) 
					+ " x i8] c\"" + value + "\\00\"", 0);
			emitter.insert("store " + size + " getelementptr inbounds ([" + (value.length() + 1) 
					+ " x i8], [" + (value.length() + 1) + " x i8]* " + strName + " , i64 0, i64 0), " 
					+ size + "* " + v, index);
		} else {
			emitter.insert(v + " = alloca " + size, index);
			emitter.insert("store " + size + " " + value + " , " + size + "* " + v, index);
		}
	}

	private String getParams(List<Map<String, Object>> params) {
		StringBuilder bob = new StringBuilder();
		for(int i = 0; i < params.size(); i++) {
			Map<String, Object> param = params.get(i);
			String name = (String) param.get(Constant.NAME);
			name = "%" + name;
			Object o = param.get(Constant.IS_ARRAY);
			if(o != null) {
				boolean isArray = (boolean) o;
				if(isArray) {
					bob.append("i32* " + name);
				} else {
					bob.append("i32 " + name);
				}
			} else {
				bob.append("i32 " + name);
			}

			if(i + 1 < params.size()) {
				bob.append(", ");
			}
		}
		return bob.toString();
	}

	@SuppressWarnings("unchecked")
	private void addConstants() {
		for(Map<String, Object> child : tree) {
			if(child.containsKey(Constant.VARIABLE)) {
				Map<String, Object> var = ((List<Map<String, Object>>) child.get(Constant.VARIABLE)).get(0);
				String name = (String) var.get(Constant.NAME);
				String v = this.emitter.getConst(name);
				String value = getValue(var, name, null, 0);
				if(value.matches("\"(.+?)\"")) {
					String strName = "@.str." + emitter.getStringCount();
					emitter.incrementStringCount();
					value = value.substring(1, value.length() - 1);
					emitter.insert( strName + " = private unnamed_addr constant [" + (value.length() + 1) + "x i8] c\"" + value + "\\00\"", 0);
					emitter.insert(v + "= global i8* getelementptr inbounds ([" 
							+ (value.length() + 1) + " x i8], [" + (value.length() + 1) + " x i8]* "+ strName + ", i32 0, i32 0)");
				} else {
					emitter.insert(v + "= global i32 " + value);
				}
			}
		}
		emitter.insert("", 0);
	}

	@SuppressWarnings("unchecked")
	private String getValue(Map<String, Object> child, String varName, String size, int index) {
		String type = (String) child.get(Constant.VALUE_TYPE);
		if(!type.equals(Constant.FUNCTION_CALL) && !type.equals(Constant.VARIABLE) 
				&& !type.equals(Constant.EXPR) && !type.equals(Constant.BOOLEAN)
				&& !type.equals(Constant.STRING)) {
			return (String) child.get(Constant.VALUE);
		} else {
			if(type.equals(Constant.FUNCTION_CALL)) {
				String v1 = "%callFunction_" + emitter.getCountVars();
				Map<String, Object> func = ((List<Map<String, Object>>)((List<Map<String, Object>>) child.get(Constant.VALUE_FUNC)).get(0)
						.get(Constant.FUNCTION)).get(0);
				String funcName = (String) func.get(Constant.VARIABLE);
				String params = getParamsFunctionCall((List<Map<String, Object>>) func.get(Constant.PARAMETERS));
				String rType = (String) ((Pair<List<?>, Map<String, Object>>)context.getFunction(funcName)).getSecond().get(Constant.TYPE);
				size = getSize(rType);
				emitter.insert(v1 + " = call " + size + " @" + funcName + "(" + params + ")", index);
				return v1;
			} else if(type.equals(Constant.EXPR)) {
				Map<String, Object> expr = ((List<Map<String, Object>>) child.get(Constant.VALUE)).get(0);
				return getExprValue(expr, null, null, size, index);
			} else if(type.equals(Constant.VARIABLE)) {
				return getVariableValue(child);
			} else if(type.equals(Constant.BOOLEAN)){
				String value = (String) child.get(Constant.VALUE);
				return getBooleanValue(value);
			} else {
				String value = (String) child.get(Constant.VALUE);
				return value;
			}
		}
	}

	private String getBooleanValue(String value) {
		if(value.equals(Constant.TRUE)) {
			return "1";
		} else {
			return "0";
		}
	}

	@SuppressWarnings("unchecked")
	private String getParamsFunctionCall(List<Map<String, Object>> params) {
		StringBuilder bob = new StringBuilder();
		for(int i = 0; i < params.size(); i++) {
			Map<String, Object> param = params.get(i);
			String valueType = (String) param.get(Constant.VALUE_TYPE);
			if(valueType != null) {
				if(valueType.equals(Constant.INT) || valueType.equals(Constant.DOUBLE) 
						|| valueType.equals(Constant.FLOAT)) {
					String value = (String) param.get(Constant.VALUE);
					bob.append("i32 " + value);
				} else if(valueType.equals(Constant.VARIABLE)) {
					String var = "%new_var" + emitter.getCountVars();
					emitter.incrementCountVars();
					String varName = (String) param.get(Constant.VALUE);
					String v;
					if(context.hasVar(varName) ) {
						v = emitter.getConst(varName);
					} else {
						v = emitter.getPointerName(varName);
					}
					emitter.insert(var + " = load i32, i32* " + v);
					bob.append("i32 " + var);
				}
			} else {
				String temp = "%temp_" + emitter.getCountVars();
				emitter.incrementCountVars();
				Map<String, Object> function = ((List<Map<String,Object>>) param.get(Constant.FUNCTION)).get(0);
				String funcName = (String) function.get(Constant.VARIABLE);
				String paramsFunc = getParamsFunctionCall((List<Map<String, Object>>) function.get(Constant.PARAMETERS));
				emitter.insert(temp + " = call i32 @" + funcName + "(" + paramsFunc + ")");
				bob.append("i32 " + temp);
			}
			if(i + 1 < params.size()) {
				bob.append(",");
			}
		}
		return bob.toString();
	}

	@SuppressWarnings("unchecked")
	private String getVariableValue(Map<String, Object> child) {
		String value = (String) child.get(Constant.VALUE);
		do {
			Pair<List<String>, Boolean> pair = (Pair<List<String>, Boolean>) context.getType(value);
			if(value != null && pair != null) {
				value = pair.getFirst().get(1);
			}
		} while(context.hasVar(value));
		return value;
	}

	@SuppressWarnings("unchecked")
	private String getExprValue(Map<String, Object> expr, String tempPointer, String operator, String size, int index) {
		String type = (String) expr.get(Constant.VALUE_TYPE);
		String v1 = "";
		if(type.equals(Constant.DOUBLE) || type.equals(Constant.INT) || type.equals(Constant.FLOAT)) {
			v1 = (String) expr.get(Constant.EXPRESSION_VALUE);
		} else if(type.equals(Constant.VARIABLE)) {
			v1 = "%tempV" + emitter.getCountVars();
			emitter.incrementCountVars();
			String vName;
			String varName = (String) expr.get(Constant.EXPRESSION_VALUE);
			if(context.hasVar(varName)) {
				vName = emitter.getConst(varName);
			} else {
				vName = emitter.getPointerName(varName);
			}
			emitter.insert(v1 + " = load i32, i32* " + vName);
		} else {
			v1 = getValue(expr, null, size, index);
		}

		String op = (String) expr.get(Constant.OPERATOR);
		if(op != null) {
			Map<String, Object> map = ((List<Map<String, Object>>) expr.get(Constant.VALUE)).get(0);
			map = ((List<Map<String, Object>>) map.get(Constant.VALUE)).get(0);
			String v2 = getExprValue(map, v1, op, size, index);
			String str = "%str_value" + emitter.getCountVars();
			emitter.incrementCountVars();
			switch(op) {
			case "*":
				emitter.insert(str + " = mul i32 " + v1 + " , " + v2);
				break;
			case "/":
				emitter.insert(str + " = sdiv i32 " + v1 + " , " + v2);
				break;
			case "+":
				emitter.insert(str + " = add i32 " + v1 + " , " + v2);
				break;
			case "-":
				emitter.insert(str + " = sub i32 " + v1 + " , " + v2);
				break;
			case "%":
				emitter.insert(str + " = srem i32 " + v1 + " , " + v2);
				break;
			}	
			v1 = str;
		}
		return v1;
	}
}

