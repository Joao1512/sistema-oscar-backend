package com.company.sistema01backEnd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.company.sistema01backEnd.DTO.SapatoDTO;
import com.company.sistema01backEnd.DTO.SapatoFiltroDTO;
import com.company.sistema01backEnd.config.DetailService;
import com.company.sistema01backEnd.model.administracao.Sapato;
import com.company.sistema01backEnd.resources.SapatoResource;
import com.company.sistema01backEnd.services.SapatoService;


@SpringBootTest
public class SapatoResourceTest {
	
	@Mock
	private SapatoService service;


	@InjectMocks
	private SapatoResource resource;
	
	@Test
	public void deveriaRetornarListaSapatos() {
		
		SapatoFiltroDTO filtroMock = mockFiltro();
		List<SapatoDTO> sapatosMock = mockSapatos();
		Mockito.when(service.buscarTodosParaTabela(filtroMock))
			.thenReturn(sapatosMock);
		
		 ResponseEntity<List<SapatoDTO>>  sapatoResponse = resource.buscarTodosParaTabela(filtroMock);
		
		assertEquals(sapatosMock, sapatoResponse.getBody());
		assertEquals(HttpStatus.OK, sapatoResponse.getStatusCode());

	}
	
	@Test
	public void deveriaCadastrarSapato() {
		SapatoDTO sapato = mockSapatoDTO();
		Sapato novoSapatoCriado = mockNovoSapatoCriado(sapato);
		
		Mockito.when(service.salvar(sapato))
			.thenReturn(novoSapatoCriado);
		
		 ResponseEntity<Sapato> sapatoResponse = resource.cadastrar(sapato);
		
		assertEquals(novoSapatoCriado, sapatoResponse.getBody());
		assertEquals(HttpStatus.OK, sapatoResponse.getStatusCode());

	}
	
	@Test
	public void deveriaEditarSapato() throws Exception {
		SapatoDTO sapatoDTO = mockSapatoDTO();
		Sapato sapato = mockSapato();
		Sapato sapatoEditado = sapatoDTO.toEntityUpdate(sapato);
		
		Mockito.when(service.editar(sapatoDTO))
			.thenReturn(sapatoEditado);
		
		 ResponseEntity<Sapato> sapatoResponse = resource.editar(sapatoDTO);
		
		assertEquals(sapatoEditado, sapatoResponse.getBody());
		assertEquals(HttpStatus.OK, sapatoResponse.getStatusCode());

	}
	
	@Test
	public void deveriaBuscarSapatoPorId() throws Exception {
		Long idSapato = RandomUtils.nextLong();
		Sapato sapato = mockSapato();

		Mockito.when(service.buscarPorId(idSapato))
			.thenReturn(sapato);
		
		 ResponseEntity<SapatoDTO> sapatoResponse = resource.buscarPorId(idSapato);
		
		assertNotNull(sapatoResponse.getBody());
		assertEquals(HttpStatus.OK, sapatoResponse.getStatusCode());

	}
	

	
	private SapatoFiltroDTO mockFiltro() {
		SapatoFiltroDTO filtro = new SapatoFiltroDTO(
				RandomUtils.nextLong(),
				RandomUtils.nextLong(),
				RandomStringUtils.randomAlphabetic(6),
				RandomStringUtils.randomAlphabetic(6),
				RandomUtils.nextLong(),
				RandomStringUtils.randomAlphabetic(6),
				LocalDate.now(),
				RandomUtils.nextLong(),
				RandomStringUtils.randomAlphabetic(6));
		return filtro;
	}
	
	private List<SapatoDTO> mockSapatos() {
		List<SapatoDTO> sapatos = new ArrayList<>();
		SapatoDTO sapato = mockSapatoDTO();
		sapatos.add(sapato);
		
		return sapatos;
	}
	
	public SapatoDTO mockSapatoDTO() {
		 SapatoDTO sapato = new SapatoDTO(
				 RandomUtils.nextLong(),
				 RandomUtils.nextLong(),
				 RandomStringUtils.randomAlphabetic(6),
				 RandomStringUtils.randomAlphabetic(6),
				 RandomUtils.nextLong(),
				 RandomStringUtils.randomAlphabetic(6),
				 LocalDate.now(),
				 RandomUtils.nextLong(),
				 RandomStringUtils.randomAlphabetic(6));
		 
		 return sapato;
	}
	
	public Sapato mockNovoSapatoCriado(SapatoDTO sapatoDTO) {
		Sapato novoSapatoCriado = sapatoDTO.toEntityInsert();
		novoSapatoCriado.setId(RandomUtils.nextLong());
		return novoSapatoCriado;
	}
	
	public Sapato mockSapato() {
		Sapato novoSapato = new Sapato(
				 RandomUtils.nextLong(),
				 RandomUtils.nextLong(),
				 RandomStringUtils.randomAlphabetic(6),
				 RandomStringUtils.randomAlphabetic(6),
				 RandomUtils.nextLong(),
				 RandomStringUtils.randomAlphabetic(6),
				 LocalDate.now(),
				 RandomUtils.nextLong(),
				 RandomStringUtils.randomAlphabetic(6));
		
		return novoSapato;
	}
	
}
