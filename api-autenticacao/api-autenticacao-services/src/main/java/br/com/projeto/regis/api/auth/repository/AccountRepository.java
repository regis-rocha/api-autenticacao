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

/**
 * Account Repository
 * 
 * @author regis.rocha
 *
 */
@Repository
@Transactional
public class AccountRepository {
	
	/**
	 * @Inject
	 */
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Persist account
	 * 
	 * @param account - Account
	 * 
	 * @throws PersistException
	 */
	public void persist(final Account account) throws PersistException {
		try {
			this.entityManager.persist(account);
			
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	
	/**
	 * Find account by ID
	 * 
	 * @param id - String
	 * 
	 * @return Account
	 * 
	 * @throws FindException
	 */
	public Account findById(final String id) throws FindException {
		try {
			return this.entityManager.find(Account.class, id);
		} catch (Exception e) {
			throw new FindException(e);
		}
	}
	
	/**
	 * Find account by email
	 * 
	 * @param email - String
	 * 
	 * @return Account
	 * 
	 * @throws FindException
	 */
	public Account findByEmail(final String email) throws FindException {
		try {
			return this.entityManager.createNamedQuery("Account.findByEmail", Account.class)
					.setParameter("email", email).getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new FindException(e);
		}
	}

	
	/**
	 * Update account
	 * 
	 * @param account - Account
	 * 
	 * @return Account
	 * 
	 * @throws PersistException
	 */
	public Account update(final Account account) throws PersistException {
		try {
			return this.entityManager.merge(account);
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	
	/**
	 * Sign in
	 * 
	 * @param account - Account
	 * 
	 * @throws FindException
	 * @throws AuthException
	 */
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
