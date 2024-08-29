package br.edu.ufape.topicos.inventory.message;

import br.edu.ufape.topicos.inventory.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.function.Consumer;
import br.edu.ufape.topicos.inventory.model.Inventory;
import br.edu.ufape.topicos.inventory.repository.InventoryRepository;

@Configuration
public class MessageProcessor {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Bean
    public Consumer<Event<Long, String>> consumeMessage() {
        return e -> {
            switch (e.getType()) {
                case CREATE:
                    warehouseRepository.findAll().forEach(w -> {
                        Inventory iv = new Inventory();
                        iv.setProductId(e.getKey());
                        iv.setQuantity(0);
                        iv.setWarehouse(w);
                        inventoryRepository.save(iv);
                    });
                    break;
                default:
                    System.out.println("Ops ...");
            }
        };
    }
}
