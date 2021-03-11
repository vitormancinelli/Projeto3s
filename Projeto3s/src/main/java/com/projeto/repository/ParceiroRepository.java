package com.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.domain.Parceiro;

public interface ParceiroRepository extends JpaRepository<Parceiro, Long> {

	List<Parceiro> findByNome(String nome);
	
	List<Parceiro> findByDocumento(String documento);
}
