package br.edu.ufape.topicos.price.discounts;

import br.edu.ufape.topicos.price.model.Price;

public class MoreOrEqual500ValueDiscount extends Discount {

    public MoreOrEqual500ValueDiscount(Discount next) {
        super(next);
    }

    @Override
    public double calculateDiscount(Price price, int quantity) {
        // Ajuste na condição para aplicar desconto apenas se o valor exceder 500
        if (price.getValue() * quantity > 500) {
            return price.getValue() * 0.05 * quantity; // 5% de desconto
        }
        if (next != null) {
            return next.calculateDiscount(price, quantity);
        }
        return 0;
    }
}
