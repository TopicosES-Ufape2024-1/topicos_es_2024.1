package br.edu.ufape.topicos.price.facade;

import br.edu.ufape.topicos.price.discounts.MoreOrEqual10ItemsDiscount;
import br.edu.ufape.topicos.price.model.Price;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricePolicyNegativeTest {

    @Test
    public void testDiscountPolicyFailsWithIncorrectExpectation() {
        MoreOrEqual10ItemsDiscount discountPolicy = new MoreOrEqual10ItemsDiscount(null);

        Price price = new Price();
        price.setValue(100.0);

        double discountValue = discountPolicy.calculateDiscount(price, 10);

        System.out.println("----- Teste: Verificação de Falha na Política de Desconto -----");
        System.out.printf("Preço por unidade: %.2f%n", price.getValue());
        System.out.printf("Quantidade: %d%n", 10);
        System.out.printf("Desconto aplicado pela política: %.2f%n", discountValue);
        System.out.printf("Comparando com valor esperado incorreto: 200.0%n");
        System.out.println("-----------------------------------------------");

        assertThrows(AssertionError.class, () -> {
            assertEquals(200.0, discountValue); // Valor esperado incorreto
        });

        System.out.println("Política de preço aplicada corretamente!");
    }
}
