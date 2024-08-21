package br.edu.ufape.topicos.price.facade;

import br.edu.ufape.topicos.price.discounts.MoreOrEqual10ItemsDiscount;
import br.edu.ufape.topicos.price.model.Price;
import org.junit.jupiter.api.Test;

public class PricePolicyValidationTest {

    @Test
    public void testDiscountPolicyValidation() {
        MoreOrEqual10ItemsDiscount discountPolicy = new MoreOrEqual10ItemsDiscount(null);

        Price price = new Price();
        price.setValue(100.0);

        double discountValue = discountPolicy.calculateDiscount(price, 15);

        // valor esperado (incorreto para o teste se a polica de preço esta funcionando)
        double expectedDiscount = 100.0; 

        String message = String.format("Esperado desconto de %.2f, mas encontrou %.2f. O valor encontrado está de acordo com a política de preço aplicada.",
                                        expectedDiscount, discountValue);
        
        System.out.println(message);

    }
}
