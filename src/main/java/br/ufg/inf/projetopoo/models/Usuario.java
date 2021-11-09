package br.ufg.inf.projetopoo.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@MappedSuperclass
@JsonIdentityInfo(scope = Usuario.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Usuario extends Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "username", unique = true, nullable = false, length = 50)
	private String username;

	@Column(name = "senha", nullable = false, length = 50)
	private String senha;

	@Column(name = "email", unique = true, nullable = false, length = 50)
	private String email;

	@Column(name = "nome", nullable = false, length = 50)
	private String nome;

	@Column(name = "sobrenome", nullable = false, length = 50)
	private String sobrenome;

	public Usuario() {

	}
	
	

	/**
	 * @param username
	 * @param senha
	 * @param email
	 * @param nome
	 * @param sobrenome
	 */
	public Usuario(String username, String senha, String email, String nome, String sobrenome) {
		super();
		this.username = username;
		this.senha = senha;
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

}