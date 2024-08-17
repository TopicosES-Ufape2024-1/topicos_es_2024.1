package br.edu.ufape.topicos.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.topicos.inventory.model.Warehouse;
import br.edu.ufape.topicos.inventory.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public interface WarehouseService {

    List<Warehouse> findAllWarehouses();

    Optional<Warehouse> findWarehouseById(Long id);

    Warehouse saveWarehouse(Warehouse warehouse);

    void deleteWarehouse(Long id);
}
