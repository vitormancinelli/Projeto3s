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

import com.projeto.domain.Icompra;
import com.projeto.service.IcompraService;

@RestController
public class IcompraController {

	@Autowired
	private IcompraService icompraService;
	
	@GetMapping("/icompra")
	List<Icompra> findAll() {
		return icompraService.findAll();
	}
	
	@GetMapping("/icompra/{id}")
	Optional<Icompra> findById(@PathVariable Long id) {
		return icompraService.findById(id);
	}
	
	@GetMapping("/icompra/compraId")
	List<Icompra> findByCompraId(@RequestParam Long id) {
		return icompraService.findByIdCompra(id);
	}
	
	@GetMapping("/icompra/itemId")
	List<Icompra> findByItemId(@RequestParam Long id) {
		return icompraService.findByIdItem(id);
	}
	
	@GetMapping("/icompra/count")
	long count() {
		return icompraService.count();
	}
	
	@PostMapping("/icompra")
	Icompra insertIcompra(@RequestBody Icompra icompra) {
		return icompraService.save(icompra);
	}
	
	@PutMapping("/icompra/{id}")
	Optional<Icompra> updateById(@RequestBody Icompra icompra, @PathVariable Long id) {
		return icompraService.updateById(icompra, id);
	}
	
	@DeleteMapping("/icompra/{id}")
	void deleteIcompra(@PathVariable Long id) {
		icompraService.deleteById(id);
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
