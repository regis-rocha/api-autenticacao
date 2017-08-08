package br.com.projeto.regis.api.auth.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Table(name = "phone")
@Entity
public class Phone implements Serializable {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 3087932192557656585L;

	@Id
	private String id;

	@Column(name = "number")
	private Long number;
	@Column(name = "ddd")
	private Integer ddd;

	/**
	 * @return the number
	 */
	public Long getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(Long number) {
		this.number = number;
	}

	/**
	 * @return the ddd
	 */
	public Integer getDdd() {
		return ddd;
	}

	/**
	 * @param ddd
	 *            the ddd to set
	 */
	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	public Phone() {
	}

	/**
	 * @param number
	 * @param ddd
	 */
	public Phone(Long number, Integer ddd) {
		super();
		this.number = number;
		this.ddd = ddd;
	}

	/**
	 * To String
	 */
	@Override
	public String toString() {
		return "Phone [id=" + id + ", number=" + number + ", ddd=" + ddd + "]";
	}

	/**
	 * Hash code by id
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * equals by id
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Phone)) {
			return false;
		}
		Phone other = (Phone) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Before persist generating Random ID
	 */
	@PrePersist
	public void prePersist() {
		this.id = String.valueOf(UUID.randomUUID());
	}

}