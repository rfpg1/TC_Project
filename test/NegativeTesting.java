

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exception.CompilerException;

class NegativeTesting {
	
	private static List<String> files = new ArrayList<>();
	
	@BeforeAll
	public static void setUp() {
		File tests = new File("tests/negative");
		for(File test : tests.listFiles()) {
			if(!test.getName().endsWith(".ll") ) {
				files.add(test.getName());
			}
		}
	}

	@Test
	void test() {
		for(String file : files) {
			String[] args = new String[]{"tests"+ File.separator + "negative" + File.separator + file};
			assertThrows(CompilerException.class, () -> Main.main(args));			
		}
		assertTrue(true);
	}

}
