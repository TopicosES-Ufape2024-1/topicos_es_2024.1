package br.edu.ufape.topicos.inventory.message;

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

    @Bean
    public Consumer<Event<Integer, Long>> consumeMessage() {
        return e -> {
            switch (e.getType()) {
                case CREATE:
                    Inventory iv = new Inventory();
                    iv.setProductId(e.getData());
                    iv.setQuantity(0);
                    inventoryRepository.save(iv);
                    break;
                default:
                    System.out.println("Ops ...");
            }
        };
    }
}
