package br.ufg.inf.projetopoo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufg.inf.projetopoo.models.Locadora;

@Repository
public interface LocadoraRepository extends JpaRepository<Locadora, Integer> {

}