package br.ufg.inf.projetopoo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "locadora")
@JsonIdentityInfo(scope = Localizacao.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Locadora extends Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome", unique = true, nullable = false, length = 50)
	private String nome;

	@Column(name = "cnpj", nullable = false, length = 50)
	private String cnpj;

	@Column(name = "telefone", unique = true, nullable = false, length = 50)
	private String telefone;

	@OneToMany(mappedBy = "locadora", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Usuario> usuarios;

	@OneToMany(mappedBy = "locadora", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Admin> admins;

	@OneToMany(mappedBy = "locadora", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<LocadoraAdmin> LocadoraAdmins;

	@OneToMany(mappedBy = "locadora", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Cliente> clientes;

	@OneToMany(mappedBy = "locadora", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Veiculo> veiculos;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_localizacao", referencedColumnName = "id")
	private Localizacao localizacao;

	public Locadora(String nome, String cnpj, String telefone, List<Usuario> usuarios, List<Admin> admins,
			List<LocadoraAdmin> locadoraAdmins, List<Cliente> clientes, List<Veiculo> veiculos,
			Localizacao localizacao) {
		super();
		this.nome = nome;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.usuarios = usuarios;
		this.admins = admins;
		LocadoraAdmins = locadoraAdmins;
		this.clientes = clientes;
		this.veiculos = veiculos;
		this.localizacao = localizacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}

	public List<LocadoraAdmin> getLocadoraAdmins() {
		return LocadoraAdmins;
	}

	public void setLocadoraAdmins(List<LocadoraAdmin> locadoraAdmins) {
		LocadoraAdmins = locadoraAdmins;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}
}
