package br.com.projeto.regis.api.auth.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.projeto.regis.api.auth.domain.Account;
import br.com.projeto.regis.api.auth.exception.AuthException;
import br.com.projeto.regis.api.auth.exception.FindException;
import br.com.projeto.regis.api.auth.exception.PersistException;

@Repository
@Transactional
public class AccountRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void persist(final Account account) throws PersistException {
		try {
			this.entityManager.persist(account);
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	public Account findById(final String id) throws FindException {
		try {
			return this.entityManager.find(Account.class, id);
		} catch (Exception e) {
			throw new FindException(e);
		}
	}

	public Account update(final Account account) throws PersistException {
		try {
			return this.entityManager.merge(account);
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	public void signin(final Account account) throws FindException, AuthException {
		try {
			this.entityManager.createNamedQuery("Account.signin", Account.class)
					.setParameter("email", account.getEmail())
					.setParameter("password", account.getPassword()).getSingleResult();
			
		} catch (NoResultException e) {
			throw new AuthException("Usuario ou senha invalidos");
		} catch (Exception e) {
			throw new FindException(e);
		}
	}
}
