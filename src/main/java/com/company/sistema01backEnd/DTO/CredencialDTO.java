package com.company.sistema01backEnd.DTO;



import java.util.List;

import com.company.sistema01backEnd.model.administracao.Credencial;
import com.company.sistema01backEnd.model.administracao.Perfil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CredencialDTO {

	private Long id;
	
	private String email;
	
	private String senha;
	
	private List<Perfil> listaPerfil;
	
	public Credencial toEntityInsert() {
		Credencial credencial = new Credencial();
		credencial.setEmail(this.email);
		credencial.setSenha(this.senha);
		return credencial;
	}
	
	public Credencial toEntityUpdate(Credencial credencialRef) {
		credencialRef.setEmail(this.email);
		return credencialRef;
	}
}
