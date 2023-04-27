package exception;

public class InvalidReturnException extends CompilerException {
	
	private static final long serialVersionUID = 1L;

	public InvalidReturnException(String message) {
		super(message);
	}
}
