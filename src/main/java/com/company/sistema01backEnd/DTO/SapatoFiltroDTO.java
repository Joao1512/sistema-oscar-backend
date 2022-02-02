package com.company.sistema01backEnd.DTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SapatoFiltroDTO {
	
private Long id;
	
	private Long tamanho;
	
	private String categoria;
	
	private String cor;
	
	private Long preco;
	
	private String marca;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataCadastro;

	private Long quantidadeEstoque;

	private String descricao;
	

	public SapatoFiltroDTO(Long id, Long tamanho, String categoria, String cor, Long preco, String marca,
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
	
	public SapatoFiltroDTO() {};	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTamanho() {
		return tamanho;
	}

	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
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
