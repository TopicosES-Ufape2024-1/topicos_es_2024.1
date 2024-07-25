package br.edu.ufape.topicos.price.service;

import br.edu.ufape.topicos.price.service.exceptions.PriceAlreadyExistsException;
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
        return priceRepository.findById(id).orElse(null);
    }

    @Override
    public Price getPriceByProductId(Long productId) {
        return priceRepository.findByProductId(productId);
    }

    @Override
    public Price savePrice(Price price) {
        if(priceRepository.findByProductId(price.getProductId()) != null) {
            throw new PriceAlreadyExistsException("Price for product with id ["+ price.getProductId() + "] already exists.");
        }
        return priceRepository.save(price);
    }

    @Override
    public void deletePrice(Long id) {
        priceRepository.deleteById(id);
    }
}
