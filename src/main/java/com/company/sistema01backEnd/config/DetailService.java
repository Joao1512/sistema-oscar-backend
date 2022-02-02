package com.company.sistema01backEnd.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.company.sistema01backEnd.model.administracao.Credencial;
import com.company.sistema01backEnd.model.administracao.Perfil;
import com.company.sistema01backEnd.model.administracao.Permissao;
import com.company.sistema01backEnd.repositories.CredencialRepository;

@Service
public class DetailService implements UserDetailsService {
	
	private Logger logger = LoggerFactory.getLogger(DetailService.class);
	
	@Autowired
	private CredencialRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		email = email.toLowerCase();
		Credencial credencial = findByLogin(email);
		if (credencial == null) {
			logger.error("Erro em loadUserByUsername(): Usuário'"+email+"' não existe");
			throw new UsernameNotFoundException("Usuário '"+email+"' não existe!");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Perfil perfil: credencial.getListaPerfil()) {
			for (Permissao permissao: perfil.getPermissoes()) {
				if (permissao.getHabilitada()) {
					authorities.add(new SimpleGrantedAuthority(permissao.calculaRegraDePermissaoHabilitada()));
				}
			}
		}
		
		return new User(credencial.getEmail(), credencial.getSenha(), true, true, true, true, authorities);
	}

	public Credencial findByLogin(String email) {
		return repository.findByLoginIgnoreCase(email);
	}
}
