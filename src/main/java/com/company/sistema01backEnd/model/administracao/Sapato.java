package com.company.sistema01backEnd.model.administracao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="sapatos")
@Data @NoArgsConstructor @Builder
public class Sapato {

	@Id @GeneratedValue(generator = "sapato_id_seq")
	@SequenceGenerator(name = "sapato_id_seq", sequenceName = "sapato_id_seq", allocationSize = 1)
	@Column(nullable=false)
	private Long id;
	
	@Column(name = "tamanho")
	private Long tamanho;
	
	@Column(name = "categoria")
	private String categoria;
	
	@Column(name = "cor")
	private String cor;
	
	@Column(name = "preco")
	private Long preco;
	
	@Column(name = "marca")
	private String marca;
	
	@Column(name = "data_cadastro")
	@JsonFormat(pattern = "yyyy/MM/dd")
	private LocalDate dataCadastro;
	
	@Column(name = "quantidade_estoque")
	private Long quantidadeEstoque;
	
	@Column(name = "descricao")
	private String descricao;

	public Sapato(Long id, Long tamanho, String categoria, String cor, Long preco, String marca,
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
	
	public Sapato() {}

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
