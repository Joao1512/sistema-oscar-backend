package com.company.sistema01backEnd.DTO;


public class MeuPerfilDTO {

	private String email;
	private String nome;
	
	public MeuPerfilDTO() {}
	
	public MeuPerfilDTO(String email, String nome) {
		super();
		this.email = email;
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
