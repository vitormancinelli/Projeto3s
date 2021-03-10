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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import com.projeto.domain.Parceiro;
import com.projeto.service.ParceiroService;

@RestController
public class ParceiroController {
	
	@Autowired
	private ParceiroService parceiroService;
	
	//Busca todos os parceiros cadastrados no banco
	@GetMapping("/parceiro")
	List<Parceiro> findAll() {
		return parceiroService.findAll();
	}
	
	//Busca o parceiro cadastrado no banco com o id recebido
	@GetMapping("/parceiro/{id}")
	Optional<Parceiro> findbyId(@PathVariable Long id) {
		return parceiroService.findById(id);
	}
	
	//Conta quantos parceiros há cadastrados no banco
	@GetMapping("/parceiro/count")
	long count() {
		return parceiroService.count();
	}
	
	//Insere o parceiro recebido no banco
	@PostMapping("/parceiro")
	Parceiro insertParceiro(@RequestBody Parceiro parceiro) {
		return parceiroService.save(parceiro);
	}
	
	//Atualiza um parceiro recebido, caso o parceiro não exista, o parceiro será inserido no banco
	@PutMapping("/parceiro/{id}")
	Optional<Parceiro> updateById(@RequestBody Parceiro parceiro, @PathVariable Long id) {
		return parceiroService.updateById(parceiro, id);
	}
	
	//Deleta o parceiro pelo id recebido
	@DeleteMapping("/parceiro/{id}")
	void deleteParceiro(@PathVariable Long id) {
		parceiroService.deleteById(id);
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
