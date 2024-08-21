package br.edu.ufape.topicos.price.discounts;

import br.edu.ufape.topicos.price.model.Price;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoreOrEqual10ItemsDiscountTest {

    @Test
    public void testDiscountAppliedForMoreOrEqual10Items() {
        MoreOrEqual10ItemsDiscount discount = new MoreOrEqual10ItemsDiscount(null);
        Price price = new Price();
        price.setValue(100.0);

        // Aplica o desconto
        double discountValue = discount.calculateDiscount(price, 10); // 10 itens

        System.out.println("----- Teste: Desconto para 10 ou mais itens -----");
        System.out.printf("Preço por unidade: %.2f%n", price.getValue());
        System.out.printf("Quantidade: %d%n", 10);
        System.out.printf("Desconto esperado: %.2f%n", 100.0);
        System.out.printf("Desconto aplicado: %.2f%n", discountValue);
        System.out.println("-----------------------------------------------");

        // verificando se o desconto aplicado é o esperado (10% de 1000 = 100)
        assertEquals(100.0, discountValue);
    }

    @Test
    public void testNoDiscountForLessThan10Items() {
        MoreOrEqual10ItemsDiscount discount = new MoreOrEqual10ItemsDiscount(null);
        Price price = new Price();
        price.setValue(100.0);

        double discountValue = discount.calculateDiscount(price, 5); // 5 itens

        System.out.println("----- Teste: Sem desconto para menos de 10 itens -----");
        System.out.printf("Preço por unidade: %.2f%n", price.getValue());
        System.out.printf("Quantidade: %d%n", 5);
        System.out.printf("Desconto esperado: %.2f%n", 0.0);
        System.out.printf("Desconto aplicado: %.2f%n", discountValue);
        System.out.println("-----------------------------------------------");

        // verificando se nenhum desconto foi aplicado (desconto deve ser 0)
        assertEquals(0.0, discountValue);
    }
}
