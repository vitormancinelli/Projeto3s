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

import com.projeto.domain.Compra;
import com.projeto.service.CompraService;

@RestController
public class CompraController {

	@Autowired
	private CompraService compraService;
	
	//Busca todas as compras cadastrados no banco
	@GetMapping("/compra")
	List<Compra> findAll() {
		return compraService.findAll();
	}
	
	//Busca a compra cadastrado no banco com o id recebido
	@GetMapping("/compra/{id}") 
	Optional<Compra> findById(@PathVariable Long id) {
		return compraService.findById(id);
	}
	
	//Busca todas as compras que contém o parceiro recebido
	@GetMapping("/compra/id")
	List<Compra> findByIdParceiro(@RequestParam Long id) {
		return compraService.findByIdParceiro(id);
	}
	
	//Conta quantas compras há cadastrados no banco
	@GetMapping("/compra/count")
	long count() {
		return compraService.count();
	}
	
	//Insere a compra recebida no banco
	@PostMapping("/compra")
	Compra insertCompra(@RequestBody Compra compra) {
		return compraService.save(compra);
	}
	
	//Atualiza uma compra recebida, caso a compra não exista, a compra será inserida no banco
	@PutMapping("/compra/{id}")
	Optional<Compra> updateCompraById(@RequestBody Compra compra, @PathVariable Long id) {
		return compraService.updateById(compra, id);
	}
	
	//Deleta a compra do banco, com o id recebido
	@DeleteMapping("/compra/{id}")
	void deleteById(@PathVariable Long id) {
		compraService.deleteById(id);
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
