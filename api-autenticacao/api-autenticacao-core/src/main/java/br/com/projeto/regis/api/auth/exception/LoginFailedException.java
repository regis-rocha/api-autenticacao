package br.com.projeto.regis.api.auth.exception;

public class LoginFailedException extends Exception {
	
	/**
	 * serial version
	 */
	private static final long serialVersionUID = -5528201448495991267L;

	public LoginFailedException(String message) {
		super(message);
	}
	
	public LoginFailedException(String message, Throwable exception) {
		super(message, exception);
	}
	
	public LoginFailedException(Throwable exception) {
		super(exception);
	}
}