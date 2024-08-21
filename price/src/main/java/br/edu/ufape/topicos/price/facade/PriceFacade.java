package br.edu.ufape.topicos.price.facade;

import br.edu.ufape.topicos.price.controller.response.CalculatePriceResponse;
import br.edu.ufape.topicos.price.model.Price;
import br.edu.ufape.topicos.price.service.PriceService;
import br.edu.ufape.topicos.price.discounts.Discount;
import br.edu.ufape.topicos.price.discounts.MoreOrEqual10ItemsDiscount;
import br.edu.ufape.topicos.price.discounts.MoreOrEqual500ValueDiscount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceFacade {

    @Autowired
    public PriceService priceService;

    public Price getPriceById(Long id) {
        return priceService.getPriceById(id);
    }

    public List<Price> getAllPrices() {
        return priceService.getAllPrices();
    }

    public Price getPriceByProductId(Long productId) {
        return priceService.getPriceByProductId(productId);
    }

    public Price savePrice(Price price) {
        return priceService.savePrice(price);
    }

    public void deletePrice(Long id) {
        priceService.deletePrice(id);
    }

    public CalculatePriceResponse calculateFinalPrice(Long productId, int quantity){
        Price price = priceService.getPriceByProductId(productId);

        // Criação da cadeia de políticas de preço
        Discount discountChain = new MoreOrEqual10ItemsDiscount(
                                    new MoreOrEqual500ValueDiscount(null));
        
        double discount = discountChain.calculateDiscount(price, quantity);
        double totalWithoutDiscount = price.getValue() * quantity;
        double totalWithDiscount = totalWithoutDiscount - discount;

        return new CalculatePriceResponse(totalWithDiscount, totalWithoutDiscount, discount);
    }
}
