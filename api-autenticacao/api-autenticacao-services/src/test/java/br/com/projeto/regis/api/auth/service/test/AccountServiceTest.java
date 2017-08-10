package br.com.projeto.regis.api.auth.service.test;

import java.util.Arrays;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.projeto.regis.api.auth.Application;
import br.com.projeto.regis.api.auth.domain.Account;
import br.com.projeto.regis.api.auth.domain.Phone;
import br.com.projeto.regis.api.auth.exception.AccountExistsException;
import br.com.projeto.regis.api.auth.exception.PersistException;
import br.com.projeto.regis.api.auth.exception.ValidationException;
import br.com.projeto.regis.api.auth.service.AccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class AccountServiceTest {
	
	@Autowired
	private AccountService accountService;

	@Test(expected = ValidationException.class)
	public void persistAccountNull() throws PersistException, AccountExistsException {
		this.accountService.create(null);
	}
	
	@Test(expected = ValidationException.class)
	public void persistAccountEmailNull() throws PersistException, AccountExistsException {
		final Account acc = new Account();
		
		acc.setEmail(null);
		acc.setName("Regis");
		acc.setPassword("1234");
		
		this.accountService.create(acc);
	}
	
	@Test(expected = ValidationException.class)
	public void persistAccountNameNull() throws PersistException, AccountExistsException {
		final Account acc = new Account();
		
		acc.setEmail("regisrocha3@gmail.com");
		acc.setName(null);
		acc.setPassword("1234");
		
		this.accountService.create(acc);
	}
	
	@Test(expected = ValidationException.class)
	public void persistAccountPasswordNull() throws PersistException, AccountExistsException {
		final Account acc = new Account();
		
		acc.setEmail("regisrocha3@gmail.com");
		acc.setName("Regis");
		acc.setPassword(null);
		
		this.accountService.create(acc);
	}
	
	@Test(expected = ValidationException.class)
	public void persistAccountNewObject() throws PersistException, AccountExistsException {
		this.accountService.create(new Account());
	}
	
	@Test
	public void persistAccount() throws PersistException, AccountExistsException {
		final Account acc = new Account();
		
		acc.setEmail("regisrocha@gmail.com");
		acc.setName("Regis");
		acc.setPassword("1234");
		
		this.accountService.create(acc);
	}
	
	@Test
	public void persistAccountWithPhone() throws PersistException, AccountExistsException {
		final Account acc = new Account();
		acc.setEmail("regisrocha2@gmail.com");
		acc.setName("Regis");
		acc.setPassword("1234");
		
		final Phone phone = new Phone();
		phone.setDdd(11);
		phone.setNumber(982895858L);
		
		acc.setPhones(Arrays.asList(new Phone[]{phone}));
		
		this.accountService.create(acc);
	}
	
	@Test(expected = AccountExistsException.class)
	public void persistAccountDuplicateEmail() throws PersistException, AccountExistsException {
		final Account acc = new Account();
		acc.setEmail("mesmoEmail@gmail.com");
		acc.setName("Regis");
		acc.setPassword("1234");
		
		final Phone phone = new Phone();
		phone.setDdd(11);
		phone.setNumber(982895858L);
		
		acc.setPhones(Arrays.asList(new Phone[]{phone}));
		
		// persist 1
		this.accountService.create(acc);
		
		// persist 2 -- erro email duplicado
		this.accountService.create(acc);
	}
	
	@Bean
    public DataSource dataSource(){
        
        final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		final EmbeddedDatabase db = builder
				.setType(EmbeddedDatabaseType.HSQL)
				.addScript("/create.sql")
				.build();
		
		return db;
    }
}
