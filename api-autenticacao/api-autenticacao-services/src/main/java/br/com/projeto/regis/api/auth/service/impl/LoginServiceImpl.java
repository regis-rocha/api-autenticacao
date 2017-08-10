package br.com.projeto.regis.api.auth.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.regis.api.auth.domain.Account;
import br.com.projeto.regis.api.auth.exception.AuthException;
import br.com.projeto.regis.api.auth.exception.LoginFailedException;
import br.com.projeto.regis.api.auth.exception.LoginInvalidException;
import br.com.projeto.regis.api.auth.exception.ValidationException;
import br.com.projeto.regis.api.auth.repository.AccountRepository;
import br.com.projeto.regis.api.auth.service.LoginService;
import br.com.projeto.regis.api.auth.validate.ValidateHelper;

/**
 * Login Service interface
 * 
 * @author regis.rocha
 *
 */
@Service
public class LoginServiceImpl implements LoginService {
	
	/**
	 * LOG
	 */
	private static final Logger LOG = Logger.getLogger(LoginServiceImpl.class);
	
	/**
	 * @Inject
	 */
	@Autowired
	private ValidateHelper validate;
	
	/**
	 * @Inject
	 */
	@Autowired
	private AccountRepository accountRepository;
	
	/**
	 * Login
	 * 
	 * @param email - String
	 * @param senha	- String
	 * 
	 * @return Account
	 * 
	 * @throws ValidationException
	 * @throws LoginInvalidException
	 * @throws LoginFailedException 
	 */
	@Override
	public Account login(final String email, final String senha) throws ValidationException, LoginInvalidException, LoginFailedException {
		LOG.info("login account");
		
		this.validate.isEmpty(email, "E-mail nao foi preenchido");
		
		this.validate.isEmpty(senha, "Senha nao foi preenchida");

		try {
			// 1 verify if email exists
			final Account accountEmail = this.accountRepository.findByEmail(email);
			
			if (accountEmail == null) {
				throw new LoginInvalidException("Usuário e/ou senha inválidos");
			}
		} catch (LoginInvalidException e) {
			throw e;
		} catch (Exception e) {
			LOG.error("", e);
			throw new LoginFailedException(e);
		}
		
		try {
			// 2 verify if email and password are correct
			return this.accountRepository.signin(new Account(email, senha));
			
		} catch (AuthException e) {
			LOG.error("", e);
			throw new  LoginInvalidException(e.getMessage());
		} catch (Exception e) {
			LOG.error("", e);
			throw new LoginFailedException(e);
		}
	}
}
