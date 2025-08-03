package com.financeiro.api.controller;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoSettings;

import com.financeiro.api.model.Estado;
import com.financeiro.api.repository.EstadoRepository;

@MockitoSettings
public class EstadoControllerTest {
	
	@Mock
	private EstadoRepository mockEstadoRepository;
	
	@Mock
	private List<Estado> mockListEstado;
	
	@Spy
	@InjectMocks
	private EstadoController estadoController;

	@Test
	void listarTest() {
		// SETUP
		when(mockEstadoRepository.findAll()).thenReturn(mockListEstado);
		
		// CALL
		List<Estado> response = estadoController.listar();
		
		// ASSERTS
		assertEquals(mockListEstado, response);
		verify(mockEstadoRepository, times(1)).findAll();
	}
	
}
