package com.company.sistema01backEnd.DTO;

import com.company.sistema01backEnd.model.administracao.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UsuarioDTO {
	
	private Long id;
	
	private String nome;
	
	private String email;
	
	private CredencialDTO credencial;
	
	public Usuario toEntityInsert() {
		return new Usuario(null, this.email, this.nome, this.credencial.toEntityInsert());
	}	
	
	public Usuario toEntityUpdate(Usuario userRef) {
		userRef.setCredencial(this.credencial.toEntityUpdate(userRef.getCredencial()));
		userRef.setNome(this.nome);
		userRef.setEmail(this.email);
		return  userRef;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CredencialDTO getCredencial() {
		return credencial;
	}

	public void setCredencial(CredencialDTO credencial) {
		this.credencial = credencial;
	}
	
	
}
