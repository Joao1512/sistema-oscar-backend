package com.company.sistema01backEnd.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.company.sistema01backEnd.DTO.PerfilDTO;
import com.company.sistema01backEnd.model.administracao.Perfil;


@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
	
	@Query("select new com.company.sistema01backEnd.DTO.PerfilDTO(p.id, p.nome)"
			+ " from Perfil p")
	List<PerfilDTO>buscarTodosParaTabela();

}
