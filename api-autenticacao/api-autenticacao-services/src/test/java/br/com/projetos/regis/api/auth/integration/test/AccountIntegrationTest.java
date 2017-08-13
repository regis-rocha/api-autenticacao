package br.com.projetos.regis.api.auth.integration.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.projeto.regis.api.auth.Application;
import br.com.projetos.regis.api.auth.request.AccountRequest;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { Application.class })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountIntegrationTest {

	@LocalServerPort
	private int port;
	
	final TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void testCreatingNullAccount() {
		final HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
		final HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		final ResponseEntity<String> response = 
				restTemplate.exchange("http://localhost:"+ port +"/account", HttpMethod.POST, entity, String.class);
		
		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
	}
	
	@Test
	public void testCreatingAccount() {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
		final AccountRequest req = new AccountRequest();
		req.setEmail("regisrocha333333333333333333333333333@gmail.com");
		req.setName("Regis");
		req.setPassword("123");
		
		final HttpEntity<AccountRequest> entity = new HttpEntity<AccountRequest>(req, headers);
		
		final ResponseEntity<String> response = 
				restTemplate.exchange("http://localhost:"+ port +"/account", HttpMethod.POST, entity, String.class);
		
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());
	}
	
	@Test
	public void testCreatingDuplicateEmailAccount() {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
		final AccountRequest req = new AccountRequest();
		req.setEmail("regisrocha11111111111111@gmail.com");
		req.setName("Regis");
		req.setPassword("123");
		
		final HttpEntity<AccountRequest> entity = new HttpEntity<AccountRequest>(req, headers);
		
		restTemplate.exchange("http://localhost:"+ port +"/account", HttpMethod.POST, entity, String.class);
		
		final ResponseEntity<String> response2 = 
				restTemplate.exchange("http://localhost:"+ port +"/account", HttpMethod.POST, entity, String.class);
		
		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response2.getStatusCode().value());
	}
	
	@Test
	public void testFindAccountWithoutToken() {
		final HttpHeaders headers = new HttpHeaders();
		final HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		final ResponseEntity<String> response = 
				restTemplate.exchange("http://localhost:"+ port +"/account/asd", HttpMethod.GET, entity, String.class);
		
		Assert.assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatusCode().value());
	}
}
