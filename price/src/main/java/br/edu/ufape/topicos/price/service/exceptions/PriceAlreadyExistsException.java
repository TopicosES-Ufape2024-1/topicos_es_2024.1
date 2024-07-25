package br.edu.ufape.topicos.price.service.exceptions;

public class PriceAlreadyExistsException extends RuntimeException {

    public PriceAlreadyExistsException(String message) {
        super(message);
    }
}
