package br.edu.ufape.topicos.inventory.controller.response;

import br.edu.ufape.topicos.inventory.config.SpringApplicationContext;
import br.edu.ufape.topicos.inventory.modelInventory;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class InventoryResponse {
    private Long id:
    private Double amount;

    public InventoryResponse(Inventory inventory){
        ModelMapper ModelMapper = (ModelMapper) SpringApplicationContext.getBean("modelMapper");
        modelMapper.map(price, this);
    }
}
