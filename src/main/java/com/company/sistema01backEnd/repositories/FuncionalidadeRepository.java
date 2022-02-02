package com.company.sistema01backEnd.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.sistema01backEnd.model.administracao.Funcionalidade;

@Repository
public interface FuncionalidadeRepository extends JpaRepository<Funcionalidade, Long>{
    List<Funcionalidade> findByIdNotIn(List<Long> ids);
}