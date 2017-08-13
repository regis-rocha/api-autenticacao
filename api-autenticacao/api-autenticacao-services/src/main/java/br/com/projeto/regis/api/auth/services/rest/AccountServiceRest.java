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
import br.com.projeto.regis.api.auth.exception.AccountExistsException;
import br.com.projeto.regis.api.auth.exception.AccountNotFoundException;
import br.com.projeto.regis.api.auth.exception.LoginInvalidException;
import br.com.projeto.regis.api.auth.exception.ValidationException;
import br.com.projeto.regis.api.auth.helper.AccountHelper;
import br.com.projeto.regis.api.auth.response.AccountResponse;
import br.com.projeto.regis.api.auth.service.AccountService;
import br.com.projeto.regis.api.auth.service.LoginService;
import br.com.projeto.regis.api.auth.service.ws.AccountServiceWs;
import br.com.projeto.regis.api.auth.types.Response;
import br.com.projetos.regis.api.auth.request.AccountRequest;

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
	 * @Inject
	 */
	@Autowired
	private LoginService loginService;
	
	/**
	 * @Inject
	 */
	@Autowired
	private AccountHelper accountHelper;
	
	/**
	 * Create an account
	 * 
	 * @param account - AccountRequest
	 * 
	 * @return Response<AccountResponse>
	 */
	@Override
	@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Response<AccountResponse> create(@RequestBody AccountRequest account) {
		log.info("Creating account");
		AccountResponse accountResponse = null;
		
		try {
			// convert request to entity and persist
			final Account accountDB = this.accountService.create(this.accountHelper.convertRequestToEntity(account));
			
			// convert entity to response
			accountResponse = this.accountHelper.convertToResponse(accountDB);
			
		} catch (AccountExistsException | ValidationException e) {
			log.error("", e);
			return new Response<AccountResponse>().createValidationErrorResponse().addGeneralMessage(e.getMessage());
		} catch (Exception e) {
			log.error("", e);
			return new Response<AccountResponse>().createErrorResponse();
		}
		
		return new Response<AccountResponse>().createSuccessCreatedResponse().addBody(accountResponse);
	}
	
	
	/**
	 * Find an account
	 * 
	 * @param id - String
	 * 
	 * @return Response<AccountResponse>
	 */
	@Override
	@RequestMapping(value = "/account/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public Response<AccountResponse> findAccount(@PathVariable String id) {
		log.info("Finding account");
		
		try {
			// find account
			final Account acc = this.accountService.find(id);
			
			if (acc == null) {
				// not found
				return new Response<AccountResponse>()
						.addHttpStatus(HttpStatus.NOT_FOUND)
						.addGeneralMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
			} else {
				// success
				return new Response<AccountResponse>().createSuccessResponse()
						.addBody(this.accountHelper.convertToResponse(acc));
			}
			
		} catch (ValidationException e) {
			log.error("", e);
			return new Response<AccountResponse>().createValidationErrorResponse();	
		} catch (Exception e) {
			log.error("", e);
			return new Response<AccountResponse>().createErrorResponse();
		}
	}
	
	/**
	 * Sign in
	 * 
	 * @param account - AccountRequest
	 * 
	 * @return Response<AccountResponse>
	 */
	@Override
	@RequestMapping(value = "/account/signing", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Response<AccountResponse> signin(@RequestBody AccountRequest account) {
		log.info("signing");
		
		try {
			// login
			final Account accountLogged = this.loginService.login(account.getEmail(), account.getPassword());

			// result
			return new Response<AccountResponse>().createSuccessResponse()
					.addBody(this.accountHelper.convertToResponse(accountLogged));
		
		} catch (AccountNotFoundException e) {
			return new Response<AccountResponse>().addHttpStatus(HttpStatus.NOT_FOUND).addGeneralMessage(e.getMessage());
		} catch (LoginInvalidException e) {
			return new Response<AccountResponse>().addHttpStatus(HttpStatus.UNAUTHORIZED).addGeneralMessage(e.getMessage());
		} catch (Exception e) {
			return new Response<AccountResponse>().createErrorResponse();
		}
	}
}
