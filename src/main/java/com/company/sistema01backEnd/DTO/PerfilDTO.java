package com.company.sistema01backEnd.DTO;

import java.util.List;
import java.util.stream.Collectors;

import com.company.sistema01backEnd.model.administracao.Perfil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class PerfilDTO {
	private Long id;
	private String nome;
	private List<PermissaoDTO> permissoes;
	
	public PerfilDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public PerfilDTO(){}
	
	public PerfilDTO(Long id, String nome, List<PermissaoDTO> permissoes) {
		this.id = id;
		this.nome = nome;
		this.permissoes = permissoes;
	}
	
	public PerfilDTO(Perfil perfil) {
		super();
		this.id = perfil.getId();
		this.nome = perfil.getNome();
		if (perfil.getPermissoes() != null)
			this.permissoes = perfil.getPermissoes().stream().map(obj -> new PermissaoDTO(obj)).collect(Collectors.toList());
		else
			this.permissoes = null;
	}
	
	public Perfil toEntityInsert() {
		return new Perfil(this.id, this.nome, this.permissoes != null ? this.permissoes.stream().map(obj -> obj.toEntityInsert()).collect(Collectors.toList()) : null);
	}
	
	public Perfil toEntityUpdate(Perfil perfil) {
		perfil.setNome(this.nome);
		return perfil;
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

	public List<PermissaoDTO> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<PermissaoDTO> permissoes) {
		this.permissoes = permissoes;
	}
	
	
}
