package br.com.projeto.regis.api.auth.service;

import br.com.projeto.regis.api.auth.domain.Account;
import br.com.projeto.regis.api.auth.exception.AccountExistsException;
import br.com.projeto.regis.api.auth.exception.AuthException;
import br.com.projeto.regis.api.auth.exception.FindException;
import br.com.projeto.regis.api.auth.exception.PersistException;

/**
 * Account Service
 * 
 * @author regis.rocha
 *
 */
public interface AccountService {
	
	/**
	 * Create account
	 * 
	 * @param account - Account
	 * 
	 * @throws PersistException
	 * 
	 * @throws AccountExistsException
	 */
	void create(Account account) throws PersistException, AccountExistsException;

	/**
	 * Find an specific account
	 * 
	 * @param id - String
	 * 
	 * @return Account
	 * 
	 * @throws FindException
	 */
	Account find(String id) throws FindException;

	/**
	 * Update an account
	 * 
	 * @param id - Integer
	 * 
	 * @throws PersistException
	 */
	void update(Account account) throws PersistException;

	/**
	 * Sign in account
	 * 
	 * @param account - Account
	 * 
	 * @throws FindException, AuthException
	 */
	void signin(Account account) throws FindException, AuthException;
	
}
