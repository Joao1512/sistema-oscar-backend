package com.company.sistema01backEnd.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.company.sistema01backEnd.model.administracao.Funcionalidade;
import com.company.sistema01backEnd.repositories.FuncionalidadeRepository;

@Service
public class FuncionalidadeService {
	
	@Autowired
	private FuncionalidadeRepository repository;
	
	public List<Funcionalidade> buscarFuncionalidades() {
		return repository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
	}
	
	public Optional<Funcionalidade> buscarPorId(Long id) {
		Optional<Funcionalidade> obj = repository.findById(id);
		return obj;
	}
	
	public List<Funcionalidade> buscarTodasExceto(List<Long> funcionalidades) {
		return repository.findByIdNotIn(funcionalidades);
	};
}