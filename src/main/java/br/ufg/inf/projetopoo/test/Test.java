package br.ufg.inf.projetopoo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.ufg.inf.projetopoo.models.Localizacao;
import br.ufg.inf.projetopoo.repositories.LocalizacaoRepository;

@Component
public class Test implements CommandLineRunner {

	@Autowired
	private LocalizacaoRepository localizacaoRep;

	@Override
	public void run(String... args) throws Exception {
		if(localizacaoRep.count() == 0) {
			
			Localizacao l = new Localizacao();
			
			l.setBairro("Jardim Califórnia");
			l.setCidade("Trindade");
			l.setEndereco("Rua Leopoldo, Qd: 11; Lt: 19");
			l.setEstado("Goiás");
			
			localizacaoRep.save(l);
		}
		
		localizacaoRep.findAll().stream()
				.forEach(localizacao -> System.out.println(
					"Estado: " + localizacao.getEstado() + 
					"\nCidade: " + localizacao.getCidade() + 
					"\nBairro: " + localizacao.getBairro() + 
					"\nEndereco " + localizacao.getEndereco()
				));
	}

}
