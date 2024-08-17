package br.edu.ufape.topicos.inventory.controller.request;

import br.edu.ufape.topicos.inventory.model.Warehouse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseRequest {
    @NotBlank(message = "O nome do armazém é obrigatório")
    private String name;

    @NotBlank(message = "A localização do armazém é obrigatória")
    private String location;

    public Warehouse toWarehouse() {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(this.name);
        warehouse.setLocation(this.location);
        return warehouse;
    }
}
