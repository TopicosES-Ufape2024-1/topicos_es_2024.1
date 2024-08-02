package br.edu.ufape.topicos.price.discounts;

import br.edu.ufape.topicos.price.model.Price;

// This class is responsible for calculating the discount based on the total value of the purchase
public class MoreOrEqual500ValueDiscount extends Discount{

    public MoreOrEqual500ValueDiscount(Discount next) {
        super(next);
    }

    @Override
    public double calculateDiscount(Price price, int quantity) {
        if (price.getValue() * quantity >= 500) {
            return price.getValue() * 0.05 * quantity;
        }
        if (next != null) {
            return next.calculateDiscount(price, quantity);
        }
        return 0;
    }
}
