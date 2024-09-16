package br.edu.ufape.topicos.catalogo.controlador.requisicao;

public record CreateProductInventoryDTO(
        Long productId,
        int quantity,
        Long warehouseId
) {}
