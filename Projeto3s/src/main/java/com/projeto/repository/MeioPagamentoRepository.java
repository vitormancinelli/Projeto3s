package com.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.domain.MeioPagamento;

public interface MeioPagamentoRepository extends JpaRepository<MeioPagamento, Long> {

	List<MeioPagamento> findByDescricao(String descricao);
}
