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
public class LocadoraAdmin extends Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_locadora")
	private Locadora locadora;

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

	public LocadoraAdmin(Locadora locadora, String username, String senha, String email, String nome,
			String sobrenome) {
		super();
		this.locadora = locadora;
		this.username = username;
		this.senha = senha;
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
	}

	public LocadoraAdmin() {

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

	public Locadora getLocadora() {
		return locadora;
	}

	public void setLocadora(Locadora locadora) {
		this.locadora = locadora;
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