package com.company.sistema01backEnd.model.administracao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="funcionalidade")
@Data @NoArgsConstructor
public class Funcionalidade implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(generator = "funcionalidade_id_seq")
	@SequenceGenerator(name = "funcionalidade_id_seq", sequenceName = "funcionalidade_id_seq", allocationSize = 1)
	@Column(nullable=false)
	private Long id;
	
	@JoinColumn(name="nome", nullable = false)
	private String nome;
	
	@JoinColumn(name="regra", nullable = false)
	private String regra;
	
	public Funcionalidade(Long id, String nome, String regra) {
		super();
		this.id = id;
		this.nome = nome;
		this.regra = regra;
	}
	
	public Funcionalidade(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public Funcionalidade() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRegra() {
		return regra;
	}

	public void setRegra(String regra) {
		this.regra = regra;
	}
	
	
}
