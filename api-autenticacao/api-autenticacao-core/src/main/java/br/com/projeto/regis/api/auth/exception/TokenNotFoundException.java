package br.com.projeto.regis.api.auth.exception;

public class TokenNotFoundException extends Exception {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 8606134582304512783L;

	public TokenNotFoundException() {
		super();
	}
	
	public TokenNotFoundException(String message) {
		super(message);
	}
	
	public TokenNotFoundException(String message, Throwable exception) {
		super(message, exception);
	}
	
	public TokenNotFoundException(Throwable exception) {
		super(exception);
	}
}
