package br.edu.ufape.topicos.inventory.controller;

import br.edu.ufape.topicos.inventory.facade.InventoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ufape.topicos.inventory.controller.request.WarehouseRequest;
import br.edu.ufape.topicos.inventory.controller.response.WarehouseResponse;
import br.edu.ufape.topicos.inventory.model.Warehouse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private InventoryFacade warehouseFacade;

    @GetMapping
    public ResponseEntity<List<WarehouseResponse>> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseFacade.getAllWarehouses();
        List<WarehouseResponse> responses = warehouses.stream()
                .map(WarehouseResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseResponse> getWarehouseById(@PathVariable Long id) {
        Optional<Warehouse> warehouse = warehouseFacade.getWarehouseById(id);
        return warehouse.map(value -> ResponseEntity.ok(new WarehouseResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<WarehouseResponse> createWarehouse(@RequestBody WarehouseRequest request) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(request.getName());
        warehouse.setLocation(request.getLocation());

        Warehouse savedWarehouse = warehouseFacade.createWarehouse(warehouse);
        return ResponseEntity.ok(new WarehouseResponse(savedWarehouse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseResponse> updateWarehouse(@PathVariable Long id, @RequestBody WarehouseRequest request) {
        Warehouse warehouseDetails = new Warehouse();
        warehouseDetails.setName(request.getName());
        warehouseDetails.setLocation(request.getLocation());

        Warehouse updatedWarehouse = warehouseFacade.updateWarehouse(id, warehouseDetails);
        return ResponseEntity.ok(new WarehouseResponse(updatedWarehouse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        warehouseFacade.deleteWarehouse(id);
        return ResponseEntity.ok().build();
    }
}
