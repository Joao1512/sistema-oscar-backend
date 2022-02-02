package com.company.sistema01backEnd.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.company.sistema01backEnd.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import com.company.sistema01backEnd.DTO.MeuPerfilDTO;
import com.company.sistema01backEnd.DTO.UsuarioDTO;
import com.company.sistema01backEnd.model.administracao.Credencial;
import com.company.sistema01backEnd.model.administracao.Usuario;
import com.company.sistema01backEnd.repositories.CredencialRepository;

import util.SenhaUtil;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private CredencialRepository credencialRepository;
	
	
	public Optional<Usuario> buscarPorId(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj;
	}
	
	public Usuario inserirUsuario(UsuarioDTO usuarioDto) {
		Usuario usuario = usuarioDto.toEntityInsert();
		usuario.getCredencial().setSenha(SenhaUtil.criptografarSHA2(usuario.getCredencial().getSenha()));
		salvar(usuario);
		return usuario;
	}
	
	public Usuario salvar(Usuario usuario) {
		Credencial existeUsuario = credencialRepository.findByLoginIgnoreCase(usuario.getEmail());
		if (existeUsuario != null) {
			repository.save(usuario);
		}
		else {
			throw new PersistenceException("Já existe usuário vinculado ao e-mail: "+usuario.getEmail());
		}
		return usuario;
	}
	
	public List<Usuario> listarUsuarios() {
		return repository.findAll();
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
	
	public MeuPerfilDTO buscarPerfilUsuarioLogado() throws Exception {
		Usuario usuario = buscarUsuarioLogado();
		return new MeuPerfilDTO(usuario.getEmail(), usuario.getNome());
	}
	
	public Usuario buscarUsuarioLogado() throws Exception {
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario usuarioLogado  = buscarPorEmailCredencial(user);
		return usuarioLogado;
	}
	
	public Usuario buscarPorEmailCredencial(String email) throws Exception {
		try {
			return buscarUsuarioPorEmail(email);
		} catch (Exception ex) {
			throw new Exception("Usuário não encontrado!");
		}
	}
	
	private Usuario buscarUsuarioPorEmail(String email) {
		Usuario usuario = new Usuario();
		Credencial credencial = buscarCredencialPorEmail(email);
		usuario = repository.buscarPorCredencial(credencial.getId());
		return usuario;		
	}
	
	public Credencial buscarCredencialPorEmail(String email) {
		return credencialRepository.findByLoginIgnoreCase(email);
	}
}
