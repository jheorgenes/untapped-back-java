package com.projetointegrador.untapped.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetointegrador.untapped.domain.Cliente;
import com.projetointegrador.untapped.dtos.ClienteDTO;
import com.projetointegrador.untapped.repositories.ClienteRepository;
import com.projetointegrador.untapped.repositories.EnderecoRepository;
import com.projetointegrador.untapped.services.exceptions.DataIntegratyViolationException;
import com.projetointegrador.untapped.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	
	public Cliente findById(Long id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(
			() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())
		);
	}
	
	public List<Cliente> findAll() {
		return clienteRepository.findAll(); /* Buscando os clientes cadastrados */
	}
	
	public Cliente create(ClienteDTO clienteDTO) {
		clienteDTO.setId(null); /* Validando se o cliente veio com o ID nulo */
		Cliente clienteCadastrado = clienteRepository.save(new Cliente(null, clienteDTO.getNome(), clienteDTO.getEmail(), clienteDTO.getContato(), clienteDTO.getLogin(), clienteDTO.getSenha())); /* Criando um cliente */
		enderecoRepository.saveAll(clienteCadastrado.getEnderecos());
		return clienteCadastrado;
	}
	
	public Cliente update(Long id, ClienteDTO clienteDTO) {
		Cliente oldObj = findById(id);
		updateData(clienteDTO, oldObj);
		return clienteRepository.save(oldObj);
	}
	
	public void delete(Long id) {
		findById(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegratyViolationException e) {
			throw new DataIntegratyViolationException("Não foi possível deletar esse cliente");
		}
	}
	
	private void updateData(ClienteDTO novoClienteDTO, Cliente oldCliente) {
		oldCliente.setNome(novoClienteDTO.getNome());
		oldCliente.setEmail(novoClienteDTO.getEmail());
		oldCliente.setContato(novoClienteDTO.getContato());
		oldCliente.setLogin(novoClienteDTO.getSenha());
	}
}
