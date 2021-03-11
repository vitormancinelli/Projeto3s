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

import com.projeto.domain.Item;
import com.projeto.service.ItemService;

@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	//Busca todos os itens cadastrados no banco
	@GetMapping("/item")
	List<Item> findAll() {
		return itemService.findAll();
	}
	
	//Busca o item cadastrado no banco com o id recebido
	@GetMapping("/item/{id}")
	Optional<Item> findById(@PathVariable Long id) {
		return itemService.findById(id);
	}
	
	//Busca todos os itens que o nome seja igual ao recebido
	@GetMapping("/item/nome")
	List<Item> findByNome(@RequestParam String nome) {
		return itemService.findByNome(nome);
	}
	
	//Conta quantos itens há cadastrados no banco
	@GetMapping("/item/count")
	Long count() {
		return itemService.count();	
	}
	
	//Insere o item recebido no banco
	@PostMapping("/item")
	Item insertItem(@RequestBody Item item) {
		return itemService.save(item);
	}
	
	//Atualiza um item recebido, caso o item não exista, o item será inserido no banco
	@PutMapping("/item/{id}")
	Optional<Item> updateItem(@RequestBody Item item, @PathVariable Long id) {
		return itemService.updateById(item, id);
	}
	
	//Deleta o item pelo id recebido
	@DeleteMapping("/item/{id}")
	void deleteItem(@PathVariable Long id) {
		itemService.deleteById(id);
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
