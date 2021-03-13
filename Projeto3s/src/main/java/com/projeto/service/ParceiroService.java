package com.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projeto.domain.Parceiro;
import com.projeto.repository.ParceiroRepository;

@Service
public final class ParceiroService {

	@Autowired
	private ParceiroRepository repository;
	
	public List<Parceiro> findAll() {
		return repository.findAll();
	}
	
	public Optional<Parceiro> findById(Long id) {
		validateIdNotNull(id);
		return repository.findById(id);
	}
	
	public List<Parceiro> findByNome(String nome) {
		validateNomeNotNull(nome);
		return repository.findByNome(nome);
	}
	
	public List<Parceiro> findByDocumento(String documento) {
		validateDocumentoNotNull(documento);
		return repository.findByDocumento(documento);
	}
	
	public long count() {
		return repository.count();
	}
	
	public Parceiro save(Parceiro parceiro) {
		validate(parceiro);
		if(parceiro.getId() != null) {
			parceiro.setId(null);
		}
		return repository.save(parceiro);
	}
	
	public Optional<Parceiro> updateById(Parceiro parceiro, Long id) {
		validateIdNotNull(id);
		if(repository.existsById(id)) {
			parceiro.setId(id);
			validate(parceiro);
			return Optional.of(repository.save(parceiro));
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Parceiro não encontrado.");
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
	
	private void validateDocumentoNotNull(String documento) {
		if(documento == null || documento.length() > 14) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Documento inválido.");
	}
	
	private void validate(Parceiro parceiro) {
		if(parceiro.getNome() == null || parceiro.getNome().length() > 60) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Nome do parceiro inválido.");
		if(parceiro.getDocumento() == null || parceiro.getDocumento().length() > 14) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Documento do parceiro inválido.");
		if(parceiro.getEndereco() == null || parceiro.getEndereco().length() > 60) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Endereço do parceiro inválido.");
		if(parceiro.getEmail() == null || parceiro.getEmail().length() > 100) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email do parceiro inválido.");
	}
	
}
