package br.gov.serpro.inscricao.security;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

/*
 * A anotação @javax.enterprise.context.SessionScoped, pertencente ao CDI, mantém a instância na sessão do usuário
 */
@SessionScoped
public class Credenciais implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private String senha;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

}
