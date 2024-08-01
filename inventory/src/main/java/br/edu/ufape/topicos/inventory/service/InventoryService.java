package br.edu.ufape.topicos.inventory.service;

import br.edu.ufape.topicos.inventory.model.Inventory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    List<Inventory> getAllInventories();
    Inventory getInventoryById(Long id);
    Inventory saveInventory(Inventory inventory);
    void deletedInventory(Long id);
    
}
