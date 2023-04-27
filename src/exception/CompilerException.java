package exception;

public abstract class CompilerException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public CompilerException(String message) {
		super(message);
	}
}
