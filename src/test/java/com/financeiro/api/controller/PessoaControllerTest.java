package com.financeiro.api.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.financeiro.api.event.RecursoCriadoEvent;
import com.financeiro.api.model.Pessoa;
import com.financeiro.api.repository.PessoaRepository;
import com.financeiro.api.repository.filter.PessoaFilter;
import com.financeiro.api.service.*;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@MockitoSettings
public class PessoaControllerTest {
     // variables private static final

     @Mock private ApplicationEventPublisher mockPublisher;
     @Mock private PessoaService mockPessoaService;
     @Mock private PessoaRepository mockPessoaRepository;
     @Mock private Pessoa mockPessoa;
     @Mock private ResponseEntity<Pessoa> mockResponseEntityPessoa;
     @Mock private ResponseEntity<?> mockResponseEntity;
     @Mock private HttpServletResponse mockHttpServletResponse;
     @Mock private Page<Pessoa> mockPagePessoa;

     @Spy
     @InjectMocks 
     private PessoaController pessoaController;

     @Test
     void listarTest() {
    	 
          // SETUP
          PessoaFilter pessoaFilter = new PessoaFilter();
          Pageable pageable = Pageable.unpaged();
          when(mockPessoaRepository.filtrar(pessoaFilter, pageable)).thenReturn(mockPagePessoa);

          // CALL
          Page<Pessoa> response = pessoaController.listar(pessoaFilter, pageable);

          // ASSERTS
          assertEquals(mockPagePessoa, response);
          verify(mockPessoaRepository, times(1)).filtrar(pessoaFilter, pageable);
     }

     @Test
     void criarTest() {
    	 
          // SETUP
          Pessoa pessoa = new Pessoa();
          when(mockPessoaService.salvar(pessoa)).thenReturn(mockPessoa);

          // CALL
          ResponseEntity<Pessoa> response = pessoaController.criar(pessoa,mockHttpServletResponse);

          // ASSERTS
          assertEquals(ResponseEntity.created(null).body(mockPessoa), response);
          verify(mockPessoaService, times(1)).salvar(pessoa);
     }

     @Test
     void removerTest() {
    	 
          // SETUP
          Long id = 1L;

          // CALL
          pessoaController.remover(id);

          // ASSERTS
          verify(mockPessoaRepository, times(1)).deleteById(id);
     }

     @Test
     void atualizarTest() {
    	 
          // SETUP
          Long codigo = 1L;
          Pessoa pessoa = new Pessoa();
          when(mockPessoaService.atualizar(codigo, pessoa)).thenReturn(mockPessoa);

          // CALL
          ResponseEntity<Pessoa> response = pessoaController.atualizar(codigo, pessoa);

          // ASSERTS
          assertEquals(ResponseEntity.ok(mockPessoa), response);
          verify(mockPessoaService, times(1)).atualizar(codigo, pessoa);
     }

     @Test
     void atualizarPropriedadeAtivoTest() {
    	 
          // SETUP
          Long codigo = 1L;
          Boolean ativo = true;

          // CALL
          pessoaController.atualizarPropriedadeAtivo(codigo, ativo);

          // ASSERTS
          verify(mockPessoaService, times(1)).atualizarPropriedadeAtivo(codigo, ativo);
     }
     
 	@Test
 	void buscarPeloCodigoOkTest() {
 		
 		// SETUP
 		Long id = 1L;
 		when(mockPessoaRepository.findById(id)).thenReturn(Optional.of(mockPessoa));
 				
 		// CALL
 		ResponseEntity<?> response = pessoaController.buscarPeloCodigo(id);
 		
 		// ASSERTS
 		assertEquals(ResponseEntity.ok(Optional.of(mockPessoa)), response);		
 		verify(mockPessoaRepository, times(1)).findById(id);
 	}

 	@Test
 	void buscarPeloCodigoNotFoundTest() {
 		
 		// SETUP
 		Long id = 1L;
 		when(mockPessoaRepository.findById(id)).thenReturn(Optional.empty());
 				
 		// CALL
 		ResponseEntity<?> response = pessoaController.buscarPeloCodigo(id);
 		
 		// ASSERTS
 		assertEquals(ResponseEntity.notFound().build(), response);		
 		verify(mockPessoaRepository, times(1)).findById(id);
 	}
     
}