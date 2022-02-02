package com.company.sistema01backEnd.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.sistema01backEnd.model.administracao.Permissao;
import com.company.sistema01backEnd.repositories.PermissaoRepository;

@Service
public class PermissaoService {
	
	@Autowired
	private PermissaoRepository repository;

	public List<Permissao> buscarFuncionalidadesPorIdPerfil(Long idPerfil) {
		return repository.buscarPermissoesPorIdPerfil(idPerfil);
	}
}
