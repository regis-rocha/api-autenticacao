package br.com.projeto.regis.api.auth.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.projeto.regis.api.auth.domain.Account;
import br.com.projeto.regis.api.auth.domain.Phone;
import br.com.projeto.regis.api.auth.response.AccountResponse;
import br.com.projeto.regis.api.auth.response.PhoneResponse;
import br.com.projeto.regis.api.auth.util.DateUtil;
import br.com.projetos.regis.api.auth.request.AccountRequest;
import br.com.projetos.regis.api.auth.request.PhoneRequest;

/**
 * Account helper class
 * 
 * @author regis.rocha
 *
 */
@Component
public class AccountHelper {

	
	/**
	 * Convert object Account to AccountResponse
	 * 
	 * @param accountEntity - Account
	 * 
	 * @return AccountResponse
	 */
	public AccountResponse convertToResponse(final Account accountEntity) {
		final AccountResponse response = new AccountResponse();
		
		// convert field create
		response.setCreated(DateUtil.convertCalendarToStringDateAndTime(accountEntity.getCreated()));
		
		// convert field email
		response.setEmail(accountEntity.getEmail());
		
		// convert field id 
		response.setId(accountEntity.getId());
		
		// convert field last login
		response.setLastLogin(DateUtil.convertCalendarToStringDateAndTime(accountEntity.getLastLogin()));
		
		// convert field modified
		response.setModified(DateUtil.convertCalendarToStringDateAndTime(accountEntity.getModified()));
		
		// convert field name
		response.setName(accountEntity.getName());
		
		// convert field phones
		response.setPhones(this.convertPhoneEntityToResponse(accountEntity.getPhones()));
		
		// convert field token
		response.setToken(accountEntity.getToken());
		
		return response;
		
	}

	/**
	 * Convert object entity Phone to PhoneResponse
	 * 
	 * @param phones
	 * 
	 * @return List<PhoneResponse>
	 */
	private List<PhoneResponse> convertPhoneEntityToResponse(final List<Phone> phones) {
		if (phones != null) {
			final List<PhoneResponse> list = new ArrayList<>();
			
			phones.forEach(p -> {
				list.add(new PhoneResponse(String.valueOf(p.getNumber()), String.valueOf(p.getDdd())));
			});
			
			return list;
		} else {
			return Collections.emptyList();
		}
	}

	/**
	 * convert request object to entity
	 * 
	 * @param request - AccountRequest
	 * 
	 * @return Account
	 */
	public Account convertRequestToEntity(final AccountRequest request) {
		final Account account = new Account();
		
		account.setEmail(request.getEmail());
		account.setName(request.getName());
		account.setPassword(request.getPassword());
		account.setPhones(this.convertPhoneRequestToEntity(request.getPhones()));
		
		return account;
	}

	/**
	 * Convert object PhoneRequest to Phone entity
	 * 
	 * @param phones - List<PhoneRequest>
	 * 
	 * @return List<Phone>
	 */
	private List<Phone> convertPhoneRequestToEntity(final List<PhoneRequest> phones) {
		final List<Phone> list = new ArrayList<>();
		
		if (phones != null) {
			phones.forEach(p -> {
				list.add(new Phone(Long.valueOf(p.getNumber()), Integer.valueOf(p.getDdd())));
			});
		}
		
		return list;
	}
}
