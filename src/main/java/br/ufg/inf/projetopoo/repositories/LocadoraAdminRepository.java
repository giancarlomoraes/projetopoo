package br.ufg.inf.projetopoo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufg.inf.projetopoo.models.LocadoraAdmin;

@Repository
public interface LocadoraAdminRepository extends JpaRepository<LocadoraAdmin, Integer>{

}
