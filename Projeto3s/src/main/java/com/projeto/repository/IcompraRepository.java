package com.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.domain.Compra;
import com.projeto.domain.Item;
import com.projeto.domain.Icompra;

public interface IcompraRepository extends JpaRepository<Icompra, Long>{

	List<Icompra> findByCompra(Compra compra);
	
	List<Icompra> findByItem(Item item);

}
