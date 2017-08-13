package br.com.projeto.regis.api.auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Bean configuration
 * 
 * @author regis.rocha
 *
 */
@Configuration
public class BeanConfiguration {

	/**
	 * Creating bean to Crypt the information using Hash algorithm 
	 * 
	 * @return PasswordEncoder
	 */
	@Bean
	public PasswordEncoder cryptEncoder() {
		return new BCryptPasswordEncoder();
	}
}