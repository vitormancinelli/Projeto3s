package com.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projeto.domain.Status;
import com.projeto.domain.MeioPagamento;
import com.projeto.domain.Parceiro;
import com.projeto.domain.Venda;
import com.projeto.repository.VendaRepository;

@Service
public final class VendaService {

	@Autowired
	private StatusService statusService;
	
	@Autowired
	private ParceiroService parceiroService;
	
	@Autowired
	private MeioPagamentoService meioPagamentoService;
	
	@Autowired
	private VendaRepository repository;
	
	public List<Venda> findAll() {
		return repository.findAll();
	}
	
	public Optional<Venda> findById(Long id) {
		validateIdNotNull(id);
		return repository.findById(id);
	}
	
	public List<Venda> findByParceiro(Long id) {
		validateIdNotNull(id);
		Optional<Parceiro> tmp = parceiroService.findById(id);
		Parceiro parceiro = tmp.get();
		if(parceiro != null) {
			return repository.findByParceiro(parceiro);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Parceiro não encontrado");
		}
	}
	
	public List<Venda> findByStatus(Long id) {
		validateIdNotNull(id);
		Optional<Status> tmp = statusService.findById(id);
		Status status = tmp.get();
		if(status != null) {
			return repository.findByStatus(status);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Status não encontrado");
		}
		
	}
	
	public List<Venda> findByPagamento(Long id) {
		validateIdNotNull(id);
		Optional<MeioPagamento> tmp = meioPagamentoService.findById(id);
		MeioPagamento pagamento = tmp.get();
		if(pagamento != null) {
			return repository.findByPagamento(pagamento);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagamento não encontrado");
		}
	}
	
	public long count() {
		return repository.count();
	}
	
	public Venda save(Venda venda) {
		validate(venda);
		if(venda.getId() != null) {
			venda.setId(null);
		}
		return repository.save(venda);
	}
	
	public Optional<Venda> updateById(Venda venda, Long id) {
		validateIdNotNull(id);
		if(repository.existsById(id)) {
			venda.setId(id);
			validate(venda);
			
			Optional<Venda> tmp = repository.findById(id);
			Venda vendaAnterior = tmp.get();
			
			if(vendaAnterior.getStatus().getId() == 8) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Venda já foi cancelado, não pode ser alterada");
			}
			return Optional.of(repository.save(venda));
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Venda não encontrada");
		}
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	private void validateIdNotNull(Long id) {
		if(id == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Id inválido.");
	}
	
	private void validate(Venda venda) {
		if(venda.getEndereco() == null || venda.getEndereco().length() > 60) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço da venda inválido");
		if(venda.getParceiro() == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Parceiro da venda inválido");
		if(venda.getPeriodo() == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data da venda inválida");
		if(venda.getStatus() == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Status da venda inválida");
	}
}
