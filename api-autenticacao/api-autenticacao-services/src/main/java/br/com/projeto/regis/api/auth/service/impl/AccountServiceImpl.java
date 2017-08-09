package br.com.projeto.regis.api.auth.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.regis.api.auth.domain.Account;
import br.com.projeto.regis.api.auth.exception.AccountExistsException;
import br.com.projeto.regis.api.auth.exception.AuthException;
import br.com.projeto.regis.api.auth.exception.FindException;
import br.com.projeto.regis.api.auth.exception.PersistException;
import br.com.projeto.regis.api.auth.repository.AccountRepository;
import br.com.projeto.regis.api.auth.service.AccountService;
import br.com.projeto.regis.api.auth.validate.ValidateHelper;

@Service
public class AccountServiceImpl implements AccountService {

	/**
	 * LOG
	 */
	private static final Logger LOG = Logger.getLogger(AccountServiceImpl.class);
	
	/**
	 * @Inject
	 */
	@Autowired
	private AccountRepository accountRepository;
	
	/**
	 * @Inject
	 */
	@Autowired
	private ValidateHelper validate;
	
	
	/**
	 * Create account
	 * 
	 * @param account - Account
	 * 
	 * @throws PersistException
	 * 
	 * @throws AccountExistsException
	 */
	@Override
	public void create(final Account account) throws PersistException, AccountExistsException {
		LOG.info("Criando conta");
		
		this.validate.isObjectNull(account, "Dados da conta nao foram preenchidos");
		
		account.validate();
		
		try {
			LOG.info("Verificando se conta existe");
			final Account accountDb = this.accountRepository.findByEmail(account.getEmail());
			if (accountDb != null) {
				final String message = "E-mail já existente";
				LOG.warn(message);
				throw new AccountExistsException(message);
			}
		} catch (FindException e) {
			LOG.error("", e);
			throw new PersistException(e);
		}
		
		try {
			LOG.info("Persistindo conta");
			this.accountRepository.persist(account);
		} catch (PersistException e) {
			LOG.error("", e);
			throw e;
		}
	}

	
	/**
	 * Find an specific account
	 * 
	 * @param id - String
	 * 
	 * @return Account
	 * 
	 * @throws FindException
	 */
	@Override
	public Account find(final String id) throws FindException {
		LOG.info("Pesquisando conta de ID: " + id);
		
		this.validate.isObjectNull(id, "ID da conta a ser pesquisada nao foi preenchido");
		
		try {
			return this.accountRepository.findById(id);
		} catch (FindException e) {
			LOG.error("", e);
			throw e;
		}
	}


	/**
	 * Update an account
	 * 
	 * @param id - Integer
	 * 
	 * @throws PersistException
	 */
	@Override
	public void update(final Account account) throws PersistException {
		LOG.info("Atualizando conta de ID");
		
		this.validate.isObjectNull(account, "Dados da conta nao foram preenchidos");
		
		account.validate();
		
		try {
			final Account accountBD = this.accountRepository.findById(account.getId());
			
			if (accountBD == null) {
				throw new PersistException("Conta nao encontrada");
			}
			
			this.accountRepository.update(account);
		} catch (FindException e) {
			LOG.error("Conta nao encontrada", e);
			throw new PersistException("Conta nao encontrada");
		} catch (PersistException e) {
			LOG.error("", e);
			throw e;
		}
	}


	/**
	 * Sign in account
	 * 
	 * @param account - Account
	 * 
	 * @throws FindException, AuthException
	 */
	@Override
	public void signin(final Account account) throws FindException, AuthException {
		LOG.info("signin account");
		
		this.validate.isObjectNull(account, "Dados da conta nao foram preenchidos");
		
		this.validate.isEmpty(account.getEmail(), "E-mail nao foi preenchido");
		
		this.validate.isEmpty(account.getPassword(), "Senha nao foi preenchida");
		
		try {
			this.accountRepository.signin(account);
		} catch (AuthException e) {
			LOG.error("Usuario ou senha invalidos");
			throw e;
		} catch (FindException e) {
			LOG.error("", e);
			throw e;
		}
	}
}