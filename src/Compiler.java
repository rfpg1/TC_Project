import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
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

//			for(Map<String, Object> child : tree) {
//				if(child.containsKey(Constant.VARIABLE)) {
//					Map<String, Object> var = ((List<Map<String, Object>>) child.get(Constant.VARIABLE)).get(0);
//					//String var = addVariable(var);
//				}
//			}
		}
		emitter.generateFile();
	}

	@SuppressWarnings("unchecked")
	private void addConstants() {
		for(Map<String, Object> child : tree) {
			if(child.containsKey(Constant.VARIABLE)) {
				Map<String, Object> var = ((List<Map<String, Object>>) child.get(Constant.VARIABLE)).get(0);
				String name = (String) var.get(Constant.NAME);
				String v = this.emitter.getConst(name);
				String value = getValue(var, name);
			}
		}
	}

	private String addVariable(Map<String, Object> child) {
		String name = (String) child.get(Constant.NAME);
		String pointer = this.emitter.getPointerName(name);
		String value = getValue(child, name);
		System.out.println("");

		return null;
	}

	@SuppressWarnings("unchecked")
	private String getValue(Map<String, Object> child, String varName) {
		String type = (String) child.get(Constant.VALUE_TYPE);
		String value;
		if(type.equals(Constant.VARIABLE)) {
			value = (String) (((Pair<List<Object>, Boolean>)context.getType((String) child.get(Constant.VALUE))).getFirst()).get(1);
			if(context.hasVar(value)) {
				Map<String, Object> map = new LinkedHashMap<>();
				map.put(Constant.VALUE, value);
				map.put(Constant.VALUE_TYPE, Constant.VARIABLE);
				value = getValue(map, varName);
				varName = emitter.getConst(varName);
				this.emitter.addConstant(varName, value);
			}
			//TODO check if it a function call
		} else if(type.equals(Constant.FUNCTION_CALL)) {
			value = "";
		} else if(type.equals(Constant.BOOLEAN)) {
			value = (String) child.get(Constant.VALUE);
			if(value.equals(Constant.TRUE)) {
				value = "1";
			} else {
				value = "0";
			}
			varName = emitter.getConst(varName);
			this.emitter.addConstant(varName, value);

		} else if(type.equals(Constant.EXPR)) {
			Map<String, Object> expr = ((List<Map<String, Object>>) child.get(Constant.VALUE)).get(0);
			String operator = (String) expr.get(Constant.OPERATOR);
			String v = getExprValue(expr);
			if(operator != null) {
				Map<String, Object> childChild = ((List<Map<String, Object>>) expr.get(Constant.VALUE)).get(0);
				String v2 = getValue(childChild, varName);
				switch(operator) {
				case "*":
					String var = emitter.getConst(varName);
					emitter.insert(var + "= mul nsw " + v + ", " + v2);
					break;

				case "+":
					break;

				case "-":
					break;

				case "/":
					break;

				case "%":
					break;
				}
			}
			value = v;
		} else {
			value = (String) child.get(Constant.VALUE);
			varName = emitter.getConst(varName);
			this.emitter.addConstant(varName, value);
		}
		return value;
	}

	private String getExprValue(Map<String, Object> expr) {
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

