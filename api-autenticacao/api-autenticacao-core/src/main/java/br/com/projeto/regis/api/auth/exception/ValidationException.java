package br.com.projeto.regis.api.auth.exception;

public class ValidationException extends RuntimeException {
	
	/**
	 * serial version
	 */
	private static final long serialVersionUID = -3034901509898360350L;

	/**
	 * @param message - String
	 */
	public ValidationException(String message) {
		super(message);
	}
}
