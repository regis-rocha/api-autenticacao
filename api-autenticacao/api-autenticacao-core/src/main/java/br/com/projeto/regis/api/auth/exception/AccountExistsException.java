package br.com.projeto.regis.api.auth.exception;

public class AccountExistsException extends Exception {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = -8940142795061971802L;

	public AccountExistsException() {
		super();
	}
	
	public AccountExistsException(String message) {
		super(message);
	}
	
	public AccountExistsException(String message, Throwable exception) {
		super(message, exception);
	}
	
	public AccountExistsException(Throwable exception) {
		super(exception);
	}
}
