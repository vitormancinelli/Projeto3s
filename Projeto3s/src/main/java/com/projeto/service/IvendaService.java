package com.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projeto.domain.Ivenda;
import com.projeto.domain.Item;
import com.projeto.domain.Venda;
import com.projeto.repository.IvendaRepository;

@Service
public final class IvendaService {
	
	@Autowired
	private VendaService vendaService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private IvendaRepository repository;
	
	public List<Ivenda> findAll() {
		return repository.findAll();
	}
	
	public Optional<Ivenda> findById(Long id) {
		validateIdNotNull(id);
		return repository.findById(id);
	}
	
	public List<Ivenda> findByVenda(Long id) {
		validateIdNotNull(id);
		Optional<Venda> tmp = vendaService.findById(id);
		Venda venda = tmp.get();
		if(venda != null) {
			return repository.findByVenda(venda);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Venda não encontrado");
		}
	}
	
	public List<Ivenda> findByItem(Long id) {
		validateIdNotNull(id);
		Optional<Item> tmp = itemService.findById(id);
		Item item = tmp.get();
		if(item != null) {
			return repository.findByItem(item);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado");
		}
	}
	
	public long count() {
		return repository.count();
	}
	
	public Ivenda save(Ivenda ivenda) {
		validate(ivenda);
		if(ivenda.getId() != null) {
			ivenda.setId(null);
		}
		
		if(checkStatusVenda(ivenda)) {
			Optional<Item> tmp = itemService.findById(ivenda.getItem().getId());
			Item item = tmp.get();
			if(item != null) {
				item.setEstoque(item.getEstoque() - ivenda.getQuantidade());
				if(item.getEstoque() < 0) {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Venda desse item não permitida, item do estoque insuficente");
				} else {
					itemService.updateById(item, item.getId());	
				}
			}
		}
		return repository.save(ivenda);
	}
	
	public Optional<Ivenda> updateById(Ivenda ivenda, Long id) {
		validateIdNotNull(id);
		if(repository.existsById(id)) {
			ivenda.setId(id);
			validate(ivenda);
			
			if(checkStatusVenda(ivenda)) {
				Optional<Ivenda> ivendaTmp = repository.findById(id);
				Ivenda ivendaAnterior = ivendaTmp.get();
				
				Optional<Item> itemTmp = itemService.findById(id);
				Item item = itemTmp.get();
				if(item != null) {
					item.setEstoque(item.getEstoque() + ivendaAnterior.getQuantidade() - ivenda.getQuantidade());
					if(item.getEstoque() < 0) {
						throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Venda desse item não permitida, item do estoque insuficente");
					} else {
						itemService.updateById(item, item.getId());	
					}
				}
			}
			return Optional.of(repository.save(ivenda));
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ivenda não encontrada");
		}
	}
	
	public void deleteById(Long id) {
		validateIdNotNull(id);
		repository.deleteById(id);
	}
	
	//True precisa atualizar o estoque do item
	//False não precisa atualizar o estoque do item
	private boolean checkStatusVenda(Ivenda ivenda) {
		if(ivenda.getVenda().getStatus() == null) {
			//Estoque do item não foi atualizado
			return true;
		} else {
			if(ivenda.getVenda().getStatus().getId() > 1 && ivenda.getVenda().getStatus().getId() != 8) {
				Optional<Venda> tmp = vendaService.findById(ivenda.getVenda().getId());
				Venda vendaAnterior = tmp.get();
				if(vendaAnterior.getStatus().getId() > 1 && vendaAnterior.getStatus().getId() != 8) {
					//Estoque do item já foi atualizado
					return false;
				} else {
					//Estoque do item não foi atualizado
					return true;
				}
			} else {
				//Estoque do item já foi atualizado
				return false;
			}
		}
	}
	
	private void validateIdNotNull(Long id) {
		if(id == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Id inválido.");
	}
	
	private void validate(Ivenda ivenda) {
		if(ivenda.getQuantidade() < 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Quantidade do item venda inválido");
		if(ivenda.getItem() == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Item do item venda inválido");
		if(ivenda.getValor() == null || ivenda.getValor() < 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Valor do item venda inválido");
		if(ivenda.getVenda() == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Venda do item venda inválido");
	}
	
}
