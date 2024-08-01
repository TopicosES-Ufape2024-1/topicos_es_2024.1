package br.edu.ufape.topicos.inventory.facade;

import br.edu.ufape.topicos.inventory.model.Inventory;
import br.edu.ufape.topicos.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryFacade {

    @Autowired
    public InventoryService inventoryService;

    public Inventory getInventoryById(Long id){
        return inventoryService.getInventoryById(id);
    }

    public List<Inventory> getAllInventories(){
        return inventoryService.getAllInventories();
    }

    public Inventory saveInventory(Inventory inventory){
        return inventoryService.saveInventory();
    }

    public void deleteInventory(Long id){
        inventoryService.deleteInventory();
    }


}