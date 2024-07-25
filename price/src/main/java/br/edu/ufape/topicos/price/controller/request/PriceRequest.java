package br.edu.ufape.topicos.price.controller.request;

import br.edu.ufape.topicos.price.model.Price;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceRequest {
    @NotNull(message = "The productID is required")
    private Long productId;

    @NotNull(message = "The value is required")
    private Double value;

    public Price toPrice() {
        Price price = new Price();
        price.setProductId(this.productId);
        price.setValue(this.value);
        return price;
    }

}
