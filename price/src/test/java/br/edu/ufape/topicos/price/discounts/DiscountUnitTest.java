package br.edu.ufape.topicos.price.discounts;

import static org.junit.jupiter.api.Assertions.*;

import br.edu.ufape.topicos.price.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiscountUnitTest {

    private Discount discountChain;

    @BeforeEach
    void setUp() {
        discountChain = new MoreOrEqual500ValueDiscount(new MoreOrEqual10ItemsDiscount(null));
    }

    @Test
    void testMoreOrEqual10ItemsDiscount() {
        System.out.println("Iniciando teste: testMoreOrEqual10ItemsDiscount");

        Price price = new Price();
        price.setValue(50.0);

        double discount = discountChain.calculateDiscount(price, 10);
        System.out.println("Desconto calculado: " + discount);

        assertEquals(50.0, discount); // 10% de 50 * 10

        discount = discountChain.calculateDiscount(price, 5);
        System.out.println("Desconto calculado (quantidade menor que 10): " + discount);

        assertEquals(0.0, discount);

        System.out.println("Teste testMoreOrEqual10ItemsDiscount concluído com sucesso.");
    }

    @Test
    void testMoreOrEqual500ValueDiscount() {
        System.out.println("Iniciando teste: testMoreOrEqual500ValueDiscount");

        Price price = new Price();
        price.setValue(60.0);

        double discount = discountChain.calculateDiscount(price, 10); // Valor total = 600
        System.out.println("Desconto calculado: " + discount);

        assertEquals(30.0, discount); // 5% de 600

        price.setValue(40.0);
        discount = discountChain.calculateDiscount(price, 12); // Valor total = 480, sem desconto de valor
        System.out.println("Desconto calculado (valor total < 500): " + discount);

        assertEquals(48.0, discount); // 10% de 40 * 12

        System.out.println("Teste testMoreOrEqual500ValueDiscount concluído com sucesso.");
    }
}
