package br.edu.ufape.topicos.price.controller.response;

public record CalculatePriceResponse(
        double totalWithDiscount,
        double totalWithoutDiscount,
        double discount
) {
}
