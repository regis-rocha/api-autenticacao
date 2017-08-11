package br.com.projeto.regis.api.auth.exception;

public class InvalidTokenAndAccountException extends Exception {
	
	/**
	 * serial version
	 */
	private static final long serialVersionUID = -8667089262461985624L;

	public InvalidTokenAndAccountException(String message) {
		super(message);
	}
	
	public InvalidTokenAndAccountException(String message, Throwable exception) {
		super(message, exception);
	}
	
	public InvalidTokenAndAccountException(Throwable exception) {
		super(exception);
	}
}
