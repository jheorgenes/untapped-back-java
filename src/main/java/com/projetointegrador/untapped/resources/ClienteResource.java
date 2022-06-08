package com.projetointegrador.untapped.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projetointegrador.untapped.domain.Cliente;
import com.projetointegrador.untapped.dtos.ClienteDTO;
import com.projetointegrador.untapped.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> busca(@PathVariable Long id){
		Cliente cliente = clienteService.findById(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> buscaTodos(){
		List<ClienteDTO> listDTO = clienteService.findAll().stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> insere(@Valid @RequestBody ClienteDTO clienteDTO){
		Cliente newObj = clienteService.create(clienteDTO);
		URI uri = ServletUriComponentsBuilder
								.fromCurrentRequest()
								.path("/{id}")
								.buildAndExpand(newObj.getId())
								.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualiza(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO){
		Cliente newObj = clienteService.update(id, clienteDTO);
		return ResponseEntity.ok().body(newObj);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleta(@PathVariable Long id){
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
