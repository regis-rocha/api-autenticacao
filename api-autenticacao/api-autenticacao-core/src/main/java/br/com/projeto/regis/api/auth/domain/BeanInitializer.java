package br.com.projeto.regis.api.auth.domain;

import br.com.projeto.regis.api.auth.exception.InitilizationBeanException;

public interface BeanInitializer<T> {

	T initilizeBean() throws InitilizationBeanException;
}
