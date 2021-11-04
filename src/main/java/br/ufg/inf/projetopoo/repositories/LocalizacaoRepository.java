package br.ufg.inf.projetopoo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufg.inf.projetopoo.models.Localizacao;

@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, Integer> {

	List<Localizacao> findByEndereco(String endereco);

	List<Localizacao> findByBairro(String bairro);

	List<Localizacao> findByCidade(String cidade);

	List<Localizacao> findByEstado(String estado);

}