package br.edu.ufape.topicos.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(
		scanBasePackages = { "br.edu.ufape.topicos", "br.edu.ufape.topicos.inventory" }
)
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)
public class InventoryApplication {
	private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		applicationContext = (ApplicationContext) SpringApplication.run(InventoryApplication.class, args);
		checkBeansPresence(
				"keycloakJwtAuthenticationConverter", "InventoryController", "InventoryApplication");
	}

	private static void checkBeansPresence(String... beans) {
		for (String beanName : beans) {
			System.out.println("Is " + beanName + " in ApplicationContext: " +
					applicationContext.containsBean(beanName));
		}
	}
}
