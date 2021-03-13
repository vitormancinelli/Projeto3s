package com.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projeto.domain.Parceiro;
import com.projeto.domain.Compra;
import com.projeto.repository.CompraRepository;

@Service
public final class CompraService {
	
	@Autowired
	private CompraRepository repository;
	
	@Autowired
	private ParceiroService parceiroService;
	
	public List<Compra> findAll() {
		return repository.findAll();
	}
	
	public Optional<Compra> findById(Long id) {
		validateIdNotNull(id);
		return repository.findById(id);
	}
	
	public List<Compra> findByIdParceiro(Long id) {
		validateIdNotNull(id);
		Optional<Parceiro> parceiro = parceiroService.findById(id);
		return repository.findByParceiro(parceiro);
	}
	
	public long count() {
		return repository.count();
	}
	
	public Compra save(Compra compra) {
		validate(compra);
		if(compra.getId() != null) {
			compra.setId(null);
		}
		return repository.save(compra);
	}
	
	public Optional<Compra> updateById(Compra compra, Long id) {
		validateIdNotNull(id);
		if(repository.existsById(id)) {
			compra.setId(id);
			validate(compra);
			return Optional.of(repository.save(compra));
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Compra não encontrada");
		}
	}
	
	public void deleteById(Long id) {
		validateIdNotNull(id);
		repository.deleteById(id);
	}

	private void validateIdNotNull(Long id) {
		if(id == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Id inválido.");
	}
	
	private void validate(Compra compra) {
		if(compra.getParceiro() == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Parceiro inválido");
		if(compra.getPeriodo() == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data inválida");
	}
	
}
