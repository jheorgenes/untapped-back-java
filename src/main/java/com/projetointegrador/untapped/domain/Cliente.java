package com.projetointegrador.untapped.domain;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Cliente extends Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idade;

	public Cliente() {
		super();
	}
	
	public Cliente(Long id, String nome, String email, String contato, String login, String senha) {
		super(id, nome, email, contato, login, senha);
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}
}
