package com.company.sistema01backEnd.resources;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.company.sistema01backEnd.DTO.MeuPerfilDTO;
import com.company.sistema01backEnd.DTO.UsuarioDTO;
import com.company.sistema01backEnd.model.administracao.Usuario;
import com.company.sistema01backEnd.services.UsuarioService;

@CrossOrigin()
@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@Secured("ROLE_DELETAR_USUARIO")
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@Secured("ROLE_CADASTRAR_USUARIO")
	@PostMapping("/cadastrar")
	public ResponseEntity<Object> cadastrarUsuario(@RequestBody UsuarioDTO usuario) {
		Usuario usuarioCadastrado = service.inserirUsuario(usuario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioCadastrado.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@Secured("ROLE_VISUALIZAR_USUARIO")
	@GetMapping("/listarUsuarios")
	public List<Usuario> listarUsuarios() {
		List<Usuario> usuarios = service.listarUsuarios();
		return usuarios;
	}
	
	@GetMapping("/buscarPerfilUsuarioLogado")
	public ResponseEntity<MeuPerfilDTO> buscarPerfilUsuarioLogado() throws Exception {
		MeuPerfilDTO meuPerfil = service.buscarPerfilUsuarioLogado();
		return ResponseEntity.ok(meuPerfil);
	}
}
