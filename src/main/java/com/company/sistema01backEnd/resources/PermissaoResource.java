package com.company.sistema01backEnd.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.sistema01backEnd.model.administracao.Permissao;
import com.company.sistema01backEnd.services.PermissaoService;

@CrossOrigin
@RestController
@RequestMapping("/permissao")
public class PermissaoResource {
	
	@Autowired
	private PermissaoService service;
	
	@GetMapping("/buscarFuncionalidadesPorIdPerfil/{id}")
	public List<Permissao> buscarFuncionalidadesPorIdPerfil(@PathVariable("id") Long id) {
		return service.buscarFuncionalidadesPorIdPerfil(id);
	}
}
