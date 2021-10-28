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
@JsonIdentityInfo(
		scope = Localizacao.class,
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Localizacao extends Domain{
	
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
    private Localizacao localizacao		;

}
