package br.ufg.inf.projetopoo.projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.ufg.inf.projetopoo.repositories.VeiculoRepository;

@SpringBootApplication
public class ProjetopooApplication {

	@Autowired
	VeiculoRepository adminRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetopooApplication.class, args);
	}

}
