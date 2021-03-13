package com.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projeto.domain.MeioPagamento;
import com.projeto.repository.MeioPagamentoRepository;

@Service
public class MeioPagamentoService {

	@Autowired
	private MeioPagamentoRepository repository;
	
	public List<MeioPagamento> findAll() {
		return repository.findAll();
	}
	
	public Optional<MeioPagamento> findById(Long id) {
		validateIdNotNull(id);
		return repository.findById(id);
	}
	
	public List<MeioPagamento> findByDescricao(String descricao) {
		return repository.findByDescricao(descricao);
	}
	
	public long count() {
		return repository.count();
	}
	
	public MeioPagamento save(MeioPagamento meioPagamento) {
		validate(meioPagamento);
		if(meioPagamento.getId() != null) {
			meioPagamento.setId(null);
		}
		return repository.save(meioPagamento);
	}
	
	public Optional<MeioPagamento> updateById(MeioPagamento meioPagamento, Long id) {
		validateIdNotNull(id);
		if(repository.existsById(id)) {
			meioPagamento.setId(id);
			validate(meioPagamento);
			return Optional.of(repository.save(meioPagamento));
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Meio de pagamento não encontrado.");
		}
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	private void validateIdNotNull(Long id) {
		if(id == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Id inválido.");
	}
	
	private void validate(MeioPagamento meioPagamento) {
		if(meioPagamento == null || meioPagamento.getDescricao().length() > 60) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Nome inválido.");
	}
	
}
