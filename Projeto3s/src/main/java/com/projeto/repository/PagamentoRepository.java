package com.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.domain.Ivenda;
import com.projeto.domain.MeioPagamento;
import com.projeto.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{
	
	List<Pagamento> findByMeioPagamento(MeioPagamento meioPagamento);
	
	List<Pagamento> findByIvenda(Ivenda ivenda);
	
}
