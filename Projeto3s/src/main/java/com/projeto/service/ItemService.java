package com.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projeto.domain.Item;
import com.projeto.repository.ItemRepository;

@Service
public final class ItemService {

		@Autowired
		private ItemRepository repository;
		
		public List<Item> findAll() {
			return repository.findAll();
		}
		
		public Optional<Item> findById(Long id) {
			validateIdNotNull(id);
			return repository.findById(id);
		}
		
		public List<Item> findByNome(String nome) {
			validateNomeNotNull(nome);
			return repository.findByNome(nome);
		}
		
		public Long count() {
			return repository.count();
		}
		
		public Item save(Item item) {
			validate(item);
			if(item.getId() != null) {
				item.setId(null);
			}
			return repository.save(item);
		}
		
		public Optional<Item> updateById(Item item, Long id) {
			validateIdNotNull(id);
			if(repository.existsById(id)) {
				item.setId(id);
				validate(item);
				return Optional.of(repository.save(item));
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Item não encontrado.");
			}
		}
		
		public void deleteById(Long id) {
			validateIdNotNull(id);
			repository.deleteById(id);
		}
		
		private void validateIdNotNull(Long id) {
			if(id == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Id inválido.");
		}
		
		private void validateNomeNotNull(String nome) {
			if(nome == null || nome.length() > 60) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Nome inválido.");
		}
		
		private void validate(Item item) {
			if(item.getNome() == null || item.getNome().length() > 60) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Nome do item inválido.");
			if(item.getDescricao() == null || item.getDescricao().length() > 60) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Descrição do item inválido.");
			if(item.getValor() == null || item.getValor() < 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Valor do item inválido.");
			if(item.getEstoque() < 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Estoque do item inválido.");
		}
}
