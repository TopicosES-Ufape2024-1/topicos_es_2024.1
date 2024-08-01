package br.edu.ufape.topicos.inventory.service.exceptions;

public class InventoryAlreadyExistsException extends RuntimeExecption {
    public InventoryAlreadyExistsException(String message){
        super(message);
    }
}
