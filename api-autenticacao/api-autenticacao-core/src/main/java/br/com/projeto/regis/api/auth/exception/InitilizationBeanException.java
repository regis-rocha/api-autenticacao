package br.com.projeto.regis.api.auth.exception;

public class InitilizationBeanException extends RuntimeException {
	
	/**
	 * serial version
	 */
	private static final long serialVersionUID = -9170652125466051697L;

	public InitilizationBeanException() {
		super();
	}
	
	public InitilizationBeanException(String message) {
		super(message);
	}
	
	public InitilizationBeanException(String message, Throwable exception) {
		super(message, exception);
	}
	
	public InitilizationBeanException(Throwable exception) {
		super(exception);
	}
}
