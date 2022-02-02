package com.company.sistema01backEnd.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.sistema01backEnd.DTO.FuncionalidadeDTO;
import com.company.sistema01backEnd.DTO.PerfilDTO;
import com.company.sistema01backEnd.model.administracao.Perfil;
import com.company.sistema01backEnd.services.PerfisService;

@RestController
@CrossOrigin()
@RequestMapping("/perfis")
public class PerfisResource {
	
	@Autowired
	private PerfisService service;
	
	@Secured("ROLE_EXCLUIR_PERFIL")
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
		service.excluir(id);
		return ResponseEntity.ok().build();
	}

	@Secured("ROLE_CADASTRAR_PERFIL")
	@PostMapping("/cadastrar")
	public ResponseEntity<Perfil> cadastrar(@RequestBody PerfilDTO perfil) {
		Perfil novoPerfilCadastrado = service.cadastrar(perfil);
		return ResponseEntity.ok(novoPerfilCadastrado);
	}
	
	@Secured("ROLE_EDITAR_PERFIL")
	@PostMapping("/editar")
	public ResponseEntity<Void> editar(@RequestBody PerfilDTO perfilDTO) throws Exception {
		service.editar(perfilDTO);
		return ResponseEntity.ok().build();
	}

	@Secured("ROLE_VISUALIZAR_PERFIL")
	@GetMapping("/buscarPerfisParaTabela")
	public ResponseEntity<List<PerfilDTO>> buscarTodosParaTabela() { 
		List<PerfilDTO> perfis = service.buscarTodosParaTabela();
		return ResponseEntity.ok(perfis);
	}
	
	@Secured("ROLE_VISUALIZAR_PERFIL")
	@GetMapping("/buscarPerfilPorId/{id}")
	public ResponseEntity<PerfilDTO> buscarPerfilPorId(@PathVariable("id") Long id) throws Exception {
		Perfil perfil = service.buscarPerfilPorId(id);
		PerfilDTO perfilDTO = new PerfilDTO(perfil);
		return ResponseEntity.ok(perfilDTO);
	}
	
	@GetMapping("/buscarFuncionalidades")
	public ResponseEntity<List<FuncionalidadeDTO>> buscarFuncionalidades() {
		List<FuncionalidadeDTO> funcionalidades = service.buscarFuncionalidades();
		return ResponseEntity.ok(funcionalidades);
	}
}
