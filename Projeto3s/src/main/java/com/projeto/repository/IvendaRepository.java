package com.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.domain.Ivenda;
import com.projeto.domain.Item;
import com.projeto.domain.Venda;

public interface IvendaRepository extends JpaRepository<Ivenda, Long>{

	List<Ivenda> findByItem(Item item);
	
	List<Ivenda> findByVenda(Venda venda);
	
}
