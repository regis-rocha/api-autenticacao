package br.com.projeto.regis.api.auth.validate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.projeto.regis.api.auth.exception.ValidationException;

@Component
public class ValidateHelper {

	public boolean isObjectNull(final Object object) throws ValidationException {
		if (object == null) {
			throw new ValidationException("Objeto esta nulo");
		}
		
		return true;
	}
	
	public boolean isEmpty(final String object) throws ValidationException {
		this.isObjectNull(object);
		
		if (StringUtils.isBlank(object)) {
			throw new ValidationException("Objeto esta vazio");
		}
		
		return true;
	}
	
	public boolean isObjectNull(final Object object, final String message) throws ValidationException {
		if (object == null) {
			throw new ValidationException(message);
		}
		
		return true;
	}
	
	public boolean isEmpty(final String object, final String message) throws ValidationException {
		this.isObjectNull(object, message);
		
		if (StringUtils.isBlank(object)) {
			throw new ValidationException(message);
		}
		
		return true;
	}
	
}
