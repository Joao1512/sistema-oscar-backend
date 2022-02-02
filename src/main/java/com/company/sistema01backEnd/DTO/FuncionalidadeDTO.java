package com.company.sistema01backEnd.DTO;

import com.company.sistema01backEnd.model.administracao.Funcionalidade;

public class FuncionalidadeDTO {

	private Long id;
	private String nome;
	private String regra;
	
	public FuncionalidadeDTO(Funcionalidade funcionalidade) {
		this.id = funcionalidade.getId();
		this.nome = funcionalidade.getNome();
		this.regra = funcionalidade.getRegra();
	}
	
	public FuncionalidadeDTO() {
		
	}
	
	public Funcionalidade toEntityInsert(FuncionalidadeDTO funcionalidadeDTO) {
		return new Funcionalidade(this.id, this.nome);
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
