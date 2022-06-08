package com.projetointegrador.untapped.dtos;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.projetointegrador.untapped.domain.Cliente;
import com.projetointegrador.untapped.domain.Endereco;

public class ClienteDTO {
	
	private Long id;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String nome;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email
	private String email;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String contato;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String login;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String senha;
	@NotEmpty(message = "Preenchimento obrigatório")
	
	private List<Endereco> enderecos;
	
	public ClienteDTO() {
		super();
	}
	
	public ClienteDTO(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.contato = cliente.getContato();
		this.login = cliente.getLogin();
		this.senha = cliente.getSenha();
		this.enderecos = cliente.getEnderecos();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
}
