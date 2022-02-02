package com.company.sistema01backEnd.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.sistema01backEnd.DTO.SapatoDTO;
import com.company.sistema01backEnd.DTO.SapatoFiltroDTO;
import com.company.sistema01backEnd.model.administracao.Sapato;
import com.company.sistema01backEnd.repositories.SapatoRepository;

@Service
public class SapatoService {

	@Autowired
	private SapatoRepository repository;
	
	@PersistenceContext
	private EntityManager manager;
	
	public Sapato buscarPorId(Long id) throws Exception {
		Optional<Sapato> sapato = repository.findById(id);
		return sapato.orElseThrow(() -> new Exception("sapato não encontrado!"));
	}
	
	public List<SapatoDTO> buscarTodosParaTabela(SapatoFiltroDTO filtro) {		
		TypedQuery<SapatoDTO> query = criarQuery(filtro);
		return query.getResultList();
	}
	
	private TypedQuery<SapatoDTO> criarQuery(SapatoFiltroDTO filtro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<SapatoDTO> criteria = builder.createQuery(SapatoDTO.class);
		Root<Sapato> root = criteria.from(Sapato.class);
		List<Predicate> predicates = new ArrayList<>();
		
		criteria.select(builder.construct(SapatoDTO.class,
		        root.get("id"),
		        root.get("tamanho"),
		        root.get("categoria"),
		        root.get("cor"),
		        root.get("preco"),
		        root.get("marca"),
		        root.get("dataCadastro"),
		        root.get("quantidadeEstoque"),
		        root.get("descricao")
		));
		
		
		if (filtro.getId() != null) {
		    predicates.add( builder.equal(root.get("id"), filtro.getId()));
		}
		
		if (filtro.getTamanho() != null) {
			predicates.add( builder.equal(root.get("tamanho"), filtro.getTamanho()));
		}
		
		if (!StringUtils.isEmpty(filtro.getCategoria())) {
			predicates.add( builder.like(
		            builder.lower(root.get("categoria")),
		            "%" + filtro.getCategoria().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(filtro.getCor())) {
			predicates.add( builder.like(
		            builder.lower(root.get("cor")),
		            "%" + filtro.getCor().toLowerCase() + "%"));
		}
		
		if (filtro.getPreco() != null) {
			predicates.add( builder.equal(root.get("preco"), filtro.getPreco()));
		}
		
		if (!StringUtils.isEmpty(filtro.getMarca())) {
			predicates.add( builder.like(
		            builder.lower(root.get("marca")),
		            "%" + filtro.getMarca().toLowerCase() + "%"));
		}
		
		if (filtro.getDataCadastro() != null) {
			predicates.add( builder.equal(root.get("dataCadastro"), filtro.getDataCadastro()));
		}
		
		if (filtro.getQuantidadeEstoque() != null) {
			predicates.add( builder.equal(root.get("quantidadeEstoque"), filtro.getQuantidadeEstoque()));
		}
		
		if (!StringUtils.isEmpty(filtro.getDescricao())) {
			predicates.add( builder.like(
		            builder.lower(root.get("descricao")),
		            "%" + filtro.getDescricao().toLowerCase() + "%"));
		}
		
		var predicatesGroup = predicates.toArray(new Predicate[predicates.size()]);
		
		criteria.where(predicatesGroup);
		TypedQuery<SapatoDTO> query = manager.createQuery(criteria);
		
		return query;
	}
	
	public Sapato salvar(SapatoDTO sapatoDTO) {
		Sapato sapato = sapatoDTO.toEntityInsert();
		return repository.save(sapato);
	}
	
	public void excluir(Long id) {
		repository.deleteById(id);
	}
	
	public Sapato editar(SapatoDTO sapatoDTO) throws Exception {
		Sapato sapato = buscarPorId(sapatoDTO.getId());
		sapato = sapatoDTO.toEntityUpdate(sapato);
		
		return repository.save(sapato);
	}
}
