package br.com.projeto.regis.api.auth.service.impl;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.regis.api.auth.domain.Account;
import br.com.projeto.regis.api.auth.exception.InvalidSessionException;
import br.com.projeto.regis.api.auth.repository.AccountRepository;
import br.com.projeto.regis.api.auth.service.UserProfileService;

/**
 * Perfil usuario Service interface
 * 
 * @author regis.rocha
 *
 */
@Service
public class UserProfileServiceImpl implements UserProfileService {

	/**
	 * LOG
	 */
	private static final Logger LOG = Logger.getLogger(UserProfileServiceImpl.class.getName());
	
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
	 * @throws InvalidSessionException
	 */
	@Override
	public Account validateSessionUser(final String idAccount, final String token) throws InvalidSessionException {
		LOG.info("validating session account");
		
		try {
			IMPLEMENTAR REGRAS ABAIXO DO TOKEN
		}
	}

	
	/*
	Caso o token não exista, retornar erro com status apropriado com a mensagem "Não autorizado".
	
	 Caso o token exista, buscar o usuário pelo id passado no path e comparar se o token no modelo é
	igual ao token passado no header.
	
	 Caso não seja o mesmo token, retornar erro com status apropriado e mensagem "Não autorizado"
	
	 Caso seja o mesmo token, verificar se o último login foi a MENOS que 30 minutos atrás. Caso não
	seja a MENOS que 30 minutos atrás, retornar erro com status apropriado com mensagem "Sessão
	inválida".
	
	 Caso tudo esteja ok, retornar o usuário no mesmo formato do retorno do Login.
	Requisitos
	*/
}
