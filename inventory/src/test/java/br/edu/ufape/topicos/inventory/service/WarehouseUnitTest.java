package br.edu.ufape.topicos.inventory.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.edu.ufape.topicos.inventory.model.Warehouse;
import br.edu.ufape.topicos.inventory.repository.WarehouseRepository;

class WarehouseUnitTest {

    @InjectMocks
    private WarehouseServiceImpl warehouseService;

    @Mock
    private WarehouseRepository warehouseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveWarehouse() {
        System.out.println("Iniciando teste: testSaveWarehouse");

        Warehouse warehouse = new Warehouse();
        warehouse.setName("Principal Warehouse");
        warehouse.setLocation("New York");
        System.out.println("Warehouse criado: " + warehouse.getName() + ", localização: " + warehouse.getLocation());

        when(warehouseRepository.save(warehouse)).thenReturn(warehouse);

        Warehouse result = warehouseService.saveWarehouse(warehouse);

        System.out.println("Resultado do teste:");
        System.out.println("Warehouse salvo: " + result.getName() + ", localização: " + result.getLocation());

        assertNotNull(result);
        assertEquals("Principal Warehouse", result.getName());
        assertEquals("New York", result.getLocation());
        verify(warehouseRepository, times(1)).save(warehouse);

        System.out.println("Teste testSaveWarehouse concluído com sucesso.");
    }

    @Test
    void testFindWarehouseById() {
        System.out.println("Iniciando teste: testFindWarehouseById");

        Warehouse warehouse = new Warehouse();
        warehouse.setId(1L);
        warehouse.setName("Principal Warehouse");
        warehouse.setLocation("New York");
        System.out.println("Warehouse criado: ID = " + warehouse.getId() + ", Nome = " + warehouse.getName());

        when(warehouseRepository.findById(1L)).thenReturn(Optional.of(warehouse));

        Optional<Warehouse> result = warehouseService.findWarehouseById(1L);

        System.out.println("Resultado do teste:");
        result.ifPresentOrElse(
                w -> System.out.println("Warehouse encontrado: ID = " + w.getId() + ", Nome = " + w.getName() + ", Localização = " + w.getLocation()),
                () -> System.out.println("Warehouse não encontrado")
        );

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("Principal Warehouse", result.get().getName());
        assertEquals("New York", result.get().getLocation());
        verify(warehouseRepository, times(1)).findById(1L);

        System.out.println("Teste testFindWarehouseById concluído com sucesso.");
    }
}
