package br.edu.ufape.topicos.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.topicos.inventory.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByProductId(Long productId);
    List<Inventory> findByWarehouseId(Long warehouseId);
}
