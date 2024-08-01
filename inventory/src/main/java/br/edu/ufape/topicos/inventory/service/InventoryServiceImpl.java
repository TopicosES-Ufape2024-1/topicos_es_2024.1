package br.edu.ufape.topicos.inventory.service;

import br.edu.ufape.topicos.inventory.service.exceptions.InventoryAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.topicos.inventory.model.Inventory;
import br.edu.ufape.topicos.inventory.repository.InventoryRepository;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService{

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> getAllInventories(){
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory getInventoryById(Long id){
        return inventoryRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteInventory(Long id){
        inventoryRepository.deleteById(id);
    }
    
}
