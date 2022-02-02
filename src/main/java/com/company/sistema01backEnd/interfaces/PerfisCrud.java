package com.company.sistema01backEnd.interfaces;

import java.util.List;
import java.util.Optional;

import com.company.sistema01backEnd.DTO.PerfilDTO;
import com.company.sistema01backEnd.model.administracao.Perfil;

public interface PerfisCrud {
	public Perfil buscarPorId(Long id) throws Exception;
	public void excluir(Long id);
	//TODO criar DTO para cadastro e edição de perfil
	public Perfil cadastrar(String perfil);
	public Perfil editar(String perfil);
	List<PerfilDTO> buscarTodosParaTabela();
}
