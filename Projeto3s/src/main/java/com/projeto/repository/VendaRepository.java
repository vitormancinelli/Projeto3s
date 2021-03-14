package com.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.domain.Venda;
import com.projeto.domain.MeioPagamento;
import com.projeto.domain.Parceiro;
import com.projeto.domain.Status;

public interface VendaRepository extends JpaRepository<Venda, Long> {

	List<Venda> findByParceiro(Parceiro parceiro);
	
	List<Venda> findByStatus(Status status);
	
	List<Venda> findByPagamento(MeioPagamento meioPagamento);
}
