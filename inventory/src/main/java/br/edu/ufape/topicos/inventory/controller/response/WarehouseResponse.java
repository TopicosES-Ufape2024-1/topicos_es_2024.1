package br.edu.ufape.topicos.inventory.controller.response;

import br.edu.ufape.topicos.inventory.model.Warehouse;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class WarehouseResponse {
    private Long id;
    private String name;
    private String location;

    public WarehouseResponse(Warehouse warehouse) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(warehouse, this);
    }
}
