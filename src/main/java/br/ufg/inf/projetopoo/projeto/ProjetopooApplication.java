package br.ufg.inf.projetopoo.projeto;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.ufg.inf.projetopoo.main.Principal;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "br.ufg.inf.projetopoo.*" })
@ComponentScan(basePackages = { "br.ufg.inf.projetopoo.*" })
@EntityScan(basePackages = { "br.ufg.inf.projetopoo.*" })
public class ProjetopooApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(ProjetopooApplication.class)
				.headless(false).run(args);
		Principal appFrame = context.getBean(Principal.class);
	}

}