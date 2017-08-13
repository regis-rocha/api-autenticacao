package br.com.projeto.regis.api.auth.service;

import br.com.projeto.regis.api.auth.domain.Account;
import br.com.projeto.regis.api.auth.exception.AccountExistsException;
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
	 * @return Account
	 * 
	 * @throws PersistException
	 * 
	 * @throws AccountExistsException
	 */
	Account create(Account account) throws PersistException, AccountExistsException;

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
	 * @return Account
	 * 
	 * @throws PersistException
	 */
	Account update(Account account) throws PersistException;

}