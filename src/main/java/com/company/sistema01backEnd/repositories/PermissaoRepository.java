package com.company.sistema01backEnd.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.company.sistema01backEnd.model.administracao.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>{

//	@Query("select new com.company.sistema01backEnd.DTO.PermissaoDTO(perm.id, f.nome, perm.habilitada) "
//			+ "from Permissao perm "
//			+ "inner join Perfil p "
//			+ "inner join Funcionalidade f "
//			+ "where p.id = :idPerfil")
//	List<Permissao> buscarPermissoesPorIdPerfil(Long idPerfil);
	
	@Query("select perm from Permissao perm")
	List<Permissao> buscarPermissoesPorIdPerfil(Long idPerfil);
}
