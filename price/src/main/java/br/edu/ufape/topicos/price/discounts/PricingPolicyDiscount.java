package br.edu.ufape.topicos.price.discounts;

import br.edu.ufape.topicos.price.model.Price;

public class PricingPolicyDiscount implements Discount {

    private double discountPercentage;

    public PricingPolicyDiscount(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double applyDiscount(Price price) {
        return price.getAmount() * (1 - discountPercentage / 100);
    }
}
