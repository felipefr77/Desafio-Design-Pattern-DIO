package one.digitalinnovation.patterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
* Projeto Spring Boot gerado via Spring Initializr.
* Starters selecionados:
* - Spring Data JPA
* - Spring Web
* - H2 Database
* - OpenFeign
* 
* @author felipefruhauf
*/

@EnableFeignClients
@SpringBootApplication
public class PadroesProjetoSpringApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PadroesProjetoSpringApplication.class, args);
	}
}

