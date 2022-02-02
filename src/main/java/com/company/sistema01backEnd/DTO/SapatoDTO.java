package com.company.sistema01backEnd.DTO;

import java.time.LocalDate;

import com.company.sistema01backEnd.model.administracao.Sapato;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;


public class SapatoDTO {
	private Long id;
	
	private Long tamanho;
	
	private String categoria;
	
	private String cor;
	
	private Long preco;
	
	private String marca;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
	private LocalDate dataCadastro;

	private Long quantidadeEstoque;

	private String descricao;
	
	public Sapato toEntityInsert() {
		return new Sapato(this.id, this.tamanho, this.categoria, this.cor, this.preco, this.marca, this.dataCadastro, this.quantidadeEstoque, this.descricao);
	}
	
	public Sapato toEntityUpdate(Sapato sapato) {
		sapato.setTamanho(this.tamanho);
		sapato.setCategoria(this.categoria);
		sapato.setCor(this.cor);
		sapato.setPreco(this.preco);
		sapato.setMarca(this.marca);
		sapato.setDataCadastro(this.dataCadastro);
		sapato.setQuantidadeEstoque(this.quantidadeEstoque);
		sapato.setDescricao(this.descricao);
		
		return sapato;
	}
	

	public SapatoDTO(Long id, Long tamanho, String categoria, String cor, Long preco, String marca,
			LocalDate dataCadastro, Long quantidadeEstoque, String descricao) {
		super();
		this.id = id;
		this.tamanho = tamanho;
		this.categoria = categoria;
		this.cor = cor;
		this.preco = preco;
		this.marca = marca;
		this.dataCadastro = dataCadastro;
		this.quantidadeEstoque = quantidadeEstoque;
		this.descricao = descricao;
	}
	
	public SapatoDTO(Sapato sapato) {
		this.id = sapato.getId();
		this.tamanho = sapato.getTamanho();
		this.categoria = sapato.getCategoria();
		this.cor = sapato.getCor();
		this.preco = sapato.getPreco();
		this.marca = sapato.getMarca();
		this.dataCadastro = sapato.getDataCadastro();
		this.quantidadeEstoque = sapato.getQuantidadeEstoque();
		this.descricao = sapato.getDescricao();
	}
	
	public SapatoDTO() {};	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}

	public Long getTamanho() {
		return tamanho;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Long getPreco() {
		return preco;
	}

	public void setPreco(Long preco) {
		this.preco = preco;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Long getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Long quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
