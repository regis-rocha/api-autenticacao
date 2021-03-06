package br.com.projetos.regis.api.auth.request;

import java.io.Serializable;

/**
 * Account to encapsulate information about Phone
 * 
 * @author regis.rocha
 *
 */
public class PhoneRequest implements Serializable {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = -5672604806224805894L;

	private String number;

	private String ddd;

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the ddd
	 */
	public String getDdd() {
		return ddd;
	}

	/**
	 * @param ddd
	 *            the ddd to set
	 */
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PhoneRequest [number=" + number + ", ddd=" + ddd + "]";
	}

	public PhoneRequest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param number
	 * @param ddd
	 */
	public PhoneRequest(String number, String ddd) {
		super();
		this.number = number;
		this.ddd = ddd;
	}

}
