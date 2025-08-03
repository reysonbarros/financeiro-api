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

import com.financeiro.api.model.Cidade;
import com.financeiro.api.repository.CidadeRepository;

@MockitoSettings
public class CidadeControllerTest {
	
	@Mock
	private CidadeRepository cidadeRepository;
	
	@Mock
	private List<Cidade> mockListCidade;
	
	@Spy
	@InjectMocks
	private CidadeController cidadeController;
	
	@Test
	void pesquisarTest () {
		// SETUP
		long estadoID = 1L;
		when(cidadeRepository.findByEstadoCodigo(estadoID)).thenReturn(mockListCidade);
		
		// CALL
		List<Cidade> response = cidadeController.pesquisar(estadoID);
		
		// ASSERTS
		assertEquals(mockListCidade,response);
		verify(cidadeRepository,times(1)).findByEstadoCodigo(estadoID);
	}
}