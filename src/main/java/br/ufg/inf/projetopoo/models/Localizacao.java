package br.ufg.inf.projetopoo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "localizacao")
@JsonIdentityInfo(scope = Localizacao.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Localizacao extends Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "endereco", unique = true, nullable = false, length = 50)
	private String endereco;

	@Column(name = "bairro", nullable = false, length = 50)
	private String bairro;

	@Column(name = "cidade", unique = true, nullable = false, length = 50)
	private String cidade;

	@Column(name = "estado", nullable = false, length = 50)
	private String estado;

	@OneToOne(mappedBy = "localizacao")
	private Locadora locadora;

	public Localizacao(String endereco, String bairro, String cidade, String estado) {
		super();
		this.endereco = endereco;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

	public Localizacao() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Locadora getLocadora() {
		return locadora;
	}

	public void setLocadora(Locadora locadora) {
		this.locadora = locadora;
	}

	@Override
	public String toString() {
		return "Localizacao [id=" + id + ", endereco=" + endereco + ", bairro=" + bairro + ", cidade=" + cidade
				+ ", estado=" + estado + ", locadora=" + locadora + "]";
	}

}