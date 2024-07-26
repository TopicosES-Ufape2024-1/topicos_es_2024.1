package br.edu.ufape.topicos.price.config;

import br.edu.ufape.topicos.price.service.exceptions.PriceAlreadyExistsException;
import br.edu.ufape.topicos.price.service.exceptions.PriceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.awt.print.PrinterException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(PriceAlreadyExistsException.class)
    protected ResponseEntity<Object> handleProductAlreadyExistsException(PriceAlreadyExistsException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("type", "ProductAlreadyExists");
        response.put("message", ex.getMessage());

        Map<String, Object> error = new HashMap<>();
        error.put("error", response);

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PriceNotFoundException.class)
    protected ResponseEntity<Object> handleProductNotFoundException(PriceNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("type", "ProductNotFound");
        response.put("message", ex.getMessage());

        Map<String, Object> error = new HashMap<>();
        error.put("error", response);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
