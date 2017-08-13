package br.com.projeto.regis.api.auth.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projeto.regis.api.auth.exception.SessionTimeoutException;
import br.com.projeto.regis.api.auth.exception.TokenNotFoundException;
import br.com.projeto.regis.api.auth.response.AccountResponse;
import br.com.projeto.regis.api.auth.service.AccountProfileService;
import br.com.projeto.regis.api.auth.types.Response;

/**
 * Interceptor to validate session token
 * 
 * @author regis.rocha
 *
 */
@Component
public class InterceptorToken extends HandlerInterceptorAdapter {
	
	/**
	 * @Inject
	 */
	 @Autowired
	 private AccountProfileService profileService;
	
	/**
	 * Intercept request
	 * 
	 * @param request 	- HttpServletRequest
	 * @param response 	- HttpServletResponse
	 * @param handler 	- Object
	 * 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// get token
		final String token = request.getHeader("token");
		
		// token is null, send UNAUTHORIZED
		if (! this.validateToken(token, response)) {
			return false;
		}
		
		// token is valid, continue execution
		return super.preHandle(request, response, handler);
	}

	
	/**
	 * verifying if token is valid
	 * 
	 * @param token 	- String
	 * @param response	- HttpServletResponse
	 * 
	 * @return boolean
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 */
	private boolean validateToken(final String token, final HttpServletResponse response) throws JsonProcessingException, IOException {
		if (StringUtils.isBlank(token)) {
			this.unauthorized(response);
		}
		
		try {
			this.profileService.validateToken(token);
			
		} catch (TokenNotFoundException e) {
			this.notFound(response);
			return false;
			
		} catch (SessionTimeoutException e) {
			this.unauthorized(response);
			return false;
		}
		
		return true;
	}

	/**
	 * Token invalid - UNAUTHORIZED
	 * 
	 * @param response - HttpServletResponse
	 * 
	 * @throws JsonProcessingException	
	 * @throws IOException
	 */
	private void unauthorized(final HttpServletResponse response) throws JsonProcessingException, IOException {
		try (final PrintWriter writer = response.getWriter()) {
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.setCharacterEncoding("UTF-8");
			
			final Response<AccountResponse> responseJson = new Response<AccountResponse>()
					.addHttpStatus(HttpStatus.UNAUTHORIZED)
					.addGeneralMessage("Não autorizado");
			
			final ObjectMapper mapper = new ObjectMapper();

			final String jsonInString = mapper.writeValueAsString(responseJson);
			
			writer.write(jsonInString);
			
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
	}
	
	/**
	 * Token invalid - Not found
	 * 
	 * @param response - HttpServletResponse
	 * 
	 * @throws JsonProcessingException	
	 * @throws IOException
	 */
	private void notFound(final HttpServletResponse response) throws JsonProcessingException, IOException {
		try (final PrintWriter writer = response.getWriter()) {
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.setCharacterEncoding("UTF-8");
			
			final Response<AccountResponse> responseJson = new Response<AccountResponse>()
					.addHttpStatus(HttpStatus.NOT_FOUND)
					.addGeneralMessage("Não autorizado");
			
			final ObjectMapper mapper = new ObjectMapper();

			final String jsonInString = mapper.writeValueAsString(responseJson);
			
			writer.write(jsonInString);
			
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}
	}
}
