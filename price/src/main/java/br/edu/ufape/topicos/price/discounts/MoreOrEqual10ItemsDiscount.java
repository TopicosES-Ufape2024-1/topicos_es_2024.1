package br.edu.ufape.topicos.price.discounts;

import br.edu.ufape.topicos.price.model.Price;

public class MoreOrEqual10ItemsDiscount extends Discount{

    public MoreOrEqual10ItemsDiscount(Discount next) {
        super(next);
    }

    @Override
    public double calculateDiscount(Price price, int quantity) {
        if (quantity >= 10) {
            return price.getValue() * 0.1 * quantity; // 10% discount
        }
        if (next != null) {
            return next.calculateDiscount(price, quantity);
        }
        return 0;
    }
}
