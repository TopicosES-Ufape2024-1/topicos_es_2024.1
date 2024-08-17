package br.edu.ufape.topicos.inventory.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ufape.topicos.inventory.model.Inventory;
import br.edu.ufape.topicos.inventory.model.Warehouse;
import br.edu.ufape.topicos.inventory.service.InventoryService;
import br.edu.ufape.topicos.inventory.service.WarehouseService;

import java.util.List;
import java.util.Optional;

@Component
public class InventoryFacade {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private WarehouseService warehouseService;


    public List<Inventory> getAllInventory() {
        return inventoryService.findAllInventory();
    }

    public Optional<Inventory> getInventoryById(Long id) {
        return inventoryService.findInventoryById(id);
    }

    public Inventory createInventory(Inventory inventory) {
        Warehouse warehouse = warehouseService.findWarehouseById(inventory.getWarehouse().getId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found with ID: " + inventory.getWarehouse().getId()));
        inventory.setWarehouse(warehouse);
        return inventoryService.saveInventory(inventory);
    }

    public Inventory updateInventory(Long id, Inventory inventoryDetails) {
        Optional<Inventory> optionalInventory = inventoryService.findInventoryById(id);
        if (optionalInventory.isPresent()) {
            Inventory existingInventory = optionalInventory.get();
            existingInventory.setProductId(inventoryDetails.getProductId());
            existingInventory.setQuantity(inventoryDetails.getQuantity());
            existingInventory.setWarehouse(inventoryDetails.getWarehouse());
            return inventoryService.saveInventory(existingInventory);
        }
        throw new RuntimeException("Inventory not found with ID: " + id);
    }

    public void deleteInventory(Long id) {
        inventoryService.deleteInventory(id);
    }


    // Retrieve all warehouses
    public List<Warehouse> getAllWarehouses() {
        return warehouseService.findAllWarehouses();
    }

    // Retrieve a warehouse by ID
    public Optional<Warehouse> getWarehouseById(Long id) {
        return warehouseService.findWarehouseById(id);
    }

    // Create a new warehouse
    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseService.saveWarehouse(warehouse);
    }

    // Update an existing warehouse
    public Warehouse updateWarehouse(Long id, Warehouse warehouseDetails) {
        Optional<Warehouse> optionalWarehouse = warehouseService.findWarehouseById(id);
        if (optionalWarehouse.isPresent()) {
            Warehouse existingWarehouse = optionalWarehouse.get();
            existingWarehouse.setName(warehouseDetails.getName());
            existingWarehouse.setLocation(warehouseDetails.getLocation());
            return warehouseService.saveWarehouse(existingWarehouse);
        }
        throw new RuntimeException("Warehouse not found with ID: " + id);
    }

    // Delete a warehouse by ID
    public void deleteWarehouse(Long id) {
        warehouseService.deleteWarehouse(id);
    }
}
