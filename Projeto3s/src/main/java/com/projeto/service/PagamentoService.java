package com.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projeto.domain.Pagamento;
import com.projeto.domain.MeioPagamento;
import com.projeto.domain.Ivenda;
import com.projeto.repository.PagamentoRepository;

@Service
public final class PagamentoService {
	
	@Autowired
	private IvendaService ivendaService;
	
	@Autowired
	private MeioPagamentoService meioPagamentoService;
	
	@Autowired
	private PagamentoRepository repository;
	
	public List<Pagamento> findAll() {
		return repository.findAll();
	}
	
	public Optional<Pagamento> findById(Long id) {
		validateIdNotNull(id);
		return repository.findById(id);
	}
	
	public List<Pagamento> findByIvenda(Long id) {
		validateIdNotNull(id);
		Optional<Ivenda> tmp = ivendaService.findById(id);
		Ivenda ivenda = tmp.get();
		if(ivenda != null) {
			return repository.findByIvenda(ivenda);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item venda não encontrado");
		}
	}
	
	public List<Pagamento> findByMeioPagamento(Long id) {
		validateIdNotNull(id);
		Optional<MeioPagamento> tmp = meioPagamentoService.findById(id);
		MeioPagamento meioPagamento = tmp.get();
		if(meioPagamento != null) {
			return repository.findByMeioPagamento(meioPagamento);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Meio de pagamento não encontrado");
		}
	}
	
	public long count() {
		return repository.count();
	}
	
	public Pagamento save(Pagamento pagamento) {
		validate(pagamento);
		if(pagamento.getId() != null) {
			pagamento.setId(null);
		}
		return repository.save(pagamento);
	}
	
	public Optional<Pagamento> updateById(Pagamento pagamento, Long id) {
		validateIdNotNull(id);
		if(repository.existsById(id)) {
			pagamento.setId(id);
			validate(pagamento);
			return Optional.of(repository.save(pagamento));
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagamento não encontrado");
		}
	}
	
	public void deleteById(Long id) {
		validateIdNotNull(id);
		repository.deleteById(id);
	}
	
	private void validateIdNotNull(Long id) {
		if(id == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Id inválido.");
	}
	
	private void validate(Pagamento pagamento) {
		if(pagamento.getIvenda() == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item venda inválido");
		if(pagamento.getMeioPagamento() == null)throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Meio de pagamento inválido");
	}

}
