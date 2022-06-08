package com.projetointegrador.untapped;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projetointegrador.untapped.domain.Cidade;
import com.projetointegrador.untapped.domain.Cliente;
import com.projetointegrador.untapped.domain.Endereco;
import com.projetointegrador.untapped.domain.Estado;
import com.projetointegrador.untapped.repositories.CidadeRepository;
import com.projetointegrador.untapped.repositories.ClienteRepository;
import com.projetointegrador.untapped.repositories.EnderecoRepository;
import com.projetointegrador.untapped.repositories.EstadoRepository;

@SpringBootApplication
public class UntappedApplication implements CommandLineRunner {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(UntappedApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Estado estado1 = new Estado(null, "Goiás");
		estadoRepository.saveAll(Arrays.asList(estado1));
		
		Cidade cidade1 = new Cidade(null, "Goiânia", estado1);
		cidadeRepository.saveAll(Arrays.asList(cidade1));
		
		Cliente c1 = new Cliente(null, "Jheorgenes Warlley", "jheorgenes@gmail.com", "62 988776655", "jheorgenes", "1234");
		Cliente c2 = new Cliente(null, "Fulando de Tal", "fulano@gmail.com", "62 988776655", "fulano", "4321");
		clienteRepository.saveAll(Arrays.asList(c1, c2));
		
		Endereco endereco1 = new Endereco(null, "Rua C-3", "Quadra 10", "19", "Parque das Laranjeiras", "74000-000", cidade1, c1);
		enderecoRepository.saveAll(Arrays.asList(endereco1));
		
	}

}
