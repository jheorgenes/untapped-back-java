package com.projetointegrador.untapped.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetointegrador.untapped.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
	
}
