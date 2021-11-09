package br.ufg.inf.projetopoo.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "cliente")
@JsonIdentityInfo(scope = Cliente.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Cliente extends Usuario {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_veiculo", referencedColumnName = "id")
	private Veiculo veiculo;

	public Cliente() {

	}

	/**
	 * @param username
	 * @param senha
	 * @param email
	 * @param nome
	 * @param sobrenome
	 */
	public Cliente(String username, String senha, String email, String nome, String sobrenome) {
		super(username, senha, email, nome, sobrenome);
		// TODO Auto-generated constructor stub
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

}