import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Emitter {

	private int count;
	private List<List<String>> lines;

	public Emitter() {
		this.count = 0;
		this.lines = new ArrayList<>();
	}
	
	public void insert(String line) {
		List<String> s = new ArrayList<>(Arrays.asList(line));
		this.lines.add(count, s);
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
	
}