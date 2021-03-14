package com.projeto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import com.projeto.domain.Venda;
import com.projeto.service.VendaService;

@RestController
public class VendaController {

	@Autowired
	private VendaService vendaService;
	
	//Busca todas as vendas cadastradas no banco
	@GetMapping("/venda")
	List<Venda> findAll() {
		return vendaService.findAll();
	}
	
	//Busca a venda cadastrada na banco com o id recebido
	@GetMapping("/venda/{id}")
	Optional<Venda> findById(@PathVariable Long id) {
		return vendaService.findById(id);
	}
	
	//Busca todas as vendas que contém o parceiro recebido
	@GetMapping("/venda/parceiro")
	List<Venda> findByParceiro(@RequestParam Long id) {
		return vendaService.findByParceiro(id);
	}
	
	//Busca todas as vendas que contém o status recebido
	@GetMapping("/venda/status")
	List<Venda> findByStatus(@RequestParam Long id) {
		return vendaService.findByStatus(id);
	}
	
	//Busca todas as vendas que contém o pagamento recebido
	@GetMapping("/venda/pagamento")
	List<Venda> findByPagamento(@RequestParam Long id) {
		return vendaService.findByPagamento(id);
	}
	
	//Conta quantas vendas há cadastradas no banco
	@GetMapping("/venda/count")
	long count() {
		return vendaService.count();
	}
	
	//Insere a venda recebida na banco
	@PostMapping("/venda")
	Venda insertVenda(@RequestBody Venda venda) {
		return vendaService.save(venda);
	}
	
	//Atualiza a venda recebida, caso a venda não exista, a venda será inserida no banco
	@PutMapping("/venda/{id}")
	Optional<Venda> updateVendaById(@RequestBody Venda venda, @PathVariable Long id) {
		return vendaService.updateById(venda, id);
	}
	
	//Deleta a venda do banco, com o id recebido
	@DeleteMapping("/venda/{id}")
	void deleteById(@PathVariable Long id) {
		vendaService.deleteById(id);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
	
}
