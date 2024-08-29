package br.edu.ufage.topicos.catalogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
		scanBasePackages= {
				"br.edu.ufape.topicos", "br.edu.ufage.topicos.catalogo"
		}
)

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)
@EnableFeignClients
public class CatalogoApplication {
	private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(CatalogoApplication.class, args);
		checkBeansPresence(
				"keycloakJwtAuthenticationConverter", "ControladorCategoria", "catalogoApplication"
		);
	}

	private static void checkBeansPresence(String... beanNames) {
		for (String beanName : beanNames) {
			System.out.println("Is " + beanName + " in applicationContext: " +
					applicationContext.containsBean(beanName));
		}
	}

}
