import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constant {

	public static final String PROG = "Prog";
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String TERMINAL_NODE_IMPL = "TerminalNodeImpl";
	public static final String COMMENT = "Comment";
	public static final String DEFINITION = "Definition";
	public static final String FUNCTION = "Function";
	public static final String STATEMENT = "Statement";
	public static final String IF_STATEMENT = "If_statement";
	public static final String ELSE_STATEMENT = "Else_statement";
	public static final String RETURN_STATEMENT = "Return_statement";
	public static final String OPERATOR = "Operator";
	public static final String BOOLEAN_EXPRESSION = "Boolean_expression";
	public static final String WHILE_STATEMENT = "While_statement";
	public static final String DECLARATION = "Declaration";
	public static final String VALUE = "Value";
	public static final String STRING_LIT = "String_lit";
	public static final String EXPR = "Expr";
	public static final String EXPRESSION_VALUE = "ExprValue";
	public static final String FUNCTION_CALL = "Function_call";
	public static final String EXPRESSION = "Expression";
	public static final String ARRAYS = "Arrays";
	public static final String POSITION = "Position";
	public static final String VARIABLE = "Variable";
	public static final String NUMBER = "Number";
	public static final String INT = "Int";
	public static final String DOUBLE = "Double";
	public static final String STRING = "String";
	public static final String BOOLEAN = "Boolean";
	public static final String FLOAT = "Float";
	public static final String VOID = "Void";
	public static final String VALUE_TYPE = "valueType";
	public static final String IS_ARRAY = "isArray";
	public static final String RETURN_TYPE = "returnType";
	public static final String PARAMETERS = "Parameters";
	public static final String REFINEMENT = "Refinement";
	public static final String NOT = "Not";
	public static final String BOOLEAN_VALUE = "BooleanValue";
	public static final String POSITION_VALUE = "PositionValue";
	public static final String MATH = "Math";
	public static final String VALUE_FUNC = "ValueFunc";
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	public static final String VALUE_BOOLEAN = "ValueBoolean";
	public static final String ARRAY_TYPE = "ArrayType";

	public static final int VARIABLE_TYPE = 36;
	public static final int BOOLEAN_TYPE = 8;
	public static final int NUMBER_INT_TYPE = 28;
	public static final int NUMBER_DOUBLE_TYPE = 29;
	public static final String[] MATH_OPERATOR = {"*", "+", "-", "/", "%", "<", ">", ">=", "<=", "=="};
	public static final String[] BOOLEAN_OPERATOR = {"&&", "||"};
	
	public static final String PRINT_F = "printf";
	public static final String[] PLACE_HOLDERS = {"%s", "%i", "%f", "%d"};
	
	public static String getOperatorType(String op) {
		List<String> mathOperator = new ArrayList<>(Arrays.asList(Constant.MATH_OPERATOR));
		List<String> booleanOperator = new ArrayList<>(Arrays.asList(Constant.BOOLEAN_OPERATOR));
		if(mathOperator.contains(op)) {
			return Constant.MATH;
		} else if(booleanOperator.contains(op)) {
			return Constant.BOOLEAN;
		}
		return null;
	}
}
