package br.edu.ufape.topicos.catalogo.config;

import br.edu.ufape.topicos.catalogo.cadastro.ServicoDePrecoIndisponivelException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import br.edu.ufape.topicos.catalogo.cadastro.ObjetoNaoEncontradoException;
import br.edu.ufape.topicos.catalogo.cadastro.RegistroDuplicadoException;

@ControllerAdvice
public class TratadorDeExcecaoCustomizada {
	
	@ExceptionHandler(RegistroDuplicadoException.class)
	protected ResponseEntity<Object> tratarExcecaoRegistroDuplicado(RegistroDuplicadoException ex) {
		Map<String, String> resposta = new HashMap<String, String>();
		resposta.put("tipo", "RegistroDuplicado");
		resposta.put("mensagem", ex.getMessage());
		
		Map<String, Object> erro = new HashMap<>();
		erro.put("erro", resposta);
		
		return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	protected ResponseEntity<Object> tratarExcecaoObjetoNaoEncontrado(ObjetoNaoEncontradoException ex) {
		Map<String, String> resposta = new HashMap<String, String>();
		resposta.put("tipo", "RecursoNaoEncontrado");
		resposta.put("mensagem", ex.getMessage());
		
		Map<String, Object> erro = new HashMap<>();
		erro.put("erro", resposta);
		
		return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ServicoDePrecoIndisponivelException.class)
	protected ResponseEntity<Object> tratarExcecaoServicoDePrecoIndisponivel(ServicoDePrecoIndisponivelException ex) {
		Map<String, String> resposta = new HashMap<String, String>();
		resposta.put("tipo", "PrecoIndisponivel");
		resposta.put("mensagem", ex.getMessage());

		Map<String, Object> erro = new HashMap<>();
		erro.put("erro", resposta);

		return new ResponseEntity<>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
