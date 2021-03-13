package com.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projeto.repository.StatusRepository;
import com.projeto.domain.Status;

@Service
public final class StatusService {

	@Autowired
	private StatusRepository repository;
	
	public List<Status> findAll() {
		return repository.findAll();
	}
	
	public Optional<Status> findById(Long id) {
		validateIdNotNull(id);
		return repository.findById(id);
	}
	
	public long count() {
		return repository.count();
	}
	
	public Status save(Status status) {
		validate(status);
		if(status.getId() != null) {
			status.setId(null);
		}
		return repository.save(status);
	}
	
	public Optional<Status> updateById(Status status, Long id) {
		validateIdNotNull(id);
		if(repository.existsById(id)) {
			status.setId(id);
			validate(status);
			return Optional.of(repository.save(status));
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Status não encontrado");
		}
	}
	
	public void deleteById(Long id) {
		validateIdNotNull(id);
		repository.deleteById(id);
	}
	
	private void validateIdNotNull(Long id) {
		if(id == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Id inválido.");
	}
	
	private void validate(Status status) {
		if(status.getDescricao() == null || status.getDescricao().length() > 60) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Descrição do status inválido");
	}
	
}
