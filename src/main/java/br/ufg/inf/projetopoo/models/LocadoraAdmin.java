package br.ufg.inf.projetopoo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "locadoraadmin")
@JsonIdentityInfo(scope = LocadoraAdmin.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LocadoraAdmin extends Usuario {

	@ManyToOne
	@JoinColumn(name = "id_locadora")
	private Locadora locadora;

	public LocadoraAdmin(Locadora locadora, String username, String senha, String email, String nome,
			String sobrenome) {
		super(username, senha, email, nome, sobrenome);
		this.locadora = locadora;
	}
	
	

	/**
	 * @param username
	 * @param senha
	 * @param email
	 * @param nome
	 * @param sobrenome
	 */
	public LocadoraAdmin(String username, String senha, String email, String nome, String sobrenome) {
		super(username, senha, email, nome, sobrenome);
		// TODO Auto-generated constructor stub
	}



	public LocadoraAdmin() {

	}

	public Locadora getLocadora() {
		return locadora;
	}

	public void setLocadora(Locadora locadora) {
		this.locadora = locadora;
	}

	
}