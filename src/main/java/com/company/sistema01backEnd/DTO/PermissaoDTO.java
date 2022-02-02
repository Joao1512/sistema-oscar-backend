package com.company.sistema01backEnd.DTO;

import com.company.sistema01backEnd.model.administracao.Permissao;

public class PermissaoDTO {

	private Long id;
	private String nomeFuncionalidade;
	private FuncionalidadeDTO funcionalidade;
	private boolean habilitada;
	
	public PermissaoDTO(Long id, String nomeFuncionalidade, boolean habilitada) {
		this.id = id;
		this.nomeFuncionalidade = nomeFuncionalidade;
		this.habilitada = habilitada;
	}
	
	public PermissaoDTO(Permissao permissao) {
		this.id = permissao.getId();
		this.funcionalidade = new FuncionalidadeDTO(permissao.getFuncionalidade());
		this.habilitada = permissao.getHabilitada();
	}
	
	public Permissao toEntityInsert() {
		return new Permissao(null, this.funcionalidade.toEntityInsert(this.funcionalidade), this.isHabilitada());
	}
	
	public Permissao toEntityUpdate(Permissao permissao) {
		permissao.setHabilitada(this.habilitada);
		return permissao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFuncionalidade() {
		return nomeFuncionalidade;
	}

	public void setNomeFuncionalidade(String nomeFuncionalidade) {
		this.nomeFuncionalidade = nomeFuncionalidade;
	}

	public boolean isHabilitada() {
		return habilitada;
	}

	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}

	public FuncionalidadeDTO getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(FuncionalidadeDTO funcionalidade) {
		this.funcionalidade = funcionalidade;
	}
	
	
	
	
}
