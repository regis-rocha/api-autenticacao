package br.com.projeto.regis.api.auth.exception;

public class InvalidSessionException extends Exception {
	
	/**
	 * serial version
	 */
	private static final long serialVersionUID = -4455672006284665405L;

	public InvalidSessionException(String message) {
		super(message);
	}
	
	public InvalidSessionException(String message, Throwable exception) {
		super(message, exception);
	}
	
	public InvalidSessionException(Throwable exception) {
		super(exception);
	}
}
