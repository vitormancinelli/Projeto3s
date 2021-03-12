package com.projeto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.domain.Compra;
import com.projeto.domain.Item;
import com.projeto.domain.Icompra;

public interface IcompraRepository extends JpaRepository<Icompra, Long>{

	List<Icompra> findByCompra(Optional<Compra> compra);
	
	List<Icompra> findByItem(Optional<Item> item);

}
