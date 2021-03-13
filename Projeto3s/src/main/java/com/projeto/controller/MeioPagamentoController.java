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

import com.projeto.domain.MeioPagamento;
import com.projeto.service.MeioPagamentoService;

@RestController
public class MeioPagamentoController {
	
	@Autowired
	private MeioPagamentoService meioPagamentoService;
	
	//Busca todos os meios de pagamento cadastrados no banco
	@GetMapping("/meioPagamento")
	List<MeioPagamento> findAll() {
		return meioPagamentoService.findAll();
	}
	
	//Busca o meio de pagamento cadastrado no banco com o id recebido
	@GetMapping("/meioPagamento/{id}")
	Optional<MeioPagamento> findById(@PathVariable Long id) {
		return meioPagamentoService.findById(id);
	}
	
	//Busca todos os meios de pagamento cadastrados no banco com a descricao recebida
	@GetMapping("/meioPagamento/descricao")
	List<MeioPagamento> findByDescricao(@RequestParam String descricao) {
		return meioPagamentoService.findByDescricao(descricao);
	}

	//Conta quantos meios de pagamentos há cadastrados no banco
	@GetMapping("/meioPagamento/count")
	long count() {
		return meioPagamentoService.count();
	}
	
	//Insere o meio de pagamento recebido no banco
	@PostMapping("/meioPagamento")
	MeioPagamento insertMeioPagamento(@RequestBody MeioPagamento meioPagamento) {
		return meioPagamentoService.save(meioPagamento);
	}
	
	//Atualiza um meio de pagamento recebido, caso o meio de pagamento não exista, ele será inserido no banco
	@PutMapping("/meioPagamento/{id}")
	Optional<MeioPagamento> updateById(@RequestBody MeioPagamento meioPagamento, @PathVariable Long id) {
		return meioPagamentoService.updateById(meioPagamento, id);
	}
	
	//Deleta o meio de pagamento do banco, com o id recebido
	@DeleteMapping("/meioPagamento/{id}")
	void deleteById(@PathVariable Long id) {
		meioPagamentoService.deleteById(id);
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
