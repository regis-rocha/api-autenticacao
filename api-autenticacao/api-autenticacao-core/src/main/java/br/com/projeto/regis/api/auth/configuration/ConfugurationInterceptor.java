package br.com.projeto.regis.api.auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.com.projeto.regis.api.auth.interceptor.InterceptorToken;

/**
 * Configure bean Interceptor
 * 
 * @author regis.rocha
 *
 */
@Configuration 
public class ConfugurationInterceptor extends WebMvcConfigurerAdapter {
	
	/**
	 * Add interceptor validation token
	 * 
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(interceptorToken()).addPathPatterns("/account/*");
	}
	
	@Bean
	public InterceptorToken interceptorToken() {
	    return new InterceptorToken();
	}
}
