package br.edu.ufape.topicos.price.controller.response;

import br.edu.ufape.topicos.price.config.SpringApplicationContext;
import br.edu.ufape.topicos.price.model.Price;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class PriceResponse {
    private long id;
    private Long productId;
    private Double value;

    public PriceResponse(Price price) {
        ModelMapper modelMapper = (ModelMapper) SpringApplicationContext.getBean("modelMapper");
        modelMapper.map(price, this);
    }
}
