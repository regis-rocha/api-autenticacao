package br.com.projeto.regis.api.auth.service.impl;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.regis.api.auth.domain.Account;
import br.com.projeto.regis.api.auth.exception.AccountNotFoundException;
import br.com.projeto.regis.api.auth.exception.FindException;
import br.com.projeto.regis.api.auth.exception.InvalidTokenAndAccountException;
import br.com.projeto.regis.api.auth.exception.SessionTimeoutException;
import br.com.projeto.regis.api.auth.exception.TokenNotFoundException;
import br.com.projeto.regis.api.auth.repository.AccountRepository;
import br.com.projeto.regis.api.auth.service.AccountProfileService;

/**
 * Account profile Service interface
 * 
 * @author regis.rocha
 *
 */
@Service
public class AccountProfileServiceImpl implements AccountProfileService {

	/**
	 * LOG
	 */
	private static final Logger LOG = Logger.getLogger(AccountProfileServiceImpl.class.getName());

	/**
	 * Timeout minutes
	 */
	private static final Integer TIMEOUT_MINUTES = 30;
	
	/**
	 * @Inject
	 */
	@Autowired
	private AccountRepository accountRepository;
	
	
	/**
	 * Validate if account session is valid
	 * 
	 * @param idAccount		- String
	 * @param token			- String
	 * 
	 * @return Account
	 * 
	 * @throws TokenNotFoundException 
	 * @throws AccountNotFoundException
	 * @throws SessionTimeoutException
	 * @throws InvalidTokenAndAccountException
	 * @throws FindException 
	 */
	@Override
	public Account validateSessionAccount(final String idAccount, final String token) 
			throws TokenNotFoundException, AccountNotFoundException, SessionTimeoutException, InvalidTokenAndAccountException, FindException {
		
		LOG.info("validating session account");
		
		try {
			// find token
			final Account accountToken = this.accountRepository.findByToken(token);
			
			// message nao autorizado
			final String naoAutorizado = "Não autorizado";
			
			if (accountToken == null) {
				throw new TokenNotFoundException(naoAutorizado);
			}
			
			// find account by id
			final Account accountId = this.accountRepository.findById(idAccount);
			
			if (accountId == null) {
				throw new AccountNotFoundException(naoAutorizado);
			}
			
			// compare token account and token form
			if (!accountId.getToken().equals(token)) {
				throw new InvalidTokenAndAccountException(naoAutorizado);
			}
			
			// timeout?
			isSessionTimeout(accountId);
			
			return accountId;
			
		} catch (TokenNotFoundException | AccountNotFoundException | InvalidTokenAndAccountException e) {
			throw e;
		} catch (SessionTimeoutException e) {
			throw e;
		}
	}


	/**
	 * Verify if last login exceeded 30 minutes
	 * 
	 * @param accountId
	 * 
	 * @return boolean - TRUE if time last login exceeded 30 minutes
	 */
	private boolean isSessionTimeout(final Account accountId) throws SessionTimeoutException {
		// instant now
		final Instant now = Instant.now();
		
		// instant last login
		final Instant lastLogin = accountId.getLastLogin().toInstant();
		
		// Duration difference between now and last login
		final Duration timeDiff = Duration.between(lastLogin, now);
		
		// minutes difference between now and last login
		final long minutesLastSession = timeDiff.toMinutes();
		
		if (minutesLastSession > TIMEOUT_MINUTES) {
			throw new SessionTimeoutException("Sessão inválida");
		}
		
		return false;
	}
}