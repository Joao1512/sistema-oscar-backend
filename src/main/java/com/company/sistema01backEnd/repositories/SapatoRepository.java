package com.company.sistema01backEnd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.sistema01backEnd.model.administracao.Sapato;

@Repository
public interface SapatoRepository extends JpaRepository<Sapato, Long>{

}
