package com.projetointegrador.untapped.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetointegrador.untapped.domain.Cidade;
import com.projetointegrador.untapped.domain.Endereco;
import com.projetointegrador.untapped.domain.Pessoa;
import com.projetointegrador.untapped.dtos.EnderecoDTO;
import com.projetointegrador.untapped.repositories.CidadeRepository;
import com.projetointegrador.untapped.repositories.EnderecoRepository;
import com.projetointegrador.untapped.repositories.PessoaRepository;
import com.projetointegrador.untapped.services.exceptions.DataIntegratyViolationException;
import com.projetointegrador.untapped.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Endereco findById(Long id) {
		Optional<Endereco> obj = enderecoRepository.findById(id);
		return obj.orElseThrow(
			() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName())
		);
	}
	
	public List<Endereco> findAll() {
		return enderecoRepository.findAll();
	}
	
	public Endereco create(EnderecoDTO objDTO) {
		Cidade cidade = null;
		Pessoa pessoa = null;
		Optional<Cidade> cidadeOptional = cidadeRepository.findById(objDTO.getCidadeId());
		if(cidadeOptional.isPresent()) {
			cidade = cidadeOptional.get();
		}
		
		Optional<Pessoa> pessoaOptional = pessoaRepository.findById(objDTO.getPessoaId());
		if(pessoaOptional.isPresent()) {
			pessoa = pessoaOptional.get();
		}
		return enderecoRepository.save(new Endereco(null, objDTO.getLougradouro(), objDTO.getComplemento(), objDTO.getNumero(), objDTO.getBairro(), objDTO.getCep(), cidade, pessoa));
	}
	
	public Endereco update(Long id, EnderecoDTO newEnderecoDTO) {
		Endereco oldEndereco = findById(id);
		updateData(newEnderecoDTO, oldEndereco);
		return enderecoRepository.save(oldEndereco);
	}
	
	public void delete(Long id) {
		findById(id);
		try {
			enderecoRepository.deleteById(id);
		} catch (DataIntegratyViolationException e) {
			throw new DataIntegratyViolationException("Não foi possível deletar esse endereço");
		}
	}
	
	private void updateData(EnderecoDTO newEnderecoDTO, Endereco oldEndereco) {
		oldEndereco.setLougradouro(newEnderecoDTO.getLougradouro());
		oldEndereco.setComplemento(newEnderecoDTO.getComplemento());
		oldEndereco.setBairro(newEnderecoDTO.getBairro());
		oldEndereco.setNumero(newEnderecoDTO.getNumero());
	}
}
