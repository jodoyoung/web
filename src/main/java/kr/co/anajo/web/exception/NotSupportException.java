package kr.co.anajo.web.exception;

public class NotSupportException extends RuntimeException {

	private static final long serialVersionUID = 375420323798549217L;

	public NotSupportException() {
		super();
	}

	public NotSupportException(String message) {
		super(message);
	}

	public NotSupportException(Throwable cause) {
		super(cause);
	}

	public NotSupportException(String message, Throwable cause) {
		super(message, cause);
	}

}
