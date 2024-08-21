package br.edu.ufape.topicos.price.discounts;

import br.edu.ufape.topicos.price.model.Price;

public abstract class Discount implements PricePolicy {
    protected Discount next;

    public Discount(Discount next) {
        this.next = next;
    }

    public abstract double calculateDiscount(Price price, int quantity);
}
