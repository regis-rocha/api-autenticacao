package br.com.projeto.regis.api.auth.types;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class to encapsulate additional information in the response.
 *
 * @author regis.rocha
 *
 */
@XmlRootElement
public class ResponseInfo implements Serializable {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = -2624527633241685377L;

	/**
	 * Map with additional information.
	 */
	private Map<String, String> additionalInformation;

	/**
	 * Return map informations
	 * 
	 * @return the additionalInformation
	 */
	@JsonProperty("info")
	public Map<String, String> getAdditionalInformation() {
		if (this.additionalInformation == null) {
			this.additionalInformation = new HashMap<>();
		}

		return additionalInformation;
	}

	/**
	 * Add information.
	 * 
	 * @param keyInfomation
	 *            - String
	 * 
	 * @param valueInformation
	 *            - String
	 * 
	 * @return Map<String, String>
	 */
	public Map<String, String> addInformation(final String keyInfomation, final String valueInformation) {
		this.getAdditionalInformation().put(keyInfomation, valueInformation);

		return this.getAdditionalInformation();
	}

	/**
	 * Create instance ResponseInfo.
	 * 
	 * @return ResponseInfo
	 */
	public static ResponseInfo createInstance() {
		return new ResponseInfo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalInformation == null) ? 0 : additionalInformation.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ResponseInfo)) {
			return false;
		}
		ResponseInfo other = (ResponseInfo) obj;
		if (additionalInformation == null) {
			if (other.additionalInformation != null) {
				return false;
			}
		} else if (!additionalInformation.equals(other.additionalInformation)) {
			return false;
		}
		return true;
	}

}
