package br.com.projeto.regis.api.auth.exception;

public class AuthException extends Exception {
	
	/**
	 * serial version
	 */
	private static final long serialVersionUID = 2993886433038776741L;

	public AuthException(String message) {
		super(message);
	}
	
	public AuthException(String message, Throwable exception) {
		super(message, exception);
	}
	
	public AuthException(Throwable exception) {
		super(exception);
	}
}
