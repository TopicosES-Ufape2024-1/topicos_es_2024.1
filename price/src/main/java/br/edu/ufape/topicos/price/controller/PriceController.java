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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PriceController {

    @Autowired
    private PriceFacade priceFacade;


    @GetMapping("/price")
    @PreAuthorize("hasRole('user') or hasRole('manager')")
    public List<PriceResponse> getAllPrices() {
        return priceFacade.getAllPrices().stream().map(PriceResponse::new).toList();
    }

    @GetMapping("/price/{id}")
    @PreAuthorize("hasRole('user') or hasRole('manager')")
    public PriceResponse getPriceById(@PathVariable Long id) {
        Price price = priceFacade.getPriceById(id);
        return new PriceResponse(price);
    }

    @GetMapping("/price/product/{productId}")
    @PreAuthorize("hasRole('user') or hasRole('manager')")
    public PriceResponse getPriceByProductId(@PathVariable Long productId) {
        Price price = priceFacade.getPriceByProductId(productId);
        return new PriceResponse(price);
    }

    @GetMapping("/intranet/price/product/{productId}")
    public PriceResponse getPriceByProductIdIntranet(@PathVariable Long productId) {
        Price price = priceFacade.getPriceByProductId(productId);
        return new PriceResponse(price);
    }

    @PostMapping("/price")
    @PreAuthorize("hasRole('manager')")
    public Map<String, String> createPrice(@Valid @RequestBody PriceRequest priceRequest) {
        new PriceResponse(priceFacade.savePrice(priceRequest.toPrice()));
        return Map.of("message", "Price created successfully");
    }

    @DeleteMapping("/price/{id}")
    @PreAuthorize("hasRole('manager')")
    public Map<String, String> deletePrice(@PathVariable Long id) {
        priceFacade.deletePrice(id);
        return Map.of("message", "Price deleted successfully");
    }

    @GetMapping("/price/calculate")
    @PreAuthorize("hasRole('user') or hasRole('manager')")
    public ResponseEntity<CalculatePriceResponse> calculateFinalPrice(@Valid @RequestBody CalculatePriceRequest request){
        CalculatePriceResponse finalPrice = priceFacade.calculateFinalPrice(request.productId(), request.quantity());
        return ResponseEntity.ok(finalPrice);
    }

}
