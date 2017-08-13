package br.com.projeto.regis.api.auth.security.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.projeto.regis.api.auth.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class HashSecurityTest {
	
	@Autowired
	private PasswordEncoder cryptEncoder;
	
	@Test
	public void encryptTest() {
		final String stringToEncode = "regis";
		
		final String encode = this.cryptEncoder.encode(stringToEncode);
		
		Assert.assertNotNull(encode);
	}
	
	@Test
	public void notMatcheTest() {
		final String stringToEncode = "regis1234567";
		
		final String encode = this.cryptEncoder.encode(stringToEncode);
		
		Assert.assertNotNull(encode);
		
		boolean matches = this.cryptEncoder.matches(stringToEncode.toUpperCase(), encode);
		
		Assert.assertFalse(matches);
	}
	
	@Test
	public void matcheTest() {
		final String stringToEncode = "regis";
		
		final String encode = this.cryptEncoder.encode(stringToEncode);
		
		Assert.assertNotNull(encode);
		
		boolean isEqual = this.cryptEncoder.matches(stringToEncode, encode);
		
		Assert.assertTrue(isEqual);
	}
}
