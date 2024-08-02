package br.edu.ufape.topicos.price.service;

import br.edu.ufape.topicos.price.controller.response.CalculatePriceResponse;
import br.edu.ufape.topicos.price.model.Price;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PriceService {
    List<Price> getAllPrices();
    Price getPriceById(Long id);
    Price getPriceByProductId(Long productId);
    Price savePrice(Price price);
    void deletePrice(Long id);
    CalculatePriceResponse calculateFinalPrice(Long productId, int quantity);
}