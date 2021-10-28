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
@JsonIdentityInfo(
		scope = Localizacao.class,
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Locadora extends Domain{
	
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
	
	
}
