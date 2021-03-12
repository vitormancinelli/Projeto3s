package com.projeto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.domain.Compra;
import com.projeto.domain.Parceiro;

public interface CompraRepository extends JpaRepository<Compra, Long> {
	
	List<Compra> findByParceiro(Optional<Parceiro> parceiro);

}
