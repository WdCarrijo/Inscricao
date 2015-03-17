package br.gov.serpro.inscricao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Aluno {
	private String nome;

	@Id
	@GeneratedValue
	private Integer matricula;

	public Aluno() {
	}

	public Aluno(String nome) {
		this.nome = nome;
	}

	@Override
	public boolean equals(Object outro) {
		return ((Aluno) outro).nome.equals(this.nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
}
