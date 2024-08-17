package br.edu.ufape.topicos.inventory.controller.request;

import br.edu.ufape.topicos.inventory.model.Inventory;
import br.edu.ufape.topicos.inventory.model.Warehouse;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class InventoryRequest {

    @NotNull
    private Long productId;

    @NotNull
    private int quantity;

    @NotNull
    private Long warehouseId;

    public Inventory toInventory() {
        Inventory inventory = new Inventory();
        inventory.setProductId(this.productId);
        inventory.setQuantity(this.quantity);
        Warehouse warehouse = new Warehouse();
        warehouse.setId(this.warehouseId);
        inventory.setWarehouse(warehouse);

        return inventory;
    }
}
