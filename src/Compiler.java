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
		String size = getSize(func, type);
		emitter.insert("define " + size  + " @" + name + "(" + params + ") #" + fCount + " {");
		
		int index = emitter.getCount();
		List<Map<String, Object>> statements = (List<Map<String, Object>>) func.get(Constant.STATEMENT);
		addStatements(statements);
		
		Map<String, Object> rValue = ((List<Map<String, Object>>) func.get(Constant.RETURN_STATEMENT)).get(0);
		setReturnStatement(rValue, size, index);
		emitter.resetVars();
	}

	private String getSize(Map<String, Object> func, String type) {	
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
			String value = getValue(map, null);
			if(value.matches("\\d+\\.?\\d*")) {
				emitter.insert("ret " + size +  " " + value + "\n}", index);
			} else {
				String varName;
				if(emitter.hasVar(value)) {
					varName = emitter.getPointerName(value);
				} else {
					varName = emitter.getConst(value);
				}
				
				String newVar = "%ret_" + emitter.getCountVars();
				emitter.incrementCountVars();
				emitter.insert(newVar + " = alloca " + size, index);
				emitter.insert("store " + size + " " + varName + ", " + size + "* " + newVar, index);
				String newVar1 = "%ret_" + emitter.getCountVars();

				emitter.insert(newVar1 + " = load " + size + ", " + size + "* " + newVar, index);
				emitter.insert("ret " + size + " " + newVar1 + "\n}", index);
			}
		} else {
			emitter.insert("ret void\n}", index);
		}
	}

	@SuppressWarnings("unchecked")
	private void addStatements(List<Map<String, Object>> statements) {
		for(Map<String, Object> statement : statements) {
			if(statement.containsKey(Constant.FUNCTION)) {
				for(Map<String, Object> map : (List<Map<String, Object>>) statement.get(Constant.FUNCTION)) {
					emitter.incrementCount();
					addFunctions(map);
					List<Map<String, Object>> states = (List<Map<String, Object>>) map.get(Constant.STATEMENT);
					addStatements(states);
				}
			} else if(statement.containsKey(Constant.VARIABLE)) {
				addVariable(((List<Map<String, Object>>)statement.get(Constant.VARIABLE)).get(0));
			}
		}
	}

	private void addVariable(Map<String, Object> statement) {
		String size = getSize(statement, (String) statement.get(Constant.TYPE));
		String name = (String) statement.get(Constant.NAME);
		String v = emitter.getPointerName(name);
		String value = getValue(statement, name);
		if(value != null && value.matches("\"(.+?)\"")) {
			emitter.insert(v + " = alloca " + size);
			String strName = "@.str." + emitter.getStringCount();
			emitter.incrementStringCount();
			value = value.substring(1, value.length() - 1);
			emitter.insert( strName + " = private unnamed_addr constant [" + (value.length() + 1) 
					+ " x i8] c\"" + value + "\\00\"", 0);
			emitter.insert("store " + size + " getelementptr inbounds ([" + (value.length() + 1) 
					+ " x i8], [" + (value.length() + 1) + " x i8]* " + strName + " , i64 0, i64 0), " 
					+ size + "* " + v);
		} else {
			emitter.insert(v + " = alloca " + size);
			emitter.insert("store " + size + " " + value + " , " + size + "* " + v);
		}
	}

	private String getParams(List<Map<String, Object>> params) {
		StringBuilder bob = new StringBuilder();
		for(int i = 0; i < params.size(); i++) {
			Map<String, Object> param = params.get(i);
			String name = (String) param.get(Constant.NAME);
			emitter.addVar(name);
			name = emitter.getPointerName(name);
			boolean isArray = (boolean) param.get(Constant.IS_ARRAY);
			if(isArray) {
				bob.append("i32* " + name);
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
				String value = getValue(var, name);
				if(value.matches("\"(.+?)\"")) {
					String strName = "@.str." + emitter.getStringCount();
					emitter.incrementStringCount();
					value = value.substring(1, value.length() - 1);
					emitter.insert( strName + " = private unnamed_addr constant [" + (value.length() + 1) + "x i8] c\"" + value + "\\00\"");
					emitter.insert(v + "= global i8* getelementptr inbounds ([" 
					+ (value.length() + 1) + " x i8], [" + (value.length() + 1) + " x i8]* "+ strName + ", i32 0, i32 0)");
				} else {
					emitter.insert(v + "= global i32 " + value);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private String getValue(Map<String, Object> child, String varName) {
		String type = (String) child.get(Constant.VALUE_TYPE);
		if(!type.equals(Constant.FUNCTION_CALL) && !type.equals(Constant.VARIABLE) 
				&& !type.equals(Constant.EXPR) && !type.equals(Constant.BOOLEAN)
				&& !type.equals(Constant.STRING)) {
			return (String) child.get(Constant.VALUE);
		} else {
			if(type.equals(Constant.FUNCTION_CALL)) {
				//TODO
			} else if(type.equals(Constant.EXPR)) {
				Map<String, Object> expr = ((List<Map<String, Object>>) child.get(Constant.VALUE)).get(0);
				return getExprValue(expr, null, null);
			} else if(type.equals(Constant.VARIABLE)) {
				return getVariableValue(child);
			} else if(type.equals(Constant.BOOLEAN)){
				String value = (String) child.get(Constant.VALUE);
				if(value.equals(Constant.TRUE)) {
					return "1";
				} else {
					return "0";
				}
			} else {
				String value = (String) child.get(Constant.VALUE);
				return value;
			}
		}
		return null;
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
	private String getExprValue(Map<String, Object> expr, String tempPointer, String operator) {
		String type = (String) expr.get(Constant.VALUE_TYPE);
		String v1 = "";
		if(type.equals(Constant.DOUBLE) || type.equals(Constant.INT) || type.equals(Constant.FLOAT)) {
			v1 = (String) expr.get(Constant.EXPRESSION_VALUE);
		} else if(type.equals(Constant.VARIABLE)) {
			v1 = "%tempV";
			String vName;
			String varName = (String) expr.get(Constant.EXPRESSION_VALUE);
			if(context.hasVar(varName)) {
				vName = emitter.getConst(varName);
			} else {
				vName = emitter.getPointerName(varName);
			}
			emitter.insert(v1 + " = load i32, i32* " + vName);
		} else {
			//TODO - function_call
		}
			
		String op = (String) expr.get(Constant.OPERATOR);
		if(op != null) {
			Map<String, Object> map = ((List<Map<String, Object>>) expr.get(Constant.VALUE)).get(0);
			map = ((List<Map<String, Object>>) map.get(Constant.VALUE)).get(0);
			String v2 = getExprValue(map, v1, op);
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

