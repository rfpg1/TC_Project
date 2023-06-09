package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import exception.CompilerException;

public class Context {

	private Stack<Map<String, Object>> stack;
	private Stack<Map<String, Object>> functionStack;
	private Stack<List<String>> functionScopes;
	private int head;
	private List<String> definitions;
	
	public Context() {
		this.stack = new Stack<>();
		this.stack.push(new LinkedHashMap<>());
		this.functionScopes = new Stack<>();
		this.functionScopes.push(new ArrayList<>());
		this.functionStack = new Stack<>();
		this.functionStack.push(new LinkedHashMap<>());
		this.head = 0;
		this.definitions = new ArrayList<>();
	}
	
	public void insertFunction(String name) {
		if(!functionScopes.get(head).contains(name))
			functionScopes.get(head).add(name);
	}
	
	public Object getType(String name) {
		for(int i = stack.size() - 1; i >= 0; i--) {
			Map<String, Object> scope = stack.get(i);
			if(scope.keySet().contains(name)) {
				return scope.get(name);
			}
		}
		return null;
	}
	
	public void setType(String name, Object o, boolean array, boolean matrix, Object value, boolean isConstant) {
		Map<String, Object> scope = stack.get(head);
		List<Object> objs = new ArrayList<>(Arrays.asList(o, value, isConstant));
		scope.put(name, new Triple<List<Object>, Boolean, Boolean>(objs, array, matrix));
	}
	
	public Object getFunction(String functionName) {
		for(int i = 0; i < functionStack.size(); i++) {
			Map<String, Object> scope = functionStack.get(i);
			if(scope.keySet().contains(functionName)) {
				return scope.get(functionName);
			}
		}
		return null;
	}
	
	public void setFunction(String name, Object o) {
		Map<String, Object> scope = functionStack.get(0);
		scope.put(name, o);
	}
	
	public boolean hasVarInCurrentScope(String name) {
		return stack.get(head).keySet().contains(name);
	}
	
	public boolean hasVar(String name) {
		for(int i = 0; i < stack.size(); i++) {
			Map<String, Object> scope = stack.get(i);
			if(scope.keySet().contains(name)) {
				return true;
			}
		}
		return false;
	}
	
	public void enterScope() {
		head++;
		stack.push(new LinkedHashMap<>());
		functionScopes.push(new ArrayList<>());
	}
	
	public void exitScope() {
		head--;
		stack.pop();
		functionScopes.pop();
	}

	public boolean hasFunction(String name) {
		Map<String, Object> scope = functionStack.get(0);
		
		return scope.keySet().contains(name);
	}

	public void setCurrentFunction(String name) {
		if(name != null) {
			this.definitions.add(name);
		} else {
			this.definitions.remove(this.definitions.size() - 1);
		}
	}	
	
	public String getCurrentFunction() throws CompilerException {
		if(this.definitions.size() < 1) {
			return null;
		}
		return this.definitions.get(this.definitions.size() - 1);
	}

	@SuppressWarnings("unchecked")
	public boolean isArray(String vName) {
		Triple<String, Object, Object> a = (Triple<String, Object, Object>) this.getType(vName);
		return (Boolean)a.getSecond();
	}

	public boolean hasVarInPreviousScopes(String name) {
		for(int i = stack.size() - 1; i > 0; i--) {
			Map<String, Object> scope = stack.get(i);
			if(scope.keySet().contains(name)) {
				return true;
			}
		}
		return false;
	}

	public Stack<Map<String, Object>> getStack() {
		return stack;
	}

	public boolean functionInScope(String funcName) {
		for(int i = 0; i < functionScopes.size(); i++) {
			List<String> array = functionScopes.get(i);
			for(String s : array) {
				if(s.equals(funcName)) {
					return true;
				}
			}
		}
		return false;
	}
}
