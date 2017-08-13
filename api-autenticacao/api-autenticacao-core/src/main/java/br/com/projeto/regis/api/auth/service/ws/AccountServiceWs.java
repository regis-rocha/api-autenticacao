package br.com.projeto.regis.api.auth.service.ws;

import org.springframework.http.ResponseEntity;

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
	 * @return ResponseEntity<Response<AccountResponse>>
	 */
	ResponseEntity<Response<AccountResponse>> create(AccountRequest account);
	
	/**
	 * Find an account
	 * 
	 * @param id 	- String
	 * @param token	- String
	 * 
	 * @return ResponseEntity<Response<AccountResponse>>
	 */
	ResponseEntity<Response<AccountResponse>> findAccount(String id, String token);
	
	/**
	 * Sign in
	 * 
	 * @param account - AccountRequest
	 * 
	 * @return ResponseEntity<Response<AccountResponse>>
	 */
	ResponseEntity<Response<AccountResponse>> signin(AccountRequest account); 
	
}