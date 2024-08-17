package br.edu.ufape.topicos.inventory.controller.response;

import br.edu.ufape.topicos.inventory.config.SpringApplicationContext;
import br.edu.ufape.topicos.inventory.model.Inventory;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class InventoryResponse {
    private Long id;
    private Long productId;
    private int quantity;
    private Long warehouseId;

    public InventoryResponse(Inventory inventory){
        ModelMapper modelMapper = (ModelMapper) SpringApplicationContext.getBean("modelMapper");
        modelMapper.map(inventory, this);
    }
}
