package br.com.projeto.regis.api.auth.services.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.regis.api.auth.domain.Account;
import br.com.projeto.regis.api.auth.exception.AccountNotFoundException;
import br.com.projeto.regis.api.auth.exception.FindException;
import br.com.projeto.regis.api.auth.exception.InvalidTokenAndAccountException;
import br.com.projeto.regis.api.auth.exception.SessionTimeoutException;
import br.com.projeto.regis.api.auth.exception.TokenNotFoundException;
import br.com.projeto.regis.api.auth.helper.AccountHelper;
import br.com.projeto.regis.api.auth.response.AccountResponse;
import br.com.projeto.regis.api.auth.service.AccountProfileService;
import br.com.projeto.regis.api.auth.service.ws.AccountProfileServiceWs;
import br.com.projeto.regis.api.auth.types.Response;

/**
 * Service to expose functionalities by web services about account profile
 * 
 * @author regis.rocha
 *
 */
@RestController("/accountProfile")
public class AccountProfileServiceRest implements AccountProfileServiceWs {

	/**
	 * LOG
	 */
	private static final Logger LOG = Logger.getLogger(AccountProfileServiceRest.class);
	
	/**
	 * @Inject
	 */
	 @Autowired
	 private AccountProfileService profileService;
	
	 /**
	  * @Inject
	  */
	 @Autowired
	 private AccountHelper accountHelper;
	
	 
	 /**
	  * Validate session Account
	  * 
	  * @param accountid - String
	  * 
	  * @param token 	- String
	  * 
	  * @return Response<AccountResponse>
	  */
	@Override
	@RequestMapping(value = "/accountProfile/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public Response<AccountResponse> validateSession(@PathVariable String id, @RequestHeader String token) {
		LOG.info("validating session account");
		
		try {
			// validate session account
			final Account accountEntity = profileService.validateSessionAccount(id, token);
			
			// return successs
			return new Response<AccountResponse>().createSuccessResponse().addBody(this.accountHelper.convertToResponse(accountEntity));
			
		} catch (TokenNotFoundException | AccountNotFoundException | FindException e) {
			return new Response<AccountResponse>().addHttpStatus(HttpStatus.NOT_FOUND).addGeneralMessage(e.getMessage());
			
		} catch (InvalidTokenAndAccountException | SessionTimeoutException e) {
			return new Response<AccountResponse>().addHttpStatus(HttpStatus.UNAUTHORIZED).addGeneralMessage(e.getMessage());
		}
	}
}
