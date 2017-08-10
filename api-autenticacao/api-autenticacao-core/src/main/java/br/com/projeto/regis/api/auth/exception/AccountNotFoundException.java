package br.com.projeto.regis.api.auth.exception;

public class AccountNotFoundException extends Exception {
	
	/**
	 * serial version
	 */
	private static final long serialVersionUID = -6052767858520054520L;

	public AccountNotFoundException(String message) {
		super(message);
	}
	
	public AccountNotFoundException(String message, Throwable exception) {
		super(message, exception);
	}
	
	public AccountNotFoundException(Throwable exception) {
		super(exception);
	}
}
