package com.company.sistema01backEnd.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.sistema01backEnd.DTO.FuncionalidadeDTO;
import com.company.sistema01backEnd.DTO.PerfilDTO;
import com.company.sistema01backEnd.DTO.PermissaoDTO;
import com.company.sistema01backEnd.model.administracao.Funcionalidade;
import com.company.sistema01backEnd.model.administracao.Perfil;
import com.company.sistema01backEnd.model.administracao.Permissao;
import com.company.sistema01backEnd.repositories.PerfilRepository;

@Service
public class PerfisService{

	@Autowired
	private PerfilRepository repository;
	
	@Autowired
	private FuncionalidadeService funcionalidadeService;

	public Perfil buscarPorId(Long id) throws Exception {
		Optional<Perfil> perfil = repository.findById(id);
		return perfil.orElseThrow(() -> new Exception("Perfil não encontrado!"));
	}

	public void excluir(Long id) {
		repository.deleteById(id);
	}

	public Perfil cadastrar(PerfilDTO perfilDTO) {
		Perfil perfil = perfilDTO.toEntityInsert();
		colocarPerfilNasPermissoes(perfil);
		return repository.save(perfil);
	}
	
	public void colocarPerfilNasPermissoes(Perfil perfil) {
		for (Permissao permissao: perfil.getPermissoes()){
			permissao.setPerfil(perfil);
		}
	}

	public Perfil editar(PerfilDTO perfilDTO) throws Exception {
		Perfil perfil = buscarPorId(perfilDTO.getId());
		perfil = perfilDTO.toEntityUpdate(perfil);
		for(PermissaoDTO permissaoDTO : perfilDTO.getPermissoes()) {
			if (permissaoDTO.getId() != null ) {
				Permissao permissaoRef = perfil.getPermissoes().stream().filter(p -> p.getId().equals(permissaoDTO.getId())).findFirst().get();
				permissaoDTO.toEntityUpdate(permissaoRef);
			}
			else {
				Permissao permissao = permissaoDTO.toEntityInsert();
				permissao.setPerfil(perfil);
				perfil.getPermissoes().add(permissao);
			}
		}
		return repository.save(perfil);
	}

	public List<PerfilDTO> buscarTodosParaTabela() {
		return repository.buscarTodosParaTabela();
	}
	
	
	public Perfil buscarPerfilPorId(Long id) throws Exception {
		Perfil perfil = montarPerfilComListaPermissoes(id);
		return organizarPermissoesPerfil(perfil);
	}
	
	public Perfil montarPerfilComListaPermissoes(Long id) throws Exception {
		List<Funcionalidade> funcionalidadesDesabilitadas = buscarFuncionalidadesDesabilitadas(id);
		Perfil perfil = buscarPorId(id);
		funcionalidadesDesabilitadas.stream().forEach(funcionalidade ->			
		perfil.getPermissoes().add(new Permissao(null, perfil, funcionalidade, false))
		);
		return perfil;
	}
	
	public List<Funcionalidade> buscarFuncionalidadesDesabilitadas(Long id) throws Exception {
		List<Long> idsFuncionalidadesDoPerfil = buscarIdsFuncionalidesDoPerfil(buscarPorId(id));
		return funcionalidadeService.buscarTodasExceto(idsFuncionalidadesDoPerfil);
	}
	
	public List<Long> buscarIdsFuncionalidesDoPerfil(Perfil perfil) {
		List<Long> idsFuncionalidadesDoPerfil = new ArrayList<Long>();
		perfil.getPermissoes().forEach(permissao -> 
			idsFuncionalidadesDoPerfil.add(permissao.getFuncionalidade().getId())
		);
		return idsFuncionalidadesDoPerfil;
	}
	
	
	public Perfil organizarPermissoesPerfil(Perfil perfil) {
		perfil.getPermissoes().sort((p1, p2) -> p1.getFuncionalidade().getNome() .compareTo(p2.getFuncionalidade().getNome()));
		return perfil;
	}
	
	public List<FuncionalidadeDTO> buscarFuncionalidades() {
		List<Funcionalidade> funcionalidades = funcionalidadeService.buscarFuncionalidades();
		List<FuncionalidadeDTO> funcionalidadesDTO = funcionalidades.stream().map(obj -> new FuncionalidadeDTO(obj)).collect(Collectors.toList());
		return funcionalidadesDTO;
	}
}
