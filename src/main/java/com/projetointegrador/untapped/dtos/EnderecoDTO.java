package com.projetointegrador.untapped.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.projetointegrador.untapped.domain.Endereco;

public class EnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String lougradouro;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String complemento;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String numero;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String bairro;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cep;

	private Long cidadeId;
	
	private Long pessoaId;
	
	public EnderecoDTO() {
		super();
	}
	
	public EnderecoDTO(Endereco obj) {
		super();
		this.id = obj.getId();
		this.lougradouro = obj.getLougradouro();
		this.complemento = obj.getComplemento();
		this.numero = obj.getNumero();
		this.bairro = obj.getBairro();
		this.cep = obj.getCep();
		this.cidadeId = obj.getCidade().getId();
		this.pessoaId = obj.getPessoa().getId();		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLougradouro() {
		return lougradouro;
	}

	public void setLougradouro(String lougradouro) {
		this.lougradouro = lougradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Long getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}
	
	public Long getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Long pessoaId) {
		this.pessoaId = pessoaId;
	}
}
