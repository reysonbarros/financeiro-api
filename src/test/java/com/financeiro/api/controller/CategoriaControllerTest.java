package com.financeiro.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoSettings;
import com.financeiro.api.model.Categoria;
import com.financeiro.api.service.CategoriaService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;

@MockitoSettings
public class CategoriaControllerTest {

	@Mock
	private ApplicationEventPublisher mockPublisher;

	@Mock
	private CategoriaService mockCategoriaService;
	@Mock
	private List<Categoria> mockListCategoria;

	@Mock
	private Categoria mockCategoria;

	@Mock
	private HttpServletResponse mockHttpServletResponse;
	
	@Spy
	@InjectMocks
	private CategoriaController categoriaController;


	@Test
	void listarTest() {
		
		// SETUP
		when(mockCategoriaService.listar()).thenReturn(mockListCategoria);
		
		// CALL
		List<Categoria> response = categoriaController.listar();
		
		// ASSERTS
		assertEquals(mockListCategoria, response);		
		verify(mockCategoriaService, times(1)).listar();
		
	}
	
	@Test
	void criarTest() {
		
		// SETUP
		when(mockCategoriaService.criar(mockCategoria)).thenReturn(mockCategoria);
				
		// CALL
		ResponseEntity<Categoria> response = categoriaController.criar(mockCategoria, mockHttpServletResponse);
		
		// ASSERTS
		assertEquals(ResponseEntity.created(null).body(mockCategoria), response);
		verify(mockCategoriaService, times(1)).criar(mockCategoria);
		
	}
	
	@Test
	void atualizarTest() {
		
		// SETUP
		Long id = 1L;
		when(mockCategoriaService.atualizar(id, mockCategoria)).thenReturn(mockCategoria);
				
		// CALL
		ResponseEntity<Categoria> response = categoriaController.atualizar(id,mockCategoria);
		
		// ASSERTS
		assertEquals(ResponseEntity.ok(mockCategoria), response);		
		verify(mockCategoriaService, times(1)).atualizar(id, mockCategoria);
		
	}
	
	@Test
	void removerTest() {
		
		// SETUP
		Long id = 1L;
				
		// CALL
		categoriaController.remover(id);
		
		// ASSERTS	
		verify(mockCategoriaService, times(1)).remover(id);
		
	}
	
	@Test
	void buscarPeloCodigoOkTest() {
		
		// SETUP
		Long id = 1L;
		when(mockCategoriaService.buscarPeloCodigo(id)).thenReturn(Optional.of(mockCategoria));
				
		// CALL
		ResponseEntity<?> response = categoriaController.buscarPeloCodigo(id);
		
		// ASSERTS
		assertEquals(ResponseEntity.ok(Optional.of(mockCategoria)), response);		
		verify(mockCategoriaService, times(1)).buscarPeloCodigo(id);
	}
	
	@Test
	void buscarPeloCodigoNotFoundTest() {
		
		// SETUP
		Long id = 1L;
		when(mockCategoriaService.buscarPeloCodigo(id)).thenReturn(null);
				
		// CALL
		ResponseEntity<?> response = categoriaController.buscarPeloCodigo(id);
		
		// ASSERTS
		assertEquals(ResponseEntity.notFound().build(), response);		
		verify(mockCategoriaService, times(1)).buscarPeloCodigo(id);
	}

}
