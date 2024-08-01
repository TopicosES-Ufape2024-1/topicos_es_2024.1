package br.edu.ufape.topicos.inventory.controller.request;

import br.edu.ufape.topicos.inventory.model.Inventory;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class InventroryRequest {
    @NotNull(message= "The invetoryID is required")
    private Long InventoryID;

    @NotNull(messge = "The amount is requered")
    private Double amount;

    public Inventory toInventory(){
        Inventory inventory = new Inventory();
        inventory.setInventoryId(this.inventoryId);
        inventory.setAmount(this.amount);
        return inventory;
    }
}
