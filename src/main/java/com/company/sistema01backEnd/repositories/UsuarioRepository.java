package com.company.sistema01backEnd.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.company.sistema01backEnd.model.administracao.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query("Select new com.company.sistema01backEnd.model.administracao.Usuario(u.id, u.nome, u.email, u.credencial) from Usuario u")
	public List<Usuario> buscarTodos();
	
	@Query("Select new com.company.sistema01backEnd.model.administracao.Usuario(u.id, u.nome, u.email, u.credencial) from Usuario u"
			+ " where u.credencial.id = :idCredencial")
	public Usuario buscarPorCredencial(Long idCredencial);
}
