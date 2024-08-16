package br.edu.ufape.topicos.price.service;

import br.edu.ufape.topicos.price.controller.response.CalculatePriceResponse;
import br.edu.ufape.topicos.price.discounts.Discount;
import br.edu.ufape.topicos.price.discounts.MoreOrEqual10ItemsDiscount;
import br.edu.ufape.topicos.price.discounts.MoreOrEqual500ValueDiscount;
import br.edu.ufape.topicos.price.service.exceptions.PriceAlreadyExistsException;
import br.edu.ufape.topicos.price.service.exceptions.PriceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.topicos.price.model.Price;
import br.edu.ufape.topicos.price.repository.PriceRepository;

import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }

    @Override
    public Price getPriceById(Long id) {
        return priceRepository.findById(id).orElseThrow(() -> new PriceNotFoundException("Price with id [" + id + "] not found."));
    }

    @Override
    public Price getPriceByProductId(Long productId) {
        Price price = priceRepository.findByProductId(productId);
        if (price == null) {
            throw new PriceNotFoundException("Price for product with id [" + productId + "] not found.");
        }
        return price;
    }

    @Override
    public Price savePrice(Price price) {
        if (priceRepository.findByProductId(price.getProductId()) != null) {
            throw new PriceAlreadyExistsException("Price for product with id [" + price.getProductId() + "] already exists.");
        }
        return priceRepository.save(price);
    }

    @Override
    public void deletePrice(Long id) {
        priceRepository.deleteById(id);
    }

    @Override
    public CalculatePriceResponse calculateFinalPrice(Long productId, int quantity) {
        Price price = priceRepository.findByProductId(productId);
        if (price == null) {
            throw new PriceNotFoundException("Price not found for product ID " + productId);
        }
        
        // Encadeamento de descontos
        Discount combinedDiscounts = new MoreOrEqual500ValueDiscount(
                new MoreOrEqual10ItemsDiscount(null)
        );
        
        double totalAmount = price.getValue() * quantity;
        double discountAmount = combinedDiscounts.calculateDiscount(price, quantity);
        double finalPrice = totalAmount - discountAmount;
        
        return new CalculatePriceResponse(
            finalPrice,
            totalAmount,
            discountAmount
        );
    }
}
