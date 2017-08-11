package br.com.projeto.regis.api.auth.service;

import br.com.projeto.regis.api.auth.domain.Account;
import br.com.projeto.regis.api.auth.exception.AccountNotFoundException;
import br.com.projeto.regis.api.auth.exception.FindException;
import br.com.projeto.regis.api.auth.exception.InvalidSessionException;
import br.com.projeto.regis.api.auth.exception.InvalidTokenAndAccountException;
import br.com.projeto.regis.api.auth.exception.SessionTimeoutException;
import br.com.projeto.regis.api.auth.exception.TokenNotFoundException;

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
	 * @throws TokenNotFoundException 
	 * @throws AccountNotFoundException
	 * @throws SessionTimeoutException
	 * @throws InvalidTokenAndAccountException
	 * @throws FindException
	 * 
	 */
	Account validateSessionUser(String idAccount, String token) 
			throws InvalidSessionException, TokenNotFoundException, AccountNotFoundException, SessionTimeoutException, 
			InvalidTokenAndAccountException, FindException;

}
