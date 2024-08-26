package br.edu.ufape.topicos.inventory.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.edu.ufape.topicos.inventory.model.Inventory;
import br.edu.ufape.topicos.inventory.repository.InventoryRepository;

class InventoryUnitTest {

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @Mock
    private InventoryRepository inventoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveInventory() {
        System.out.println("Iniciando teste: testSaveInventory");

        Inventory inventory = new Inventory();
        inventory.setProductId(1L);
        inventory.setQuantity(100);
        System.out.println("Inventory criado: " + inventory.getProductId() + ", quantidade: " + inventory.getQuantity());

        when(inventoryRepository.save(inventory)).thenReturn(inventory);

        Inventory result = inventoryService.saveInventory(inventory);

        System.out.println("Resultado do teste:");
        System.out.println("Inventário salvo: " + result.getProductId() + ", quantidade: " + result.getQuantity());

        assertNotNull(result);
        assertEquals(1L, result.getProductId());
        assertEquals(100, result.getQuantity());
        verify(inventoryRepository, times(1)).save(inventory);

        System.out.println("Teste testSaveInventory concluído com sucesso.");
    }

    @Test
    void testFindInventoryByProductId() {
        System.out.println("Iniciando teste: testFindInventoryByProductId");

        Inventory inventory1 = new Inventory();
        inventory1.setId(1L);
        inventory1.setProductId(1L);
        inventory1.setQuantity(50);

        Inventory inventory2 = new Inventory();
        inventory2.setId(2L);
        inventory2.setProductId(1L);
        inventory2.setQuantity(150);

        List<Inventory> inventories = Arrays.asList(inventory1, inventory2);
        System.out.println("Dois inventários foram criados para o produto com ID 1: Inventário com ID 1 e Inventário com ID 2");

        when(inventoryRepository.findByProductId(1L)).thenReturn(inventories);

        List<Inventory> result = inventoryService.findInventoryByProductId(1L);

        System.out.println("Resultado do teste:");
        result.forEach(inventory -> System.out.println("Inventário listado: ID = " + inventory.getId() + ", productId = " + inventory.getProductId() + ", quantidade = " + inventory.getQuantity()));

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());
        assertEquals(50, result.get(0).getQuantity());
        assertEquals(150, result.get(1).getQuantity());
        verify(inventoryRepository, times(1)).findByProductId(1L);

        System.out.println("Teste testFindInventoryByProductId concluído com sucesso.");
    }
}
