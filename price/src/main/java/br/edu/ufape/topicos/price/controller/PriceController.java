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

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/price")
public class PriceController {

    @Autowired
    private PriceFacade priceFacade;


    @GetMapping
    public List<PriceResponse> getAllPrices() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt principal = (Jwt) authentication.getPrincipal();
        System.out.println(principal.getClaimAsString("email"));
        System.out.println(principal.getId());
        System.out.println(authentication.getAuthorities());
        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getName());

        return priceFacade.getAllPrices().stream().map(PriceResponse::new).toList();
    }

    @GetMapping("/{id}")
    public PriceResponse getPriceById(@PathVariable Long id) {
        Price price = priceFacade.getPriceById(id);
        return new PriceResponse(price);
    }

    @GetMapping("/product/{productId}")
    public PriceResponse getPriceByProductId(@PathVariable Long productId) {
        Price price = priceFacade.getPriceByProductId(productId);
        return new PriceResponse(price);
    }

    @PostMapping
    public Map<String, String> createPrice(@Valid @RequestBody PriceRequest priceRequest) {
        new PriceResponse(priceFacade.savePrice(priceRequest.toPrice()));
        return Map.of("message", "Price created successfully");
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deletePrice(@PathVariable Long id) {
        priceFacade.deletePrice(id);
        return Map.of("message", "Price deleted successfully");
    }

    @GetMapping("/calculate")
    public ResponseEntity<CalculatePriceResponse> calculateFinalPrice(@Valid @RequestBody CalculatePriceRequest request){
        CalculatePriceResponse finalPrice = priceFacade.calculateFinalPrice(request.productId(), request.quantity());
        return ResponseEntity.ok(finalPrice);
    }

}
