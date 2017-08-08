package br.com.projeto.regis.api.auth.validate;

import br.com.projeto.regis.api.auth.exception.ValidationException;

/**
 * Interface para realizar validacao de beans
 * 
 * @author regis.rocha
 *
 */
public interface BeanValidation {

	/**
	 * Valida os campos obrigatorios do bean
	 * 
	 * @return true - caso o bean esta com os campos obrigatorios validos
	 * 
	 * @throws ValidationException
	 */
	boolean validate() throws ValidationException;
}
