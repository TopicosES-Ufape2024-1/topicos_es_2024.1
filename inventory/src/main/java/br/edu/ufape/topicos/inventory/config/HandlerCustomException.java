package br.edu.ufape.topicos.price.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import br.edu.ufage.topicos.catalogo.cadastro.ObjetoNaoEncontradoException;
import br.edu.ufage.topicos.catalogo.cadastro.RegistroDuplicadoException;

@ControllerAdvice
public class HandlerCustomException {

}
