package br.edu.ufape.topicos.price.discounts;

import br.edu.ufape.topicos.price.model.Price;

public interface PricePolicy {
    double calculateDiscount(Price price, int quantity);
}
