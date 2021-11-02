package br.ufg.inf.projetopoo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufg.inf.projetopoo.models.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
