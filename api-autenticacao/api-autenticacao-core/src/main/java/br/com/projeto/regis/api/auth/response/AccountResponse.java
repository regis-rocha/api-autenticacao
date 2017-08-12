package br.com.projeto.regis.api.auth.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class to encapsulate information about account that will be send in response.
 * 
 * @author regis.rocha
 *
 */
public class AccountResponse implements Serializable {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = -7373721968245873638L;

	private String id;

	private String name;

	private String email;

	private String created;

	private String modified;

	private String lastLogin;

	private List<PhoneResponse> phones;

	private String token;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the created
	 */
	public String getCreated() {
		return created;
	}

	/**
	 * @param created
	 *            the created to set
	 */
	public void setCreated(String created) {
		this.created = created;
	}

	/**
	 * @return the modified
	 */
	public String getModified() {
		return modified;
	}

	/**
	 * @param modified
	 *            the modified to set
	 */
	public void setModified(String modified) {
		this.modified = modified;
	}

	/**
	 * @return the lastLogin
	 */
	@JsonProperty("last_login")
	public String getLastLogin() {
		return lastLogin;
	}

	/**
	 * @param lastLogin
	 *            the lastLogin to set
	 */
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * @return the phones
	 */
	public List<PhoneResponse> getPhones() {
		return phones;
	}

	/**
	 * @param phones
	 *            the phones to set
	 */
	public void setPhones(List<PhoneResponse> phones) {
		this.phones = phones;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccountResponse [id=" + id + ", name=" + name + ", email=" + email
				+ ", created=" + created + ", modified=" + modified + ", lastLogin=" + lastLogin + ", phones=" + phones
				+ ", token=" + token + "]";
	}

}
