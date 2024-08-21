package br.edu.ufape.topicos.price.facade;

import br.edu.ufape.topicos.price.controller.response.CalculatePriceResponse;
import br.edu.ufape.topicos.price.model.Price;
import br.edu.ufape.topicos.price.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PriceFacadeTest {

    @InjectMocks
    private PriceFacade priceFacade;

    @Mock
    private PriceService priceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateFinalPriceWithDiscount() {
        Long productId = 1L;
        int quantity = 10; // Quantidade que deveria aplicar desconto
        double priceValue = 100.0;

        Price price = new Price();
        price.setId(productId);
        price.setValue(priceValue);

        when(priceService.getPriceByProductId(productId)).thenReturn(price);

        CalculatePriceResponse response = priceFacade.calculateFinalPrice(productId, quantity);

        // Valores esperados
        double expectedDiscount = priceValue * 0.1 * quantity; // Desconto de 10%
        double expectedTotalWithoutDiscount = priceValue * quantity;
        double expectedTotalWithDiscount = expectedTotalWithoutDiscount - expectedDiscount;

        // Prints organizados para visualização se está funcionando
        System.out.println("----- Teste: Calcular Preço Final Com Desconto -----");
        System.out.printf("Preço por unidade: %.2f%n", priceValue);
        System.out.printf("Quantidade: %d%n", quantity);
        System.out.printf("Total esperado sem desconto: %.2f%n", expectedTotalWithoutDiscount);
        System.out.printf("Desconto esperado: %.2f%n", expectedDiscount);
        System.out.printf("Total esperado com desconto: %.2f%n", expectedTotalWithDiscount);
        System.out.printf("Desconto aplicado: %.2f%n", response.discount());
        System.out.printf("Total calculado com desconto: %.2f%n", response.totalWithDiscount());
        System.out.printf("Total calculado sem desconto: %.2f%n", response.totalWithoutDiscount());
        System.out.println("-----------------------------------------------");

        assertEquals(expectedTotalWithDiscount, response.totalWithDiscount());
        assertEquals(expectedTotalWithoutDiscount, response.totalWithoutDiscount());
        assertEquals(expectedDiscount, response.discount());
    }

    @Test
    public void testCalculateFinalPriceWithoutDiscount() {
        Long productId = 1L;
        int quantity = 5; // Quantidade que não deveria aplicar desconto
        double priceValue = 100.0;

        Price price = new Price();
        price.setId(productId);
        price.setValue(priceValue);

        when(priceService.getPriceByProductId(productId)).thenReturn(price);

        CalculatePriceResponse response = priceFacade.calculateFinalPrice(productId, quantity);

        System.out.println("----- Teste: Calcular Preço Final Sem Desconto -----");
        System.out.printf("Preço por unidade: %.2f%n", priceValue);
        System.out.printf("Quantidade: %d%n", quantity);
        System.out.printf("Total esperado sem desconto: %.2f%n", priceValue * quantity);
        System.out.printf("Desconto esperado: %.2f%n", 0.0);
        System.out.printf("Desconto aplicado: %.2f%n", response.discount());
        System.out.printf("Total calculado com desconto: %.2f%n", response.totalWithDiscount());
        System.out.printf("Total calculado sem desconto: %.2f%n", response.totalWithoutDiscount());
        System.out.println("-----------------------------------------------");

        // Valores esperados
        double expectedDiscount = 0.0; // Nenhum desconto deve ser aplicado
        double expectedTotalWithoutDiscount = priceValue * quantity;
        double expectedTotalWithDiscount = expectedTotalWithoutDiscount;

        assertEquals(expectedTotalWithDiscount, response.totalWithDiscount(), 0.01);
        assertEquals(expectedTotalWithoutDiscount, response.totalWithoutDiscount(), 0.01);
        assertEquals(expectedDiscount, response.discount(), 0.01);
    }
}
