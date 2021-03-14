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

import com.projeto.domain.Ivenda;
import com.projeto.service.IvendaService;

@RestController
public class IvendaController {
	
	@Autowired
	private IvendaService ivendaService;
	
	//Busca todos os itens venda cadastrados no banco
	@GetMapping("/ivenda")
	List<Ivenda> findAll() {
		return ivendaService.findAll();
	}
	
	//Busca o item venda cadastrado com o id recebido
	@GetMapping("/ivenda/{id}")
	Optional<Ivenda> findById(@PathVariable Long id) {
		return ivendaService.findById(id);
	}
	
	//Busca todos os itens venda cadastrados no banco com o item recebido
	@GetMapping("/ivenda/item")
	List<Ivenda> findByItemId(@RequestParam Long id) {
		return ivendaService.findByItem(id);
	}
	
	//Busca todos os itens vendas cadastrados no banco com a venda recebido
	@GetMapping("/ivenda/venda")
	List<Ivenda> findByStatusId(@RequestParam Long id) {
		return ivendaService.findByVenda(id);
	}
	
	//Conta quantos itens venda há cadastrados no banco
	@GetMapping("/ivenda/count")
	long count() {
		return ivendaService.count();
	}
	
	//Insere o item venda recebido no banco
	@PostMapping("/ivenda")
	Ivenda insertIvenda(@RequestBody Ivenda ivenda) {
		return ivendaService.save(ivenda);
	}
	
	//Atualiza o item recebido, caso o item venda não exista, o item venda será inserido no banco
	@PutMapping("/ivenda/{id}")
	Optional<Ivenda> updateById(@RequestBody Ivenda ivenda, @PathVariable Long id) {
		return ivendaService.updateById(ivenda, id);
	}
	
	//Deleta o item venda no banco, com o id recebido
	@DeleteMapping("/ivenda/{id}")
	void deleteById(@PathVariable Long id) {
		ivendaService.deleteById(id);
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
