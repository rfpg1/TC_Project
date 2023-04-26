package exception;

public class FunctionAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public FunctionAlreadyExistsException(String message) {
		super(message);
	}
}
