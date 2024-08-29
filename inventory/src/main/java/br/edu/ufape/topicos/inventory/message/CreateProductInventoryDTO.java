package br.edu.ufape.topicos.inventory.message;

public record CreateProductInventoryDTO(
        Long productId,
        int quantity,
        Long warehouseId
) {}
