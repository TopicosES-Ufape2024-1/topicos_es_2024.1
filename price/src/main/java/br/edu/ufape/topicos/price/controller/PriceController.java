package br.edu.ufape.topicos.price.controller;

import br.edu.ufape.topicos.price.controller.request.CalculatePriceRequest;
import br.edu.ufape.topicos.price.controller.request.PriceRequest;
import br.edu.ufape.topicos.price.controller.response.CalculatePriceResponse;
import br.edu.ufape.topicos.price.controller.response.PriceResponse;
import br.edu.ufape.topicos.price.facade.PriceFacade;
import br.edu.ufape.topicos.price.model.Price;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/price")
public class PriceController {

    @Autowired
    private PriceFacade priceFacade;

    @GetMapping
    public List<PriceResponse> getAllPrices() {
        return priceFacade.getAllPrices().stream()
                .map(PriceResponse::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceResponse> getPriceById(@PathVariable Long id) {
        Price price = priceFacade.getPriceById(id);
        return ResponseEntity.ok(new PriceResponse(price));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<PriceResponse> getPriceByProductId(@PathVariable Long productId) {
        Price price = priceFacade.getPriceByProductId(productId);
        return ResponseEntity.ok(new PriceResponse(price));
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createPrice(@Valid @RequestBody PriceRequest priceRequest) {
        priceFacade.savePrice(priceRequest.toPrice());
        return ResponseEntity.ok(Map.of("message", "Price created successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletePrice(@PathVariable Long id) {
        priceFacade.deletePrice(id);
        return ResponseEntity.ok(Map.of("message", "Price deleted successfully"));
    }

    @PostMapping("/calculate")
    public ResponseEntity<CalculatePriceResponse> calculateFinalPrice(@Valid @RequestBody CalculatePriceRequest request) {
        CalculatePriceResponse finalPrice = priceFacade.calculateFinalPrice(request.productId(), request.quantity());
        return ResponseEntity.ok(finalPrice);
    }
}
