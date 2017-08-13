package br.com.projeto.regis.api.auth.service.ws;

import org.springframework.web.bind.annotation.RequestBody;

import br.com.projeto.regis.api.auth.response.AccountResponse;
import br.com.projeto.regis.api.auth.types.Response;
import br.com.projetos.regis.api.auth.request.AccountRequest;

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
	 * @param account - AccountRequest
	 * 
	 * @return Response<AccountResponse>
	 */
	Response<AccountResponse> create(AccountRequest account);
	
	/**
	 * Find an account
	 * 
	 * @param id - String
	 * 
	 * @return Response<Account>
	 */
	Response<AccountResponse> findAccount(String id);
	
	/**
	 * Sign in
	 * 
	 * @param account - AccountRequest
	 * 
	 * @return Response<AccountResponse>
	 */
	Response<AccountResponse> signin(@RequestBody AccountRequest account); 
	
}