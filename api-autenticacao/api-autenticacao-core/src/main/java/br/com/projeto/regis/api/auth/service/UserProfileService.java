package br.com.projeto.regis.api.auth.service;

import br.com.projeto.regis.api.auth.domain.Account;
import br.com.projeto.regis.api.auth.exception.InvalidSessionException;

/**
 * Perfil usuario Service interface
 * 
 * @author regis.rocha
 *
 */
public interface UserProfileService {
	
	/**
	 * Validate if account session is valid
	 * 
	 * @param idAccount		- String
	 * @param token			- String
	 * 
	 * @return Account
	 * 
	 * @throws InvalidSessionException
	 */
	Account validateSessionUser(String idAccount, String token) throws InvalidSessionException;

}
