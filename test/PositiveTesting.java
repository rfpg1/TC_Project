import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exception.CompilerException;

class PositiveTesting {

	private static List<String> files = new ArrayList<>();
	
	@BeforeAll
	public static void setUp() {
		File tests = new File("tests/positive");
		for(File test : tests.listFiles()) {
			System.out.println();
			files.add(test.getName());
		}
	}
	
	@Test
	void test() {
		for(String file : files) {
			String[] args = new String[]{"tests"+ File.separator + "positive" + File.separator + file};
			try {
				Main.main(args);
			} catch (IOException | CompilerException | NullPointerException e) {
				System.out.println(file);
				System.out.println(e.getMessage());
				assertFalse(false);
			}
		}
		assertTrue(true);
	}
}

