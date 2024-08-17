package br.edu.ufape.topicos.inventory.service.exceptions;

public class InventoryAlreadyExistsException extends RuntimeException {
    public InventoryAlreadyExistsException(String message){
        super(message);
    }
}
