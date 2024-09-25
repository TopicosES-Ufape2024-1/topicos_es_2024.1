package br.edu.ufape.topicos.price;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"br.edu.ufape.topicos.auth", "br.edu.ufape.topicos.price"})
public class PriceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceApplication.class, args);
	}

}
