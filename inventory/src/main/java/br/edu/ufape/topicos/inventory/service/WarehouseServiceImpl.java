package br.edu.ufape.topicos.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.topicos.inventory.model.Warehouse;
import br.edu.ufape.topicos.inventory.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    public List<Warehouse> findAllWarehouses() {
        List<Warehouse> all = warehouseRepository.findAll();
        if (all.isEmpty()) {
            Warehouse warehouse = new Warehouse();
            warehouse.setName("Default Warehouse");
            warehouse.setLocation("Default Location");
            warehouseRepository.save(warehouse);
            all = warehouseRepository.findAll();
        }
        return all;
    }

    public Optional<Warehouse> findWarehouseById(Long id) {
        return warehouseRepository.findById(id);
    }

    public Warehouse saveWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }
}
