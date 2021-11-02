package br.ufg.inf.projetopoo.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = { "br.ufg.inf.projetopoo.*" })
@ComponentScan(basePackages = { "br.ufg.inf.projetopoo.*" })
@EntityScan(basePackages = { "br.ufg.inf.projetopoo.*" })
public class ProjetopooApplication {
	

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProjetopooApplication.class, args);
	}

}
