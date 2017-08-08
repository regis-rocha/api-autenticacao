package br.com.projeto.regis.api.auth.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.regis.api.auth.domain.Account;
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
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ValidateHelper validate;
	
	@Override
	public void create(final Account account) throws PersistException {
		LOG.info("Criando conta");
		
		this.validate.isObjectNull(account, "Dados da conta nao foram preenchidos");
		
		account.validate();
		
		try {
			this.accountRepository.persist(account);
		} catch (PersistException e) {
			LOG.error("", e);
			throw e;
		}
	}

	
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