package br.com.projeto.regis.api.auth.service;

import br.com.projeto.regis.api.auth.domain.Account;
import br.com.projeto.regis.api.auth.exception.AccountNotFoundException;
import br.com.projeto.regis.api.auth.exception.FindException;
import br.com.projeto.regis.api.auth.exception.InvalidTokenAndAccountException;
import br.com.projeto.regis.api.auth.exception.SessionTimeoutException;
import br.com.projeto.regis.api.auth.exception.TokenNotFoundException;

/**
 * Account profile Service interface
 * 
 * @author regis.rocha
 *
 */
public interface AccountProfileService {
	
	/**
	 * Validate if account session is valid
	 * 
	 * @param idAccount		- String
	 * @param token			- String
	 * 
	 * @return Account
	 * 
	 * @throws TokenNotFoundException 
	 * @throws AccountNotFoundException
	 * @throws SessionTimeoutException
	 * @throws InvalidTokenAndAccountException
	 * @throws FindException
	 * 
	 */
	Account validateSessionAccount(String idAccount, String token) 
			throws TokenNotFoundException, AccountNotFoundException, SessionTimeoutException, 
			InvalidTokenAndAccountException, FindException;

}
