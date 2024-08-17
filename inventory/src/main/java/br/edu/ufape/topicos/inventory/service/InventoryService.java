package br.edu.ufape.topicos.inventory.service;

import br.edu.ufape.topicos.inventory.model.Inventory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface InventoryService {
    List<Inventory> findAllInventory();

    Optional<Inventory> findInventoryById(Long id);

    List<Inventory> findInventoryByProductId(Long productId);

    List<Inventory> findInventoryByWarehouseId(Long warehouseId);

    Inventory saveInventory(Inventory inventory);

    void deleteInventory(Long id);
}
