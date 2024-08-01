package br.edu.ufape.topicos.inventory.controller;

import br.edu.ufape.topicos.inventory.controller.request.InventoryRequest;
import br.edu.ufape.topicos.inventory.controller.response.InventoryResponse;
import br.edu.ufape.topicos.inventory.facade.InventoryFacade;
import br.edu.ufape.topicos.inventory.model.Inventory;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    
    @Autowired
    private InventoryFacade inventoryFacade;

    @Autowired 
    private ModelMapper modelMapper;

    @GetMapping
    public List<InventoryResponse> getAllInventories(){
        return inventoryFacade.getAllInventories().stream().map(InventoryResponse::new).toList();
    }

    @GetMapping("/{id}")
    public InventoryResponse getInventoryById(@PathVariable Long id){
        Inventory inventory = inventoryFacade.getInventoryById(id);
        return new InventoryResponse(inventory);
    }

    @PostMapping
    public Map<String, String> createInventory(@Valid @RequestBody InventoryRequest inventoryRequest){
        new InventoryResponse(inventoryFacade.saveInventory(inventoryRequest.toInventory()));
        return Map.of("message", "Inventory created successfully");        
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteInventory(@PathVariable Long id){
        inventoryFacade.deleteInventory(id);
        return Map.of("message", "Inventory deleted successfully");
    }
}
