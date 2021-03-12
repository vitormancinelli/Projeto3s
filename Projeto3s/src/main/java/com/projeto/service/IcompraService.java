package com.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projeto.domain.Item;
import com.projeto.domain.Compra;
import com.projeto.domain.Icompra;
import com.projeto.repository.IcompraRepository;

@Service
public final class IcompraService {
	
	@Autowired
	private IcompraRepository repository;
	
	@Autowired
	private CompraService compraService;
	
	@Autowired
	private ItemService itemService;
	
	public List<Icompra> findAll() {
		return repository.findAll();
	}
	
	public Optional<Icompra> findById(Long id) {
		validateIdNotNull(id);
		return repository.findById(id);
	}
	
	public List<Icompra> findByIdCompra(Long id) {
		validateIdNotNull(id);
		Optional<Compra> compra = compraService.findById(id);
		return repository.findByCompra(compra);
	}
	
	public List<Icompra> findByIdItem(Long id) {
		validateIdNotNull(id);
		Optional<Item> item = itemService.findById(id);
		return repository.findByItem(item);
	}
	
	public long count() {
		return repository.count();
	}
	
	public Icompra save(Icompra icompra) {
		validate(icompra);
		if(icompra.getId() != null) {
			icompra.setId(null);
		}
		return repository.save(icompra);
	}
	
	public Optional<Icompra> updateById(Icompra icompra, Long id) {
		if(repository.existsById(id)) {
			icompra.setId(id);
			validate(icompra);
			return Optional.of(repository.save(icompra));
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Icompra não encontrada");
		}
	}
	
	public void deleteById(Long id) {
		validateIdNotNull(id);
		repository.deleteById(id);
	}
	
	private void validateIdNotNull(Long id) {
		if(id == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Id inválido.");
	}
	
	private void validate(Icompra icompra) {
		if(icompra.getCompra() == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Compra do item compra inválido.");
		if(icompra.getItem() == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Item do item compra inválido.");
		if(icompra.getQuantidade() <= 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Quantidade do item compra inválido.");
		if(icompra.getValor() == null || icompra.getValor() <= 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Valor do item compra inválido.");
	}

}
