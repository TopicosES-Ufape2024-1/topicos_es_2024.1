package br.edu.ufape.topicos.inventory.service;

import br.edu.ufape.topicos.inventory.service.exceptions.InventoryAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.topicos.inventory.model.Inventory;
import br.edu.ufape.topicos.inventory.repository.InventoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> findAllInventory() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> findInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    public List<Inventory> findInventoryByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId);
    }

    public List<Inventory> findInventoryByWarehouseId(Long warehouseId) {
        return inventoryRepository.findByWarehouseId(warehouseId);
    }

    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}
