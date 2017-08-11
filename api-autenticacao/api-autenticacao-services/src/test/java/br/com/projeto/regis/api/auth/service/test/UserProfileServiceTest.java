package br.com.projeto.regis.api.auth.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.projeto.regis.api.auth.Application;
import br.com.projeto.regis.api.auth.domain.Account;
import br.com.projeto.regis.api.auth.exception.AccountExistsException;
import br.com.projeto.regis.api.auth.exception.AccountNotFoundException;
import br.com.projeto.regis.api.auth.exception.FindException;
import br.com.projeto.regis.api.auth.exception.InvalidSessionException;
import br.com.projeto.regis.api.auth.exception.InvalidTokenAndAccountException;
import br.com.projeto.regis.api.auth.exception.LoginFailedException;
import br.com.projeto.regis.api.auth.exception.LoginInvalidException;
import br.com.projeto.regis.api.auth.exception.PersistException;
import br.com.projeto.regis.api.auth.exception.SessionTimeoutException;
import br.com.projeto.regis.api.auth.exception.TokenNotFoundException;
import br.com.projeto.regis.api.auth.exception.ValidationException;
import br.com.projeto.regis.api.auth.service.AccountService;
import br.com.projeto.regis.api.auth.service.LoginService;
import br.com.projeto.regis.api.auth.service.UserProfileService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class UserProfileServiceTest {
	
	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private LoginService loginService;
	
	@Test(expected = TokenNotFoundException.class)
	public void tokenNotFoundTest() throws InvalidSessionException, TokenNotFoundException, AccountNotFoundException, SessionTimeoutException, InvalidTokenAndAccountException, FindException {
		this.userProfileService.validateSessionUser("12", "abc");
	}
	
	@Test(expected = AccountNotFoundException.class)
	public void accountNotFoundTest() throws InvalidSessionException, TokenNotFoundException, AccountNotFoundException, SessionTimeoutException, InvalidTokenAndAccountException, FindException, PersistException, AccountExistsException {
		final Account acc = new Account();
		acc.setEmail("mesmoEmail@gmail.com");
		acc.setName("Regis");
		acc.setPassword("1234");
		
		accountService.create(acc);
		
		this.userProfileService.validateSessionUser("abc", acc.getToken());
	}
	
	@Test(expected = InvalidTokenAndAccountException.class)
	public void accountOrTokenInvalidTest() throws InvalidSessionException, TokenNotFoundException, AccountNotFoundException, SessionTimeoutException, InvalidTokenAndAccountException, FindException, PersistException, AccountExistsException, ValidationException, LoginInvalidException, LoginFailedException {
		final Account acc = new Account();
		acc.setEmail("mesmoEmail2@gmail.com");
		acc.setName("Regis");
		acc.setPassword("1234");
		accountService.create(acc);
		
		this.loginService.login("mesmoEmail2@gmail.com", "1234");
		
		final Account acc2 = new Account();
		acc2.setEmail("outroEmail@gmail.com");
		acc2.setName("Regis 2");
		acc2.setPassword("987");
		accountService.create(acc2);
		
		this.loginService.login("outroEmail@gmail.com", "987");
		
		this.userProfileService.validateSessionUser(acc.getId(), acc2.getToken());
	}

}
