package br.ufg.inf.projetopoo.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "admin")
@JsonIdentityInfo(scope = Admin.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Admin extends Usuario {

	public Admin() {

	}

	/**
	 * @param username
	 * @param senha
	 * @param email
	 * @param nome
	 * @param sobrenome
	 */
	public Admin(String username, String senha, String email, String nome, String sobrenome) {
		super(username, senha, email, nome, sobrenome);
		// TODO Auto-generated constructor stub
	}

}