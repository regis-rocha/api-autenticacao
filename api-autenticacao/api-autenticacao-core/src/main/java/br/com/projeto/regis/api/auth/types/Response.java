package br.com.projeto.regis.api.auth.types;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that encapsulates the default response.
 * 
 * @author regis.rocha
 *
 */
@XmlRootElement(name = "response")
public final class Response<BodyType> implements Serializable {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 3118689620114658552L;

	private HttpStatus httpStatus;

	private String generalMessage;

	private ResponseInfo responseInfo;

	private BodyType reponseBody;
	
	private Collection<BodyType> collectionBody;

	/**
	 * @return the http status code
	 */
	@JsonProperty("http-status-code")
	public Integer getHttpStatusCode() {
		if (this.httpStatus == null) {
			this.httpStatus = HttpStatus.NO_CONTENT;
			return httpStatus.value();
		}
		
		return httpStatus.value();
	}
	
	/**
	 * @return the http status description
	 */
	@JsonProperty("http-status-description")
	public String getHttpStatusDescription() {
		if (this.httpStatus == null) {
			this.httpStatus = HttpStatus.NO_CONTENT;
			return httpStatus.getReasonPhrase();
		}
		
		return httpStatus.getReasonPhrase();
	}

	/**
	 * @return the generalMessage
	 */
	@JsonProperty("message")
	public String getGeneralMessage() {
		if (StringUtils.isBlank(this.generalMessage)) {
			this.generalMessage = "";
		}
		
		return generalMessage;
	}

	/**
	 * @return the responseInfo
	 */
	@JsonProperty("additional-information")
	public ResponseInfo getResponseInfo() {
		if (this.responseInfo == null) {
			return new ResponseInfo();
		}
		
		return responseInfo;
	}

	/**
	 * @return the reponseBody
	 */
	@JsonProperty("body-attribute")
	public BodyType getReponseBody() {
		return reponseBody;
	}
	

	/**
	 * @return the collectionBody
	 */
	@JsonProperty("body-collection-attributes")
	public Collection<BodyType> getCollectionBody() {
		if (this.collectionBody == null) {
			this.collectionBody = Collections.emptyList();
		}
		
		return collectionBody;
	}

	/**
	 * Create object that represents the success response.
	 * 
	 * Success response return 
	 * 	. HTTP Status 		- OK 
	 *  . general Message 	- OK 
	 *  . responseInfo 		- empty 
	 *  . reponseBody 		- empty
	 * 
	 * @return Response<T>
	 */
	public Response<BodyType> createSuccessResponse() {
		// status OK http
		this.httpStatus = HttpStatus.OK;

		// message OK
		this.generalMessage = HttpStatus.OK.getReasonPhrase();

		// empty additional information
		this.responseInfo = new ResponseInfo();

		return this;
	}
	
	/**
	 * Create object that represents the success created response.
	 * 
	 * Success response return 
	 * 	. HTTP Status 		- CREATED 
	 *  . general Message 	- CREATED 
	 *  . responseInfo 		- empty 
	 *  . reponseBody 		- empty
	 * 
	 * @return Response<T>
	 */
	public Response<BodyType> createSuccessCreatedResponse() {
		// status CREATED http
		this.httpStatus = HttpStatus.CREATED;

		// message CREATED
		this.generalMessage = HttpStatus.CREATED.getReasonPhrase();

		// empty additional information
		this.responseInfo = new ResponseInfo();

		return this;
	}

	
	/**
	 * Create object that represents the internal server error response.
	 * 
	 * Success response return 
	 * 	. HTTP Status 		- INTERNAL_SERVER_ERROR 
	 *  . general Message 	- Internal Server Error 
	 *  . responseInfo 		- empty 
	 *  . reponseBody 		- empty
	 * 
	 * @return Response<T>
	 */
	public Response<BodyType> createErrorResponse() {
		// status INTERNAL_SERVER_ERROR http
		this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

		// message INTERNAL_SERVER_ERROR
		this.generalMessage = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();

		// empty additional information
		this.responseInfo = new ResponseInfo();
		
		return this;
	}

	
	/**
	 * Create object that represents no content.
	 * 
	 * Success response return 
	 * 	. HTTP Status 		- NO_CONTENT 
	 *  . general Message 	- No Content
	 *  . responseInfo 		- empty 
	 *  . reponseBody 		- empty
	 * 
	 * @return Response<T>
	 */
	public Response<BodyType> createEmptyResponse() {
		// status NO_CONTENT http
		this.httpStatus = HttpStatus.NO_CONTENT;

		// message No Content
		this.generalMessage = HttpStatus.NO_CONTENT.getReasonPhrase();

		// empty additional information
		this.responseInfo = new ResponseInfo();

		return this;
	}
	
	
	/**
	 * Add HTTP status.
	 * 
	 * @param pHttpStatus - HttpStatus
	 * 
	 * @return Response<T>
	 */
	public Response<BodyType> addHttpStatus(final HttpStatus pHttpStatus) {
		this.httpStatus = pHttpStatus;
		return this;
	}
	
	/**
	 * Add message.
	 * 
	 * @param message - String
	 * 
	 * @return Response<T>
	 */
	public Response<BodyType> addGeneralMessage(final String message) {
		this.generalMessage = message;
		return this;
	}
	
	
	/**
	 * Add info.
	 * 
	 * @param message - String
	 * 
	 * @return Response<T>
	 */
	public Response<BodyType> addInfo(final String key, final String value) {
		this.getResponseInfo().addInformation(key, value);
		
		return this;
	}
	
	
	/**
	 * Add body in a single attribute.
	 * 
	 * @param body - BodyType
	 * 
	 * @return Response<T>
	 */
	public Response<BodyType> addBody(final BodyType body) {
		this.reponseBody = body;
		
		return this;
	}
	
	/**
	 * Add collection body.
	 * 
	 * @param pCollectionBody - Collection<BodyType>
	 * 
	 * @return Response<T>
	 */
	public Response<BodyType> addCollectionBody(final Collection<BodyType> pCollectionBody) {
		if (this.collectionBody == null) {
			this.collectionBody = pCollectionBody;
			
			return this;
		}
		
		this.collectionBody.addAll(pCollectionBody);
		
		return this;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Response [httpStatus=" + httpStatus + ", generalMessage=" + generalMessage + ", responseInfo="
				+ responseInfo + ", reponseBody=" + reponseBody + ", collectionBody=" + collectionBody + "]";
	}
}
