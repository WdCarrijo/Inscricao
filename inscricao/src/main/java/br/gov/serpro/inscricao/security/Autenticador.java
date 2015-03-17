package br.gov.serpro.inscricao.security;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.security.User;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

public class Autenticador implements Authenticator {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Credenciais credenciais;	
	
	@Inject
	private ResourceBundle bundle;

	@Override
	public void authenticate() throws Exception {		
		if(credenciais.getNome().equals("secretaria") && credenciais.getSenha().equals("segredo")){						
		}else{
			throw new RuntimeException(bundle.getString("usuarioNaoAutenticado"));
		}
	}

	@Override
	public void unauthenticate() throws Exception {
	}	


	@Override
	public User getUser() {
		return new User(){
			private static final long serialVersionUID = 1L;

			@Override
			public String getId() {	
				return null;
			}

			@Override
			public Object getAttribute(Object key) {		
				return null;
			}

			@Override
			public void setAttribute(Object key, Object value) {				
			}
			
		};
	}

}
