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

import com.projeto.domain.Status;
import com.projeto.service.StatusService;

@RestController
public class StatusController {

	@Autowired
	private StatusService statusService;
	
	//Busca todos os status cadastrados no banco
	@GetMapping("/status")
	List<Status> findAll() {
		return statusService.findAll();
	}
	
	//Busca o status cadastrado no banco, com o id recebido
	@GetMapping("/status/{id}")
	Optional<Status> findById(@PathVariable Long id) {
		return statusService.findById(id);
	}
	
	//Conta quantos status há cadastrados no banco
	@GetMapping("/status/count")
	long count() {
		return statusService.count();
	}
	
	//Insere um status recebido no banco
	@PostMapping("/status")
	Status insertStatus(@RequestBody Status status) {
		return statusService.save(status);
	}
	
	//Atualiza um status recebido, caso o status não exista, o status será inserido no banco
	@PutMapping("/status/{id}")
	Optional<Status> updateStatus(@RequestBody Status status, @PathVariable Long id) {
		return statusService.updateById(status, id);
	}
	
	//Deleta o status pelo id recebido
	@DeleteMapping("/status/{id}")
	void deleteStatus(@PathVariable Long id) {
		statusService.deleteById(id);
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
