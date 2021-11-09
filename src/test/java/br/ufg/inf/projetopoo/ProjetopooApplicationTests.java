package br.ufg.inf.projetopoo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import br.ufg.inf.projetopoo.models.Admin;
import br.ufg.inf.projetopoo.models.Locadora;
import br.ufg.inf.projetopoo.models.Localizacao;

class ProjetopooApplicationTests {

	@Test
	void test_create_admin() {
		Admin admin = new Admin("test.username", "test.password", "email@teste.com", "Teste", "Testando");
		assertNotNull(admin.getUsername());
	}

	@Test
	void test_create_locadora() {
		Localizacao localizacao = new Localizacao("endereco.teste", "bairro.teste", "cidade.teste", "estado.teste");
		assertNotNull(localizacao.getBairro());

		Locadora locadora = new Locadora("locadora.teste", "cnpj.teste", "telefone.teste", localizacao);
		assertNotNull(locadora.getNome());
	}
}
