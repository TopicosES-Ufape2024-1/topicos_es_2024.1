package br.edu.ufape.topicos.inventory.controller;

import br.edu.ufape.topicos.inventory.controller.request.InventoryRequest;
import br.edu.ufape.topicos.inventory.controller.response.InventoryResponse;
import br.edu.ufape.topicos.inventory.facade.InventoryFacade;
import br.edu.ufape.topicos.inventory.model.Inventory;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryFacade inventoryFacade;

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAllInventory() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt principal = (Jwt) authentication.getPrincipal();
        System.out.println(principal.getClaimAsString("email"));
        System.out.println(principal.getId());
        System.out.println(authentication.getAuthorities());
        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getName());

        List<Inventory> inventories = inventoryFacade.getAllInventory();
        List<InventoryResponse> responses = inventories.stream()
                .map(InventoryResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponse> getInventoryById(@PathVariable Long id) {
        Optional<Inventory> inventory = inventoryFacade.getInventoryById(id);
        return inventory.map(value -> ResponseEntity.ok(new InventoryResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InventoryResponse> createInventory(@RequestBody InventoryRequest request) {
        Inventory inventory = request.toInventory();
        Inventory savedInventory = inventoryFacade.createInventory(inventory);
        return ResponseEntity.ok(new InventoryResponse(savedInventory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponse> updateInventory(@PathVariable Long id, @RequestBody InventoryRequest request) {
        Inventory inventoryDetails = request.toInventory();
        Inventory updatedInventory = inventoryFacade.updateInventory(id, inventoryDetails);
        return ResponseEntity.ok(new InventoryResponse(updatedInventory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        inventoryFacade.deleteInventory(id);
        return ResponseEntity.ok().build();
    }
}
