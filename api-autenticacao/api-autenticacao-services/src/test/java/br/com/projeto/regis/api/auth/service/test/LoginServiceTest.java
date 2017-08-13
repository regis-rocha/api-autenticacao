package br.com.projeto.regis.api.auth.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.projeto.regis.api.auth.Application;
import br.com.projeto.regis.api.auth.domain.Account;
import br.com.projeto.regis.api.auth.exception.AccountExistsException;
import br.com.projeto.regis.api.auth.exception.AccountNotFoundException;
import br.com.projeto.regis.api.auth.exception.LoginFailedException;
import br.com.projeto.regis.api.auth.exception.LoginInvalidException;
import br.com.projeto.regis.api.auth.exception.PersistException;
import br.com.projeto.regis.api.auth.exception.ValidationException;
import br.com.projeto.regis.api.auth.service.AccountService;
import br.com.projeto.regis.api.auth.service.LoginService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@WebAppConfiguration
public class LoginServiceTest {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private AccountService accountService;
	
	@Test(expected = ValidationException.class)
	public void loginEmailNotFilledTest() throws ValidationException, LoginInvalidException, LoginFailedException, AccountNotFoundException {
		this.loginService.login(null, "pass");
	}
	
	@Test(expected = ValidationException.class)
	public void loginPasswordNotFilledTest() throws ValidationException, LoginInvalidException, LoginFailedException, AccountNotFoundException {
		this.loginService.login("email@email.com", null);
	}
	
	@Test(expected = AccountNotFoundException.class)
	public void loginEmailNotExistsTest() throws ValidationException, LoginInvalidException, LoginFailedException, AccountNotFoundException {
		this.loginService.login("email@email.com", "pass");
	}
	
	@Test(expected = AccountNotFoundException.class)
	public void loginInvalidEmailTest() throws ValidationException, LoginInvalidException, LoginFailedException, PersistException, AccountExistsException, AccountNotFoundException {
		final Account account = new Account();
		
		account.setEmail("regisrocha1@gmail.com");
		account.setName("Regis");
		account.setPassword("12234");
		
		this.accountService.create(account);
		
		this.loginService.login("email@email.com", "pass");
	}
	
	
	@Test
	public void loginSuccessTest() throws ValidationException, LoginInvalidException, LoginFailedException, PersistException, AccountExistsException, AccountNotFoundException {
		final Account account = new Account();
		
		account.setEmail("regisrocha3@gmail.com");
		account.setName("Regis");
		account.setPassword("12234");
		
		this.accountService.create(account);
		
		this.loginService.login("regisrocha3@gmail.com", "12234");
	}
	
	@Test
	public void loginSuccessCompareAccountTest() throws ValidationException, LoginInvalidException, LoginFailedException, PersistException, AccountExistsException, AccountNotFoundException {
		final Account accountLogin = new Account();
		
		accountLogin.setEmail("regisrocha4@gmail.com");
		accountLogin.setName("Regis");
		accountLogin.setPassword("12234");
		
		this.accountService.create(accountLogin);
		
		final Account accountLogged = this.loginService.login("regisrocha4@gmail.com", "12234");
		
		assertNotNull(accountLogged);
		assertEquals(accountLogin.getEmail(), accountLogged.getEmail());
		assertEquals(accountLogin.getName(), accountLogged.getName());
		assertEquals(accountLogin.getCreated(), accountLogged.getCreated());
	}
}
