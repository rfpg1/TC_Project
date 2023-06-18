package utils;

import java.util.ArrayList;
import java.util.List;

public class BooleanExpressionUtils {

	private List<String> constraints;
	private List<String> operators;
	private Emitter emitter;
	private boolean isMath;
	private List<String> priorityConstraints;
	private List<String> priorityOperators;
	
	public BooleanExpressionUtils(Emitter emitter) {
		constraints = new ArrayList<>();
		operators = new ArrayList<>();
		priorityOperators = new ArrayList<>();
		priorityConstraints = new ArrayList<>();
		this.emitter = emitter;
		isMath = false;
	}
	
	public void addConstrainst(String s, String opType) {
		if(isMath || (opType != null && opType.equals(Constant.MATH))) {
			isMath = !isMath;
			priorityConstraints.add(s);
		} else {
			constraints.add(s);
		}
	}
	
	public void addOperator(String op) {
		if(isMath) {
			priorityOperators.add(op);
		} else {
			operators.add(op);
		}
	}

	public String solveExpr(int index) {
		
		String tempVar = solvePriority(index);
		if(operators.size() == 0 && constraints.size() == 1) {
			tempVar = constraints.get(0);
		} else {
			for(String operator : operators) {
				String first = constraints.get(0);
				String second = constraints.get(1);
				tempVar = "%temp_var" + emitter.getCountVars();
				switchCase(operator, tempVar, first, second, index);
				constraints.add(0, tempVar);
			}
		}
		
		constraints = new ArrayList<>();
		operators = new ArrayList<>();
		return tempVar;
	}

	private String solvePriority(int index) {
		String tempVar = null;
		for(String operator : priorityOperators) {
			String first = priorityConstraints.get(0);
			String second = priorityConstraints.get(1);
			tempVar = "%temp_var" + emitter.getCountVars();
			switchCase(operator, tempVar, first, second, index);
			priorityConstraints.add(0, tempVar);
		}
		if(tempVar != null) {
			constraints.add(tempVar);
		}
		priorityConstraints = new ArrayList<>();
		priorityOperators = new ArrayList<>();
		return tempVar;
	}

	private void switchCase(String op, String tempVar, String first, String second, int index) {
		switch(op) {
		case "||":
			emitter.insert(tempVar + " = or i1 " + first + ", " + second, index);
			break;
		case "&&":
			emitter.insert(tempVar + " = and i1 " + first + ", " + second, index);
			break;
		case ">":
			emitter.insert(tempVar + " = icmp sgt i32 " + first + ", " + second, index);
			break;
		case ">=":
			emitter.insert(tempVar + " = icmp sge i32 " + first + ", " + second, index);
			break;
		case "<":
			emitter.insert(tempVar + " = icmp slt i32 " + first + ", " + second, index);
			break;
		case "<=":
			emitter.insert(tempVar + " = icmp sle i32 " + first + ", " + second, index);
			break;
		case "==":
			emitter.insert(tempVar + " = icmp eq i32 " + first + ", " + second, index);
			break;
		case "!=":
			emitter.insert(tempVar + " = icmp ne i32 " + first + ", " + second, index);
			break;
		}
	}
}
