package br.edu.ufape.topicos.inventory.controller;

import br.edu.ufape.topicos.auth.JWTConverter;
import br.edu.ufape.topicos.inventory.controller.request.WarehouseRequest;
import br.edu.ufape.topicos.inventory.controller.response.WarehouseResponse;
import br.edu.ufape.topicos.inventory.facade.InventoryFacade;
import br.edu.ufape.topicos.inventory.model.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    private static final Logger logger = LoggerFactory.getLogger(JWTConverter.class);

    @Autowired
    private InventoryFacade warehouseFacade;

    @GetMapping
    @PreAuthorize("hasRole('user') or hasRole('manager')")
    public ResponseEntity<List<WarehouseResponse>> getAllWarehouses(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        logger.info("Authenticated user roles: {}", authorities);

        List<Warehouse> warehouses = warehouseFacade.getAllWarehouses();
        List<WarehouseResponse> responses = warehouses.stream()
                .map(WarehouseResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('manager') or hasRole('user')")
    public ResponseEntity<WarehouseResponse> getWarehouseById(@PathVariable Long id) {
        Optional<Warehouse> warehouse = warehouseFacade.getWarehouseById(id);
        return warehouse.map(value -> ResponseEntity.ok(new WarehouseResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('manager')")
    public ResponseEntity<WarehouseResponse> createWarehouse(@RequestBody WarehouseRequest request) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(request.getName());
        warehouse.setLocation(request.getLocation());

        Warehouse savedWarehouse = warehouseFacade.createWarehouse(warehouse);
        return ResponseEntity.ok(new WarehouseResponse(savedWarehouse));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('manager')")
    public ResponseEntity<WarehouseResponse> updateWarehouse(@PathVariable Long id, @RequestBody WarehouseRequest request) {
        Warehouse warehouseDetails = new Warehouse();
        warehouseDetails.setName(request.getName());
        warehouseDetails.setLocation(request.getLocation());

        Warehouse updatedWarehouse = warehouseFacade.updateWarehouse(id, warehouseDetails);
        return ResponseEntity.ok(new WarehouseResponse(updatedWarehouse));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('manager')")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        warehouseFacade.deleteWarehouse(id);
        return ResponseEntity.ok().build();
    }
}
