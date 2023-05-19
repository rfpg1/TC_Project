import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Emitter {

	private int count;
	private List<List<String>> lines;
	private int functionCount;
	private int stringCount;
	private List<String> vars;
	private int countVars;
 
	public Emitter() {
		this.count = 0;
		this.functionCount = 0 ;
		this.lines = new ArrayList<>();
		this.stringCount = 0;
		this.vars = new ArrayList<>();
		this.countVars = 0;
	}	
	
	public int getCountVars() {
		return countVars;
	}

	public void incrementCountVars() {
		this.countVars++;
	}

	public void addVar(String var) {
		this.vars.add(var);
	}
	
	public int getFunctionCount() {
		return functionCount;
	}

	public void incrementFuntionCount() {
		this.functionCount++;
	}
	
	public int getStringCount() {
		return stringCount;
	}

	public void incrementStringCount() {
		this.stringCount++;
	}

	public void insert(String line) {
		if(count < this.lines.size()) {
			List<String> s = this.lines.get(count);
			s.add(line);
		} else {
			List<String> s = new ArrayList<>(Arrays.asList(line));
			this.lines.add(count, s);
		}
	}
	
	public void insert(String line, int index) {
		if(index < this.lines.size()) {
			List<String> s = this.lines.get(index);
			s.add(line);
		} else {
			List<String> s = new ArrayList<>(Arrays.asList(line));
			this.lines.add(index, s);
		}
	}
	
	public void incrementCount() {
		this.count++;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public List<String> get(int index){
		return this.lines.get(index);
	}
	
	public String getPointerName(String name) {
		return "%pont_" + name;
	}
	
	public String getConst(String name) {
		return "@global_" + name;
	}
	
	public void generateFile() throws FileNotFoundException {
		//TODO
		StringBuilder bob = new StringBuilder();
		for(List<String> l : lines) {
			for(String s : l) {
				bob.append(s + "\n");
			}
		}
		
		PrintWriter pw = new PrintWriter(new File("Main.ll"));
		pw.append(bob.toString());
		pw.close();
	}

	public void addConstant(String v, String value) {
		String line = v + " = global i32 " + value;
		insert(line);
	}

	public boolean hasVar(String value) {
		return vars.contains(value);
	}
	
	public void resetVars() {
		this.vars = new ArrayList<>();
	}
	
}