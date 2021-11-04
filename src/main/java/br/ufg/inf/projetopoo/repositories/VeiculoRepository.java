package br.ufg.inf.projetopoo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufg.inf.projetopoo.models.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {

	List<Veiculo> findByMarca(String marca);

	List<Veiculo> findByModelo(String modelo);

	List<Veiculo> findByAno(String ano);
}