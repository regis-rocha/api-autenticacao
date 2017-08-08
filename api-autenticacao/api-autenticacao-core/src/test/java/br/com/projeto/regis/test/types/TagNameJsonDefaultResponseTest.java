package br.com.projeto.regis.test.types;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projeto.regis.api.auth.types.Response;

public class TagNameJsonDefaultResponseTest {

	@Test
	public void testExistenciaTagMesssageJson() {
		try {
			
			// initialize the attribute message with content 'OK'
			final Response<Object> successResponse = new Response<Object>().createSuccessResponse();
			
			final ObjectMapper mapper = new ObjectMapper();
			final String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(successResponse);
			
			final JsonNode jsonNode = mapper.readTree(json);
			
			final JsonNode nodeMessage = jsonNode.findValue("message");
			
			Assert.assertNotNull(nodeMessage);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testTagNameInfoJson() {
		try {
			final Response<Object> successResponse = new Response<Object>().createSuccessResponse();
			
			// add additional information 
			successResponse.addInfo("page", "1");
			
			final ObjectMapper mapper = new ObjectMapper();
			final String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(successResponse);
			
			final JsonNode jsonNode = mapper.readTree(json); 
			
			// tag additional-information
			final JsonNode tagAdditionalInfo = jsonNode.findValue("additional-information");
			
			Assert.assertNotNull(tagAdditionalInfo);
			
			// sub tag - tag info
			final JsonNode tagInfo = tagAdditionalInfo.findValue("info");
			
			Assert.assertNotNull(tagInfo);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	@Test
	public void testTagNameBodyJson() {
		try {
			final Response<Object> successResponse = new Response<Object>().createSuccessResponse();
			
			// add body
			successResponse.addBody("Body JSON");
			
			final ObjectMapper mapper = new ObjectMapper();
			final String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(successResponse);
			
			final JsonNode jsonNode = mapper.readTree(json); 
			
			// tag body
			final JsonNode tagBody = jsonNode.findValue("body-attribute");
			
			Assert.assertNotNull(tagBody);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	@Test
	public void testTagNameCollectionBodyJson() {
		try {
			final Response<Object> successResponse = new Response<Object>().createSuccessResponse();
			
			// add body
			final Collection<Object> itemsBody = Arrays.asList(new Object[]{"Item 1", "Item 2", "Item N"});
			successResponse.addCollectionBody(itemsBody);
			
			final ObjectMapper mapper = new ObjectMapper();
			final String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(successResponse);
			
			final JsonNode jsonNode = mapper.readTree(json); 
			
			// tag body
			final JsonNode tagBody = jsonNode.findValue("body-collection-attributes");
			
			Assert.assertNotNull(tagBody);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testTagHttpStatusCodeJson() {
		try {
			final Response<Object> successResponse = new Response<Object>().createSuccessResponse();
			
			final ObjectMapper mapper = new ObjectMapper();
			final String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(successResponse);
			
			final JsonNode jsonNode = mapper.readTree(json); 
			
			// tag body
			final JsonNode tagHttpStatusCode = jsonNode.findValue("http-status-code");
			
			Assert.assertNotNull(tagHttpStatusCode);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testTagHttpStatusDescriptionJson() {
		try {
			final Response<Object> successResponse = new Response<Object>().createSuccessResponse();
			
			final ObjectMapper mapper = new ObjectMapper();
			final String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(successResponse);
			
			final JsonNode jsonNode = mapper.readTree(json); 
			
			System.out.println(json);
			
			// tag body
			final JsonNode tagHttpStatusCode = jsonNode.findValue("http-status-description");
			
			Assert.assertNotNull(tagHttpStatusCode);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
