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
@Table(name = "veiculo")
@JsonIdentityInfo(
		scope = Veiculo.class,
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Veiculo extends Domain{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_locadora")
	private Locadora locadora;
	
	@Column(name = "codigo", unique = true, nullable = false, length = 50)
	private String codigo;
	
	@Column(name = "marca", nullable = false, length = 50)
	private String marca;
	
	@Column(name = "modelo", nullable = false, length = 50)
	private String modelo;
	
	@Column(name = "ano", nullable = false, length = 50)
	private String ano;
	
	@Column(name = "acessorios", nullable = false, length = 50)
	private String acessorios;
	
	@Column(name = "preco", nullable = false, length = 50)
	private Double preco;
	
	@Column(name = "categoria", nullable = false, length = 50)
	private String categoria;
	
}
