package br.edu.ufape.topicos.inventory.controller;

import br.edu.ufape.topicos.inventory.controller.request.InventoryRequest;
import br.edu.ufape.topicos.inventory.controller.response.InventoryResponse;
import br.edu.ufape.topicos.inventory.facade.InventoryFacade;
import br.edu.ufape.topicos.inventory.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryFacade inventoryFacade;

    @GetMapping
    @PreAuthorize("hasRole('manager') or hasRole('user')")
    public ResponseEntity<List<InventoryResponse>> getAllInventory() {
        List<Inventory> inventories = inventoryFacade.getAllInventory();
        List<InventoryResponse> responses = inventories.stream()
                .map(InventoryResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('manager') or hasRole('user')")
    public ResponseEntity<InventoryResponse> getInventoryById(@PathVariable Long id) {
        Optional<Inventory> inventory = inventoryFacade.getInventoryById(id);
        return inventory.map(value -> ResponseEntity.ok(new InventoryResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('manager')")
    public ResponseEntity<InventoryResponse> createInventory(@RequestBody InventoryRequest request) {
        Inventory inventory = request.toInventory();
        Inventory savedInventory = inventoryFacade.createInventory(inventory);
        return ResponseEntity.ok(new InventoryResponse(savedInventory));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('manager')")
    public ResponseEntity<InventoryResponse> updateInventory(@PathVariable Long id, @RequestBody InventoryRequest request) {
        Inventory inventoryDetails = request.toInventory();
        Inventory updatedInventory = inventoryFacade.updateInventory(id, inventoryDetails);
        return ResponseEntity.ok(new InventoryResponse(updatedInventory));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('manager')")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        inventoryFacade.deleteInventory(id);
        return ResponseEntity.ok().build();
    }
}
