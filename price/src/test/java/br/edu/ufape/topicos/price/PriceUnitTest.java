package br.edu.ufape.topicos.price;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.edu.ufape.topicos.price.model.Price;
import br.edu.ufape.topicos.price.repository.PriceRepository;
import br.edu.ufape.topicos.price.service.PriceServiceImpl;
import br.edu.ufape.topicos.price.service.exceptions.PriceAlreadyExistsException;
import br.edu.ufape.topicos.price.service.exceptions.PriceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PriceUnitTest {

    @InjectMocks
    private PriceServiceImpl priceService;

    @Mock
    private PriceRepository priceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSavePrice() {
        System.out.println("Iniciando teste: testSavePrice");

        Price newPrice = new Price();
        newPrice.setProductId(1L);
        newPrice.setValue(100.0);
        System.out.println("Price criado: Product ID = " + newPrice.getProductId() + ", Value = " + newPrice.getValue());

        when(priceRepository.findByProductId(1L)).thenReturn(null);
        when(priceRepository.save(newPrice)).thenReturn(newPrice);

        Price result = priceService.savePrice(newPrice);

        System.out.println("Resultado do teste:");
        System.out.println("Preço salvo: Product ID = " + result.getProductId() + ", Value = " + result.getValue());

        assertNotNull(result);
        assertEquals(1L, result.getProductId());
        assertEquals(100.0, result.getValue());
        verify(priceRepository, times(1)).save(newPrice);

        Price existingPrice = new Price();
        existingPrice.setProductId(1L);
        existingPrice.setValue(150.0);

        when(priceRepository.findByProductId(1L)).thenReturn(existingPrice);

        assertThrows(PriceAlreadyExistsException.class, () -> {
            priceService.savePrice(existingPrice);
        });

        verify(priceRepository, never()).save(existingPrice);

        System.out.println("Teste testSavePrice concluído com sucesso.");
    }

    @Test
    void testGetPriceByProductId() {
        System.out.println("Iniciando teste: testGetPriceByProductId");

        Price price = new Price();
        price.setId(1L);
        price.setProductId(1L);
        price.setValue(100.0);

        when(priceRepository.findByProductId(1L)).thenReturn(price);

        Price result = priceService.getPriceByProductId(1L);

        System.out.println("Resultado do teste:");
        System.out.println("Preço encontrado: Product ID = " + result.getProductId() + ", Value = " + result.getValue());

        assertNotNull(result);
        assertEquals(1L, result.getProductId());
        assertEquals(100.0, result.getValue());
        verify(priceRepository, times(1)).findByProductId(1L);

        when(priceRepository.findByProductId(1L)).thenReturn(null);

        assertThrows(PriceNotFoundException.class, () -> {
            priceService.getPriceByProductId(1L);
        });

        verify(priceRepository, times(2)).findByProductId(1L);

        System.out.println("Teste testGetPriceByProductId concluído com sucesso.");
    }
}
