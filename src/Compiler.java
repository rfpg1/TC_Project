
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import utils.BooleanExpressionUtils;
import utils.Constant;
import utils.Context;
import utils.Emitter;
import utils.Triple;

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
			addPreludeFunctions();
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

	private void addPreludeFunctions() {
		emitter.insert("declare void @printf(i8* noundef, ...) #" + emitter.getFunctionCount(), 0);
		emitter.insert("declare void @llvm.memcpy.p0i8.p0i8.i64(i8* noalias nocapture writeonly, i8* noalias nocapture readonly, i64, i1 immarg) #" + emitter.getFunctionCount(), 0);
		emitter.insert("declare noalias noundef i8* @malloc(i64 noundef) local_unnamed_addr #" + emitter.getFunctionCount(), 0);
		emitter.insert(createArrayFunction(), 0);
	}

	private String createArrayFunction() {
		String result = 
				"define dso_local noalias i32* @createArray(i8* nocapture readnone %0, i32 noundef %1) local_unnamed_addr #"+ emitter.getFunctionCount() + " { "  + "\n"
						+ " %3 = sext i32 %1 to i64" + "\n"
						+ " %4 = shl nsw i64 %3, 2 " + "\n"
						+ " %5 = tail call noalias i8* @malloc(i64 noundef %4)" + "\n"
						+ " %6 = bitcast i8* %5 to i32*" + "\n"
						+ " ret i32* %6}";
		return result;
	}

	@SuppressWarnings("unchecked")
	private void addFunctions(Map<String, Object> func) {
		String name = (String) func.get(Constant.NAME);
		String params = getParams((List<Map<String, Object>>) func.get(Constant.PARAMETERS));
		int fCount = emitter.getFunctionCount();
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
			//			boolean isMatrix = (boolean) param.get(Constant.IS_MATRIX);
			//			if(isMatrix) {
			//				size += "*";
			//			}
			emitter.insert(pointerName + " = alloca " + size);
			name = "%" + name;
			emitter.insert("store " + size + " " + name + ", " + size + "* " + pointerName);			
		}
	}

	private String getSize(String type) {	
		switch(type) {
		case Constant.INT:
		case Constant.DOUBLE:
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
				value = value.substring(1, value.length() - 1);
				emitter.insert( strName + " = private unnamed_addr constant [" + (value.length() + 1) 
						+ " x i8] c\"" + value + "\\00\"", 0);
				emitter.insert("ret " + size + " getelementptr inbounds ([" + (value.length() + 1) 
						+ " x i8], [" + (value.length() + 1) + " x i8]* " + strName + " , i64 0, i64 0)", index);
				emitter.insert("\n}", index);
			} else if(value.contains("%")) { //FuntionCall
				String temp = "%temp" + emitter.getCountVars();
				emitter.insert(temp + " = alloca " + size);
				emitter.insert("store " + size + " " + value + ", " + size + "* " + temp);
				String newVar = "%ret_" + emitter.getCountVars();

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
				} else if(key.equals(Constant.FUNCTION_CALL)) {
					for(Map<String, Object> funcCall : (List<Map<String, Object>>) statement.get(Constant.FUNCTION_CALL)) {
						addFunctionCall(funcCall, index);
					}					
				} else if(key.equals(Constant.DECLARATION)) {
					List<Map<String, Object>> arrays = (List<Map<String, Object>>) statement.get(Constant.DECLARATION);
					addDeclaration(arrays, 0);
				}
			}

		}
	}

	@SuppressWarnings("unchecked")
	private void addDeclaration(List<Map<String, Object>> funcs, int index) {
		for(Map<String, Object> func : funcs) {
			String name = (String) func.get(Constant.NAME);
			String size = (String) ((List<Map<String, Object>>) func.get(Constant.RETURN_TYPE)).get(0).get(Constant.TYPE);
			size = getSize(size);
			List<Map<String, Object>> ps = (List<Map<String, Object>>) func.get(Constant.PARAMETERS);
			String params = getParamsDeclaration(ps);
			boolean isArray = (boolean) ((List<Map<String, Object>>) func.get(Constant.RETURN_TYPE)).get(0).get(Constant.IS_ARRAY);
			StringBuilder bob = new StringBuilder();
			bob.append("declare " + size);
			if(isArray) {
				bob.append("*");
			}
			bob.append(" @" + name + "(" + params + ") #" + emitter.getFunctionCount());
			emitter.insert(bob.toString(), index);
		}
	}

	private String getParamsDeclaration(List<Map<String, Object>> ps) {
		StringBuilder bob = new StringBuilder();

		for(int i = 0; i < ps.size(); i++) {
			Map<String, Object> param = ps.get(i);
			String type = (String) param.get(Constant.TYPE);
			String size = getSize(type);
			boolean isArray = (boolean) param.get(Constant.IS_ARRAY);
			bob.append(size);
			if(isArray) {
				bob.append("*");
			}
			if(i + 1 < ps.size()) {
				bob.append(",");
			}
		}
		return bob.toString();
	}

	@SuppressWarnings("unchecked")
	private String addArrays(Map<String, Object> array, int index) {
		String size = getSize((String) array.get(Constant.ARRAY_TYPE));
		String name = (String) array.get(Constant.VARIABLE);
		if(context.hasVar(name)) {
			name = emitter.getConst(name);
		} else {
			name = emitter.getPointerName(name);
		}
		Map<String, Object> pos = ((List<Map<String, Object>>) array.get(Constant.VALUE)).get(0);
		String position = getPosition(pos, index);
		String tempVar = "%temp_var" + emitter.getCountVars();
		emitter.insert(tempVar + " = load " + size + "*, " + size + "** " + name, index);
		String indexVar = "%temp_var" + emitter.getCountVars();
		emitter.insert(indexVar + " = getelementptr inbounds " + size + ", " + size + "* " + tempVar + ", i32 " + position, index);
		String result = "%result_var" + emitter.getCountVars();
		emitter.insert(result + " = load " + size + ", " + size + "* " + indexVar, index);
		return result;

	}

	@SuppressWarnings("unchecked")
	private String getPosition(Map<String, Object> expr, int index) {
		String type = (String) expr.get(Constant.VALUE_TYPE);
		String v1 = "";
		if(type.equals(Constant.INT)) {
			v1 = (String) expr.get(Constant.POSITION_VALUE);
		} else if(type.equals(Constant.VARIABLE)) {
			v1 = "%tempV" + emitter.getCountVars();
			String vName;
			String varName = (String) expr.get(Constant.POSITION_VALUE);
			if(context.hasVar(varName)) {
				vName = emitter.getConst(varName);
			} else {
				vName = emitter.getPointerName(varName);
			}
			emitter.insert(v1 + " = load i32, i32* " + vName, index);
		} else if(type.equals(Constant.FUNCTION_CALL)) {
			v1 = getValue(expr, null, null, index);
		}

		String op = (String) expr.get(Constant.OPERATOR);
		if(op != null) {
			Map<String, Object> map = ((List<Map<String, Object>>) expr.get(Constant.VALUE)).get(0);
			map = ((List<Map<String, Object>>) map.get(Constant.VALUE)).get(0);
			String v2 = getPosition(map, index);
			String str = "%str_value" + emitter.getCountVars();
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

	@SuppressWarnings("unchecked")
	private void addFunctionCall(Map<String, Object> funcCall, int index) {
		String funcName = (String) funcCall.get(Constant.VARIABLE);
		List<Map<String, Object>> ps = (List<Map<String, Object>>) funcCall.get(Constant.PARAMETERS);
		String params = getParamsFunctionCall(ps, index);
		String rType = (String) ((Triple<List<?>, Map<String, Object>, Object>)context.getFunction(funcName)).getSecond().get(Constant.TYPE);
		String size = getSize(rType);
		if(funcName.equals(Constant.PRINT_F)) {
			emitter.insert("call " + size + "(i8*, ...) @" + funcName + "(" + params + ")", index);
		} else {
			emitter.insert("call " + size + " @" + funcName + "(" + params + ")", index);
		}
	}

	@SuppressWarnings("unchecked")
	private void addBooleanStatements(Map<String, Object> state, int index, String type) {
		//TODO evaluate not operator
		String bExpr = "%bExpr_" + emitter.getCountVars();
		emitter.insert("br label " + bExpr, index);
		emitter.insert(bExpr.substring(1, bExpr.length()) + ":", index);
		String endLabel = "%end_label" + emitter.getCountVars();
		String ifLabel = "%if_label" + emitter.getCountVars();
		String elseLabel = "%else_label" + emitter.getCountVars();
		BooleanExpressionUtils booleanExpr = new BooleanExpressionUtils(emitter);
		getBooleanExpr(state, index, null, booleanExpr);

		String var = booleanExpr.solveExpr(index);
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
	private void getBooleanExpr(Map<String, Object> state, int index, String exprVar, BooleanExpressionUtils bExpr) {
		String type = (String) state.get(Constant.VALUE_TYPE);
		String op = (String) state.get(Constant.OPERATOR);
		String opType = Constant.getOperatorType(op);
		String tempVar = "%temp_var" + emitter.getCountVars();

		if(type.equals(Constant.BOOLEAN)) {
			String value = (String) state.get(Constant.VALUE);

			value = getBooleanValue(value);
			emitter.insert(tempVar + " = icmp eq i32 " + value + ", 1", index);
			bExpr.addConstrainst(tempVar, opType);
		} else if(type.equals(Constant.VARIABLE)) {
			String value = (String) state.get(Constant.VALUE);

			if(context.hasVar(value)) {
				value = emitter.getConst(value);
			} else {
				value = emitter.getPointerName(value);
			}
			String actualType = (String) state.get(Constant.VALUE_ACTUAL_TYPE);
			String size = getSize(actualType);
			emitter.insert(tempVar + " = load " + size + ", " + size + "* " + value, index);
			bExpr.addConstrainst(tempVar, opType);
		} else if(type.equals(Constant.FUNCTION_CALL)) {
			tempVar = getValue(state, null, null, index);
			bExpr.addConstrainst(tempVar, opType);
		} else if(type.equals(Constant.EXPR)) {
			Map<String, Object> expr = ((List<Map<String, Object>>) state.get(Constant.VALUE)).get(0);
			tempVar = getExprValue(expr, tempVar, null, null, index);
			bExpr.addConstrainst(tempVar, opType);

		} else if(type.equals(Constant.INT)) {
			String value = (String) state.get(Constant.VALUE);

			value = (String) state.get(Constant.BOOLEAN_VALUE);
			emitter.insert(tempVar + " = add i32 0, " + value, index);
			bExpr.addConstrainst(tempVar, opType);
		}
		if(op != null) {
			bExpr.addOperator(op);
			Map<String, Object> nextExpr = ((List<Map<String, Object>>) state.get(Constant.VALUE_BOOLEAN)).get(0);
			nextExpr =  ((List<Map<String, Object>>) nextExpr.get(Constant.VALUE_BOOLEAN)).get(0);
			getBooleanExpr(nextExpr, index, exprVar, bExpr);
		}
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

	@SuppressWarnings("unchecked")
	private void addVariable(Map<String, Object> statement, int index) {
		String type = (String) statement.get(Constant.TYPE);
		String name = (String) statement.get(Constant.NAME);
		boolean isArray = (boolean) statement.get(Constant.IS_ARRAY);
		List<Map<String, Object>> pos = (List<Map<String, Object>>) statement.get(Constant.POSITION);
		if(pos != null) {
			assignmentToPosition(statement, index);
		} else {
			if(type != null) {
				String size = getSize((String) statement.get(Constant.TYPE));
				if(isArray) {
					size += "*";
				}
				String v = emitter.getPointerName(name);
				String value = getValue(statement, name, size, index);
				if(value != null && value.matches("\"(.+?)\"")) {
					emitter.insert(v + " = alloca " + size, index);
					String strName = "@.str." + emitter.getStringCount();
					value = value.substring(1, value.length() - 1);
					emitter.insert( strName + " = private unnamed_addr constant [" + (value.length() + 1) 
							+ " x i8] c\"" + value + "\\00\"", 0);
					emitter.insert("store " + size + " getelementptr inbounds ([" + (value.length() + 1) 
							+ " x i8], [" + (value.length() + 1) + " x i8]* " + strName + " , i64 0, i64 0), " 
							+ size + "* " + v, index);
				} else {
					emitter.insert(v + " = alloca " + size, index);
					emitter.insert("store " + size + " " + value + ", " + size + "* " + v, index);
				}
			} else {
				String value = getValue(statement, name, null, index);
				String size = getSize((String) statement.get(Constant.VALUE_TYPE)) != null ? getSize((String) statement.get(Constant.VALUE_TYPE)) : "i32";

				if(context.hasVar(name)) {
					name = emitter.getConst(name);
				} else {
					name = emitter.getPointerName(name);
				}

				if(value.equals("true") || value.equals("false")) {
					value = getBooleanValue(value);
					emitter.insert("store " + size + " " + value + ", " + size + "* " + name, index);
				} else if(value.startsWith("\"")) {
					String strName = "@.str." + emitter.getStringCount();
					value = value.substring(1, value.length() - 1);
					emitter.insert( strName + " = private unnamed_addr constant [" + (value.length() + 1) 
							+ " x i8] c\"" + value + "\\00\"", 0);
					emitter.insert("store " + size + " getelementptr inbounds ([" + (value.length() + 1) 
							+ " x i8], [" + (value.length() + 1) + " x i8]* " + strName + " , i64 0, i64 0), " 
							+ size + "* " + name, index);
				} else {
					emitter.insert("store " + size + " " + value + ", " + size + "* " + name, index);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void assignmentToPosition(Map<String, Object> statement, int index) {
		String type = (String) statement.get(Constant.VALUE_TYPE);
		String size = getSize(type);
		String value;
		String name = (String) statement.get(Constant.NAME);
		if(context.hasVar(name)) {
			name = emitter.getConst(name);
		} else {
			name = emitter.getPointerName(name);
		}

		String strName = "";

		if(type.equals(Constant.STRING)) {
			value = getValue(statement, type, size, index);
			strName = "@.str." + emitter.getStringCount();
			value = value.substring(1, value.length() - 1);
			emitter.insert( strName + " = private unnamed_addr constant [" + (value.length() + 1) 
					+ " x i8] c\"" + value + "\\00\"", 0);
		} else {
			value = getValue(statement, type, size, index);
		}

		boolean isArray = (boolean) statement.get(Constant.IS_ARRAY);
		if(isArray) {
			size += "*";
		}
		String tempVar = "%temp_var" + emitter.getCountVars();
		emitter.insert(tempVar + " = load " + size + ", " + size + "* " + name, index);
		String tempVar2 = "%temp_var" + emitter.getCountVars();
		Map<String, Object> pos = ((List<Map<String, Object>>)statement.get(Constant.POSITION)).get(0);
		pos = ((List<Map<String, Object>>)pos.get(Constant.VALUE)).get(0);
		String position = getPosition(pos, 0);
		emitter.insert(tempVar2 + " = getelementptr inbounds " + size.substring(0, size.length() - 1) 
		+ ", " + size + " " + tempVar + ", i64 " + position, index);
		storeNewValue(size, value, type, tempVar2, index, strName);
	}

	private void storeNewValue(String size, String value, String type, String tempVar2, int index, String strName) {
		switch(type) {
		case Constant.STRING:
			emitter.insert("store " + size.substring(0, size.length()-1) + " getelementptr inbounds"
					+ "([" + (value.length() + 1) + " x i8], [" + (value.length() + 1) + " x i8]*"
					+ " " + strName + ", i64 0, i64 0), " + size + " " + tempVar2, index);
			break;
		case Constant.INT:
		case Constant.DOUBLE:
			emitter.insert("store " + size.substring(0, size.length() - 1) + " " + value 
					+ ", " + size + " " + tempVar2, index);
			System.out.println("");
			break;			
		}
	}

	private String getParams(List<Map<String, Object>> params) {
		StringBuilder bob = new StringBuilder();
		for(int i = 0; i < params.size(); i++) {
			Map<String, Object> param = params.get(i);
			String name = (String) param.get(Constant.NAME);
			name = "%" + name;
			Object o = param.get(Constant.IS_ARRAY);
			String type = (String) param.get(Constant.TYPE);
			String size = getSize(type);
			if(o != null) {
				boolean isArray = (boolean) o;
				if(isArray) {
					bob.append(size + "* " + name);
				} else {
					bob.append(size + " " + name);
				}
			} else {
				bob.append(size + " " + name);
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
				&& !type.equals(Constant.STRING) && !type.equals(Constant.ARRAYS)) {
			return (String) child.get(Constant.VALUE);
		} else {
			if(type.equals(Constant.FUNCTION_CALL)) {
				String v1 = "%callFunction_" + emitter.getCountVars();
				Map<String, Object> func = ((List<Map<String, Object>>)((List<Map<String, Object>>) child.get(Constant.VALUE_FUNC)).get(0)
						.get(Constant.FUNCTION)).get(0);
				String funcName = (String) func.get(Constant.VARIABLE);
				String params = getParamsFunctionCall((List<Map<String, Object>>) func.get(Constant.PARAMETERS), index);
				String rType = (String) ((Triple<List<?>, Map<String, Object>, Object>)context.getFunction(funcName)).getSecond().get(Constant.TYPE);
				size = getSize(rType);
				boolean isArray = child.get(Constant.IS_ARRAY) != null ? (boolean) child.get(Constant.IS_ARRAY) : false;
				boolean isMatrix = child.get(Constant.IS_MATRIX) != null ? (boolean) child.get(Constant.IS_MATRIX) : false;
				if(isArray) {
					size += "*";
				}
				if(isMatrix) {
					size += "*";
				}

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
			} else if(type.equals(Constant.ARRAYS)) {
				List<Map<String, Object>> value = (List<Map<String, Object>>) child.get(Constant.VALUE);
				return addArrays(value.get(0), index);
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
	private String getParamsFunctionCall(List<Map<String, Object>> params, int index) {
		StringBuilder bob = new StringBuilder();
		for(int i = 0; i < params.size(); i++) {
			Map<String, Object> param = params.get(i);
			String valueType = (String) param.get(Constant.VALUE_TYPE);
			String size;
			if(valueType != null && !valueType.equals(Constant.VARIABLE) 
					&& !valueType.equals(Constant.FUNCTION_CALL) && !valueType.equals(Constant.ARRAYS)) {
				size = getSize(valueType);
			} else {
				String actualValueType = (String) param.get(Constant.VALUE_ACTUAL_TYPE);
				size = getSize(actualValueType);
			}

			boolean isArray = param.get(Constant.IS_ARRAY) != null ? (boolean) param.get(Constant.IS_ARRAY) : false;
			if(isArray) {
				size += "*";
			}
			if(valueType != null) {
				if(valueType.equals(Constant.INT) || valueType.equals(Constant.DOUBLE)) {
					String value = (String) param.get(Constant.VALUE);
					bob.append(size + " " + value);
				} else if(valueType.equals(Constant.VARIABLE)) {
					String var = "%new_var" + emitter.getCountVars();
					String varName = (String) param.get(Constant.VALUE);
					String v;
					if(context.hasVar(varName) ) {
						v = emitter.getConst(varName);
					} else {
						v = emitter.getPointerName(varName);
					}
					emitter.insert(var + " = load " + size + ", " + size + "* " + v);
					bob.append(size + " " + var);
				} else if(valueType.equals(Constant.STRING)) {
					String strName = "@.str." + emitter.getStringCount();
					String value = (String) param.get(Constant.VALUE);
					value = value.substring(1, value.length() - 1);
					emitter.insert( strName + " = private unnamed_addr constant [" + (value.length() + 1) 
							+ " x i8] c\"" + value + "\\00\"", 0);
					bob.append("i8* getelementptr inbounds ([" + (value.length() + 1) + " x i8], [" + (value.length() + 1) + " x i8]* " + strName + " , i64 0, i64 0)");
				} else if(valueType.equals(Constant.BOOLEAN)) {
					String value = (String) param.get(Constant.VALUE);
					if(value.equals(Constant.FALSE)) {
						bob.append("i1 0");
					} else {
						bob.append("i1 1");
					}
				} else if(valueType.equals(Constant.EXPR)) {
					Map<String, Object> expr = ((List<Map<String, Object>>)param.get(Constant.VALUE)).get(0);
					String value = getExprValue(expr, null, null, null, index);
					bob.append("i32 " + value);
				} else if(valueType.equals(Constant.ARRAYS)) {
					Map<String, Object> position = ((List<Map<String, Object>>) param.get(Constant.VALUE)).get(0);
					String varName = (String) position.get(Constant.VARIABLE);
					String v;
					if(context.hasVar(varName) ) {
						v = emitter.getConst(varName);
					} else {
						v = emitter.getPointerName(varName);
					}
					String var = "%new_var" + emitter.getCountVars();
					emitter.insert(var + " = load " + size + ", " + size + "* " + v);

					position = ((List<Map<String, Object>>) position.get(Constant.VALUE)).get(0);
					String pos = getPosition(position, index);
					String tempVar = "%temp_var" + emitter.getCountVars();
					emitter.insert(tempVar + " = getelementptr inbounds " + size.substring(0, size.length() - 1) + ", " +size  + " " + var + ", i64 " + pos, index);
					String tempVar1 = "%temp_var" + emitter.getCountVars();
					emitter.insert(tempVar1 + " = load " + size.substring(0, size.length() - 1) + ", " + size + " " + tempVar, index);
					bob.append(size.substring(0, size.length() - 1) + " " + tempVar1);
				}
			} else {
				String temp = "%temp_" + emitter.getCountVars();
				Map<String, Object> function = ((List<Map<String,Object>>) param.get(Constant.FUNCTION)).get(0);
				String funcName = (String) function.get(Constant.VARIABLE);
				String paramsFunc = getParamsFunctionCall((List<Map<String, Object>>) function.get(Constant.PARAMETERS), index);
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
			Triple<List<String>, Boolean, Object> pair = (Triple<List<String>, Boolean, Object>) context.getType(value);
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
		if(type.equals(Constant.INT)) {
			v1 = (String) expr.get(Constant.EXPRESSION_VALUE);
		} else if(type.equals(Constant.DOUBLE)) {
			String tempVar = "%temp_var" + emitter.getCountVars();
			String value  = (String) expr.get(Constant.EXPRESSION_VALUE);
			emitter.insert(tempVar + " = alloca double", index);
			emitter.insert("store double " + value + ", double* " + tempVar, index);
			String intResult = "%load_double" + emitter.getCountVars();
			emitter.insert(intResult + " = load double, double* " + tempVar, index);
			v1 = "%temp_var" + emitter.getCountVars();
			emitter.insert(v1 + " = fptosi double " +  intResult + " to i32", index);
		} else if(type.equals(Constant.VARIABLE)) {
			v1 = "%tempV" + emitter.getCountVars();
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
			switch(op) {
			case "*":
				emitter.insert(str + " = mul i32 " + v1 + " , " + v2, index);
				break;
			case "/":
				emitter.insert(str + " = sdiv i32 " + v1 + " , " + v2, index);
				break;
			case "+":
				emitter.insert(str + " = add i32 " + v1 + " , " + v2, index);
				break;
			case "-":
				emitter.insert(str + " = sub i32 " + v1 + " , " + v2, index);
				break;
			case "%":
				emitter.insert(str + " = srem i32 " + v1 + " , " + v2, index);
				break;
			}	
			v1 = str;
		}
		return v1;
	}
}

