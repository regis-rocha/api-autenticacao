package br.com.projeto.regis.api.auth.exception;

public class LoginInvalidException extends Exception {
	
	/**
	 * serial version
	 */
	private static final long serialVersionUID = -5528201448495991267L;

	public LoginInvalidException(String message) {
		super(message);
	}
	
	public LoginInvalidException(String message, Throwable exception) {
		super(message, exception);
	}
	
	public LoginInvalidException(Throwable exception) {
		super(exception);
	}
}