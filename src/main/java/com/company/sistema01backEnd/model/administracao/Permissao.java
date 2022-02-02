package com.company.sistema01backEnd.model.administracao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Permissao")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Permissao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id  @GeneratedValue(generator = "permissao_id_seq")
	@SequenceGenerator(name = "permissao_id_seq", sequenceName = "permissao_id_seq", allocationSize = 1)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="funcionalidade", nullable=false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Funcionalidade funcionalidade;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="perfil")
	@JsonBackReference()
	private Perfil perfil;
		
	private Boolean habilitada;
	
	public Permissao(Long id, Funcionalidade funcionalidade,  Boolean habilitada) {
		super();
		this.id = id;
		this.funcionalidade = funcionalidade;
		this.habilitada = habilitada;
	}
	
	public Permissao(Long id, Perfil perfil, Funcionalidade funcionalidade,  Boolean habilitada) {
		super();
		this.id = id;
		this.perfil = perfil;
		this.funcionalidade = funcionalidade;
		this.habilitada = habilitada;
	}

	public Permissao() {
		super();
	}

	public Boolean getHabilitada() {
		return habilitada;
	}

	public void setHabilitada(Boolean habilitada) {
		this.habilitada = habilitada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Funcionalidade getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public String calculaRegraDePermissaoHabilitada() {
		StringBuilder sb = new StringBuilder();		
		if (habilitada) {
			sb.append("ROLE_");
			sb.append(funcionalidade.getRegra());
		}		
		return sb.toString();
	}	
	
}
