package br.com.projeto.regis.api.auth.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.projeto.regis.api.auth.exception.InitilizationBeanException;
import br.com.projeto.regis.api.auth.exception.ValidationException;
import br.com.projeto.regis.api.auth.validate.BeanValidation;
import br.com.projeto.regis.api.auth.validate.ValidateHelper;

@Entity
@Table(name = "account")
@XmlRootElement
@NamedQueries(value = {
		@NamedQuery(name = "Account.findByEmail",	query = "SELECT a FROM Account a WHERE a.email=:email"),
		@NamedQuery(name = "Account.signin", 		query = "SELECT a FROM Account a WHERE email=:email AND password=:password")})
public class Account implements Serializable, BeanInitializer<Account>, BeanValidation {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1955650772796286231L;

	@Id
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Calendar created;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified")
	private Calendar modified;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login")
	private Calendar lastLogin;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "account_phone", joinColumns = {@JoinColumn(name = "id_phone", referencedColumnName = "id")})
	private List<Phone> phones;
	
	@Column(name = "token")
	private String token;
	
	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timestamp_created_token")
	private Calendar timestapCreatedToken;
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
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
	 * @param email the email to set
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
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the phones
	 */
	public List<Phone> getPhones() {
		return phones;
	}

	/**
	 * @param phones the phones to set
	 */
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @return the created
	 */
	public Calendar getCreated() {
		return created;
	}

	/**
	 * @return the modified
	 */
	public Calendar getModified() {
		return modified;
	}

	/**
	 * @return the lastLogin
	 */
	public Calendar getLastLogin() {
		return lastLogin;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @return the timestapCreatedToken
	 */
	public Calendar getTimestapCreatedToken() {
		return timestapCreatedToken;
	}

	@Override
	public Account initilizeBean() throws InitilizationBeanException {
		return new Account();
	}

	@Override
	public boolean validate() throws ValidationException {
		final ValidateHelper validateHelper = new ValidateHelper();
		
		validateHelper.isEmpty(this.name, "Field name is null");
		
		validateHelper.isEmpty(this.email, "Field email is null");
		
		validateHelper.isEmpty(this.password, "Field password is null");
		
		return true;
	}
	
	/**
	 * Before persist generating Random ID
	 */
	@PrePersist
	public void prePersist() {
		this.id = String.valueOf(UUID.randomUUID());
		
		this.created = Calendar.getInstance();
		
		this.modified = Calendar.getInstance();
		
		this.timestapCreatedToken = Calendar.getInstance();
		
		this.token = String.valueOf(UUID.randomUUID());
	}
}
