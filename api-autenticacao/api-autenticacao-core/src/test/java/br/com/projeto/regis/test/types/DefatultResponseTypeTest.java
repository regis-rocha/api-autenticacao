package br.com.projeto.regis.test.types;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import br.com.projeto.regis.api.auth.types.Response;
import br.com.projeto.regis.api.auth.types.ResponseInfo;

public class DefatultResponseTypeTest {

	@Test
	public void testResultSuccess() {
		final Response<Object> response = new Response<Object>().createSuccessResponse();
		Assert.assertEquals(HttpStatus.OK.value(), response.getHttpStatusCode().intValue());
		Assert.assertEquals(HttpStatus.OK.getReasonPhrase(), response.getHttpStatusDescription());
	}
	
	@Test
	public void testResultError() {
		final Response<Object> response = new Response<Object>().createErrorResponse();
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getHttpStatusCode().intValue());
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), response.getHttpStatusDescription());
	}
	
	@Test
	public void testResultNoContent() {
		final Response<Object> response = new Response<Object>().createEmptyResponse();
		Assert.assertEquals(HttpStatus.NO_CONTENT.value(), response.getHttpStatusCode().intValue());
		Assert.assertEquals(HttpStatus.NO_CONTENT.getReasonPhrase(), response.getHttpStatusDescription());
	}
	
	@Test
	public void testaddCustomStatusResult() {
		final Response<Object> response = new Response<Object>().createEmptyResponse();
		
		response.addHttpStatus(HttpStatus.CREATED);
		
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getHttpStatusCode().intValue());
		Assert.assertEquals(HttpStatus.CREATED.getReasonPhrase(), response.getHttpStatusDescription());
	}
	
	@Test
	public void testAddGeneralMessageResult() {
		final Response<Object> response = new Response<Object>().createEmptyResponse();
		response.addGeneralMessage("teste");
		Assert.assertEquals("teste", response.getGeneralMessage());
	}
	
	@Test
	public void testAddInformationResult() {
		final Response<Object> response = new Response<Object>().createEmptyResponse();
		response.addInfo("encoding", "UTF-8");
		
		ResponseInfo info = new ResponseInfo();
		info.addInformation("encoding", "UTF-8");
		
		Assert.assertEquals(info, response.getResponseInfo());
	}
	
	@Test
	public void testBodyTypeClassResult() {
		final Response<Object> response = new Response<Object>().createEmptyResponse();
		response.addBody("Body");
		
		Assert.assertEquals(String.class.getName(), response.getReponseBody().getClass().getName());
	}
	
	@Test
	public void testBodyContentResult() {
		final Response<Object> response = new Response<Object>().createEmptyResponse();
		String body = "Body";
		response.addBody(body);
		
		Assert.assertEquals(body, response.getReponseBody());
	}
	
	@Test
	public void testBodyContentCollectionResult() {
		final Response<String> response = new Response<String>().createEmptyResponse();

		final List<String> list = new ArrayList<>();
		
		list.add("joao");
		list.add("maria");
		
		response.addCollectionBody(list);
		
		Assert.assertEquals(list, response.getCollectionBody());
	}
}
