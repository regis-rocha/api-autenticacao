package br.com.projeto.regis.api.auth.service.test;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
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
import br.com.projeto.regis.api.auth.service.AccountProfileService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class UserProfileServiceTest {
	
	@Autowired
	private AccountProfileService userProfileService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private LoginService loginService;
	
	@Test(expected = TokenNotFoundException.class)
	public void tokenNotFoundTest() throws InvalidSessionException, TokenNotFoundException, AccountNotFoundException, SessionTimeoutException, InvalidTokenAndAccountException, FindException {
		this.userProfileService.validateSessionAccount("12", "abc");
	}
	
	@Test(expected = AccountNotFoundException.class)
	public void accountNotFoundTest() throws InvalidSessionException, TokenNotFoundException, AccountNotFoundException, SessionTimeoutException, InvalidTokenAndAccountException, FindException, PersistException, AccountExistsException {
		final Account acc = new Account();
		acc.setEmail("accountNotFoundTest@gmail.com");
		acc.setName("Regis");
		acc.setPassword("1234");
		
		accountService.create(acc);
		
		this.userProfileService.validateSessionAccount("abc", acc.getToken());
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
		
		this.userProfileService.validateSessionAccount(acc.getId(), acc2.getToken());
	}
	
	@Test
	public void accountValidateOkTest() throws InvalidSessionException, TokenNotFoundException, AccountNotFoundException, SessionTimeoutException, InvalidTokenAndAccountException, FindException, PersistException, AccountExistsException, ValidationException, LoginInvalidException, LoginFailedException {
		final Account acc = new Account();
		acc.setEmail("mesmoEmail3@gmail.com");
		acc.setName("Regis mesmoEmail3");
		acc.setPassword("12345");
		accountService.create(acc);
		
		final Account acc2 = new Account();
		acc2.setEmail("outroEmail3@gmail.com");
		acc2.setName("Regis outroEmail3");
		acc2.setPassword("9876");
		accountService.create(acc2);
		
		this.loginService.login("outroEmail3@gmail.com", "9876");
		this.loginService.login("mesmoEmail3@gmail.com", "12345");
		
		final Account accDb1 = this.userProfileService.validateSessionAccount(acc.getId(), acc.getToken());
		
		final Account accDb2 = this.userProfileService.validateSessionAccount(acc2.getId(), acc2.getToken());
		
		assertEquals(accDb1.getId(), acc.getId());
		
		assertEquals(accDb2.getId(), acc2.getId());
	}
	
	@Test
	@Ignore(value = "Test is too long to execute")
	public void testTimeout() throws PersistException, AccountExistsException, InterruptedException, ValidationException, LoginInvalidException, LoginFailedException, AccountNotFoundException, InvalidSessionException, TokenNotFoundException, SessionTimeoutException, InvalidTokenAndAccountException, FindException {
		final Account acc = new Account();
		acc.setEmail("timeoutEmail@gmail.com");
		acc.setName("Regis timout test");
		acc.setPassword("1234");
		
		accountService.create(acc);

		this.loginService.login("timeoutEmail@gmail.com", "1234");
		
		Thread.sleep(120000);
		
		this.userProfileService.validateSessionAccount(acc.getId(), acc.getToken());
	}

}
