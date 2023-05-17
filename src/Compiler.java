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
					Map<String, Object> map = ((List<Map<String, Object>>) func.get(Constant.FUNCTION)).get(0);
					emitter.incrementCount();
					addFunctions(map);
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
		emitter.insert("define i32 @" + name + "(" + params + ") #" + fCount + " {");
		addStatements();
		Map<String, Object> rValue = ((List<Map<String, Object>>) func.get(Constant.RETURN_STATEMENT)).get(0);
		String returnStatement = getReturnStatement(rValue);
		emitter.insert("ret i32 " + returnStatement);
		emitter.resetVars();
	}

	private String getReturnStatement(Map<String, Object> map) {
		String value = getValue(map, null);
		if(emitter.hasVar(value)) {
			String varName = emitter.getPointerName(value);
			//\xdwadString newVar = 
		} else {
			
		}
		return value;
	}

	private void addStatements() {
		// TODO Auto-generated method stub
		
	}

	private String getParams(List<Map<String, Object>> params) {
		System.out.println("");
		StringBuilder bob = new StringBuilder();
		for(int i = 0; i < params.size(); i++) {
			Map<String, Object> param = params.get(i);
			String name = (String) param.get(Constant.NAME);
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
				Map<String, Object> expr = null;
				return getExprValue(expr, "");
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

	private String getExprValue(Map<String, Object> expr, String exp) {
		String type = (String) expr.get(Constant.VALUE_TYPE);
		if(type.equals(Constant.DOUBLE) || type.equals(Constant.INT) || type.equals(Constant.FLOAT)) {
			return (String) expr.get(Constant.EXPRESSION_VALUE);
		} else if(type.equals(Constant.VARIABLE)) {
			//TODO
		} else {
			//TODO
		}
		return null;
	}
}

