package com.company.sistema01backEnd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.company.sistema01backEnd.model.administracao.Credencial;

@Repository
public interface CredencialRepository extends JpaRepository<Credencial, Long> {
	
	@Query("select c from Credencial c where LOWER(c.email) like LOWER(?1)")
	public Credencial findByLoginIgnoreCase(String email);

}
