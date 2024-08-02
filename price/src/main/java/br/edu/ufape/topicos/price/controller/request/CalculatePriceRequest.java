package br.edu.ufape.topicos.price.controller.request;

import jakarta.validation.constraints.NotNull;

public record CalculatePriceRequest(
        @NotNull(message = "The productID is required")
        Long productId,
        @NotNull(message = "The quantity is required")
        int quantity
) {
}
