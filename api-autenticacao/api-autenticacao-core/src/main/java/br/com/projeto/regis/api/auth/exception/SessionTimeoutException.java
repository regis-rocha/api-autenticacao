package br.com.projeto.regis.api.auth.exception;

public class SessionTimeoutException extends Exception {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = -3527673011783317718L;

	public SessionTimeoutException() {
		super();
	}
	
	public SessionTimeoutException(String message) {
		super(message);
	}
	
	public SessionTimeoutException(String message, Throwable exception) {
		super(message, exception);
	}
	
	public SessionTimeoutException(Throwable exception) {
		super(exception);
	}
}
