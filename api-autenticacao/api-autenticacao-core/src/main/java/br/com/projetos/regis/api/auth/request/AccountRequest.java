package br.com.projetos.regis.api.auth.request;

import java.io.Serializable;
import java.util.List;

/**
 * Class to encapsulate information about Account
 * 
 * @author regis.rocha
 *
 */
public class AccountRequest implements Serializable {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 4678847214541082050L;

	private String id;
	
	private String name;

	private String email;

	private String password;

	private List<PhoneRequest> phones;
	
	private String token;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the phones
	 */
	public List<PhoneRequest> getPhones() {
		return phones;
	}

	/**
	 * @param phones
	 *            the phones to set
	 */
	public void setPhones(List<PhoneRequest> phones) {
		this.phones = phones;
	}
	
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccountRequest [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", phones=" + phones + ", token=" + token + "]";
	}

}
