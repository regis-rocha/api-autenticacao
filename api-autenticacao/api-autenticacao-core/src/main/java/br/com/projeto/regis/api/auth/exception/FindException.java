package br.com.projeto.regis.api.auth.exception;

public class FindException extends Exception {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 5415832350050405934L;

	public FindException() {
		super();
	}
	
	public FindException(String message) {
		super(message);
	}
	
	public FindException(String message, Throwable exception) {
		super(message, exception);
	}
	
	public FindException(Throwable exception) {
		super(exception);
	}
}
