package br.com.projeto.regis.api.auth.service;

import br.com.projeto.regis.api.auth.domain.Account;
import br.com.projeto.regis.api.auth.exception.LoginFailedException;
import br.com.projeto.regis.api.auth.exception.LoginInvalidException;
import br.com.projeto.regis.api.auth.exception.ValidationException;

/**
 * Login Service interface
 * 
 * @author regis.rocha
 *
 */
public interface LoginService {

	/**
	 * Login
	 * 
	 * @param email - String
	 * @param senha	- String
	 * 
	 * @return Account
	 * 
	 * @throws ValidationException
	 * @throws LoginInvalidException
	 * @throws LoginFailedException
	 */
	Account login(String email, String senha) throws ValidationException, LoginInvalidException, LoginFailedException;
	
}
