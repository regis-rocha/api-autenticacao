package br.com.projeto.regis.api.auth.service.ws;

import org.springframework.web.bind.annotation.RequestBody;

import br.com.projeto.regis.api.auth.domain.Account;
import br.com.projeto.regis.api.auth.types.Response;

/**
 * Interface to expose functionalities by web services about account
 * 
 * @author regis.rocha
 *
 */
public interface AccountServiceWs {

	/**
	 * Create an account
	 * 
	 * @param account - Account
	 * 
	 * @return Response<Account>
	 */
	Response<Account> create(Account account);
	
	/**
	 * Find an account
	 * 
	 * @param id - String
	 * 
	 * @return Response<Account>
	 */
	Response<Account> findAccount(String id);
	
	/**
	 * Update an account
	 * 
	 * @param account - Account
	 * 
	 * @return Response<Account>
	 */
	Response<Account> updateAccount(Account account);
	
	/**
	 * Sign in
	 * 
	 * @param account - Account
	 * 
	 * @return Response<Account>
	 */
	Response<Account> signin(@RequestBody Account account); 
	
}