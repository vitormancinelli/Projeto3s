package com.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

	List<Item> findByNome(String nome);
}
