package br.com.projeto.regis.api.auth.exception;

public class PersistException extends Exception {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 5002500840959971631L;

	public PersistException() {
		super();
	}
	
	public PersistException(String message) {
		super(message);
	}
	
	public PersistException(String message, Throwable exception) {
		super(message, exception);
	}
	
	public PersistException(Throwable exception) {
		super(exception);
	}
}
