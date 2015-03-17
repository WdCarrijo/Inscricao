package br.gov.serpro.inscricao.security;

import br.gov.frameworkdemoiselle.security.Authorizer;

public class Autorizador implements Authorizer {
	private static final long serialVersionUID = 1L;

	//Tem papel
	@Override
	public boolean hasRole(String role) throws Exception {		
		return true;
	}

	//Tem permissão
	@Override
	public boolean hasPermission(String resource, String operation)
			throws Exception {
		return true;
	}

}
