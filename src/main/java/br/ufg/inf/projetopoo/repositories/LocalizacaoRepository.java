package br.ufg.inf.projetopoo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufg.inf.projetopoo.models.Localizacao;

@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, Integer> {

}