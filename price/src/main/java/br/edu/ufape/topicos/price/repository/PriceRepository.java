package br.edu.ufape.topicos.price.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.topicos.price.model.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
	Price findByProductId(Long productId);
}