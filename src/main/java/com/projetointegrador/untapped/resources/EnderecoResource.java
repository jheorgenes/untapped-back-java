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

import com.projetointegrador.untapped.domain.Endereco;
import com.projetointegrador.untapped.dtos.EnderecoDTO;
import com.projetointegrador.untapped.services.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoResource {

	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Endereco> busca(@PathVariable Long id){
		Endereco endereco = enderecoService.findById(id);
		return ResponseEntity.ok().body(endereco);
	}
	
	@GetMapping
	public ResponseEntity<List<EnderecoDTO>> buscaTodos(){
		List<EnderecoDTO> listDTO = enderecoService.findAll().stream().map(obj -> new EnderecoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<EnderecoDTO> insere(@Valid @RequestBody EnderecoDTO enderecoDTO){
		Endereco newObj = enderecoService.create(enderecoDTO);
		URI uri = ServletUriComponentsBuilder /* Chamando a classe para montar a URL completa */
							.fromCurrentRequest() /* buscando o caminho completo da requisição */
							.path("/{id}") /* Definindo que "{id}" é uma variável */
							.buildAndExpand(newObj.getId()) /* Substituíndo a variável pelo id do objeto */
							.toUri(); /* montando a URL completa com as informações passadas */
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Endereco> atualiza(@PathVariable Long id, @Valid @RequestBody EnderecoDTO enderecoDTO){
		Endereco newObj = enderecoService.update(id, enderecoDTO);
		return ResponseEntity.ok().body(newObj);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		enderecoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
