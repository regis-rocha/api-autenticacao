package br.com.projeto.regis.api.auth.services.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.regis.api.auth.domain.Account;
import br.com.projeto.regis.api.auth.exception.AuthException;
import br.com.projeto.regis.api.auth.exception.FindException;
import br.com.projeto.regis.api.auth.exception.ValidationException;
import br.com.projeto.regis.api.auth.service.AccountService;
import br.com.projeto.regis.api.auth.service.ws.AccountServiceWs;
import br.com.projeto.regis.api.auth.types.Response;

/**
 * Service to manipulate information about Account
 * 
 * @author regis.rocha
 *
 */
@RestController("/account")
public class AccountServiceRest implements AccountServiceWs {
	
	/**
	 * LOG
	 */
	private Logger log = Logger.getLogger(getClass());
	
	/**
	 * @Inject
	 */
	@Autowired
	private AccountService accountService;
	
	
	/**
	 * Create an account
	 * 
	 * @param account - Account
	 * 
	 * @return Response<Account>
	 */
	@Override
	@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Response<Account> create(@RequestBody Account account) {
		log.info("Creating account");
		
		try {
			this.accountService.create(account);
		} catch (ValidationException e) {
			log.error("", e);
			return new Response<Account>().createValidationErrorResponse().addGeneralMessage(e.getMessage());
		} catch (Exception e) {
			log.error("", e);
			return new Response<Account>().createErrorResponse();
		}
		
		return new Response<Account>().createSuccessCreatedResponse().addBody(account);
	}
	
	
	/**
	 * Find an account
	 * 
	 * @param id - String
	 * 
	 * @return Response<Account>
	 */
	@Override
	@RequestMapping(value = "/account/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public Response<Account> findAccount(@PathVariable String id) {
		log.info("Finding account");
		
		try {
			final Account acc = this.accountService.find(id);
			
			if (acc == null) {
				return new Response<Account>().createEmptyResponse();
			} else {
				return new Response<Account>().createSuccessResponse().addBody(acc);
			}
			
		} catch (ValidationException e) {
			log.error("", e);
			return new Response<Account>().createValidationErrorResponse();	
		} catch (Exception e) {
			log.error("", e);
			return new Response<Account>().createErrorResponse();
		}
	}
	
	
	/**
	 * Update an account
	 * 
	 * @param account - Account
	 * 
	 * @return Response<Account>
	 */
	@Override
	@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Response<Account> updateAccount(@RequestBody Account account) {
		log.info("Updating account");
		
		try {
			this.accountService.update(account);
			
			return new Response<Account>().createEmptyResponse().addBody(account);
			
		} catch (ValidationException e) {
			log.error("", e);
			return new Response<Account>().createValidationErrorResponse();
		} catch (Exception e) {
			log.error("", e);
			return new Response<Account>().createErrorResponse();
		}
	}
	
	
	/**
	 * Sign in
	 * 
	 * @param account - Account
	 * 
	 * @return Response<Account>
	 */
	@Override
	@RequestMapping(value = "/account/signing", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Response<Account> signin(@RequestBody Account account) {
		log.info("signing");
		
		try {
			this.accountService.signin(account);
			
			return new Response<Account>().createSuccessResponse();
		} catch (AuthException e) {
			return new Response<Account>().addHttpStatus(HttpStatus.UNAUTHORIZED)
					.addGeneralMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		} catch (FindException e) {
			return new Response<Account>().createErrorResponse();
		}
	}
}
