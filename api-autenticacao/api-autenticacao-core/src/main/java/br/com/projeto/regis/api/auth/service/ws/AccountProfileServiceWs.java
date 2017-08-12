package br.com.projeto.regis.api.auth.service.ws;

import br.com.projeto.regis.api.auth.response.AccountResponse;
import br.com.projeto.regis.api.auth.types.Response;

/**
 * Service to expose functionalities by web services about account profile
 * 
 * @author regis.rocha
 *
 */
public interface AccountProfileServiceWs {

	/**
	 * Validate session Account
	 * 
	 * @param accountid - String
	 * 
	 * @param token 	- String
	 * 
	 * @return Response<AccountResponse>
	 */
	Response<AccountResponse> validateSession(String accountid, String token);
	
}