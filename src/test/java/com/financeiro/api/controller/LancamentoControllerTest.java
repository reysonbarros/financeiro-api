package com.financeiro.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.financeiro.api.dto.Anexo;
import com.financeiro.api.dto.LancamentoEstatisticaCategoria;
import com.financeiro.api.dto.LancamentoEstatisticaDia;
import com.financeiro.api.model.Lancamento;
import com.financeiro.api.repository.LancamentoRepository;
import com.financeiro.api.repository.filter.LancamentoFilter;
import com.financeiro.api.repository.projection.ResumoLancamento;
import com.financeiro.api.service.LancamentoService;
import com.financeiro.api.storage.S3;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
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
import org.springframework.web.multipart.MultipartFile;

@MockitoSettings
public class LancamentoControllerTest {
    // variables private static final

    @Mock private ApplicationEventPublisher mockPublisher;
    @Mock private LancamentoService mockLancamentoService;
    @Mock private LancamentoRepository mockLancamentoRepository;
    @Mock private List<Lancamento> mockListLancamento;
    @Mock private Lancamento mockLancamento;
    @Mock private ResponseEntity<Lancamento> mockResponseEntityLancamento;
    @Mock private ResponseEntity<?> mockResponseEntity;
    @Mock private HttpServletResponse mockHttpServletResponse;
    @Mock private List<LancamentoEstatisticaDia> mockLancamentoEstatisticaDia;
    @Mock private List<LancamentoEstatisticaCategoria> mockLancamentoEstatisticaCategoria;
    @Mock private Page<Lancamento> mockPageLancamento;
    @Mock private Page<ResumoLancamento> mockPageResumoLancamento;
    @Mock private S3 mocks3;

    @Spy @InjectMocks private LancamentoController lancamentoController;

    @Test
    void UploadAnexoTest() throws IOException {
    	
        // SETUP
        MultipartFile anexo = mock(MultipartFile.class);
        String nome = "arquivo";
        when(mocks3.salvarTemporariamente(anexo, null)).thenReturn(nome);

        // CALL
        Anexo response = lancamentoController.uploadAnexo(anexo);

        // ASSERTS
        assertEquals("arquivo", response.getNome());
        verify(mocks3, times(1)).configurarUrl(nome);
    }

    @Test
    void porDiaTest() {
    	
        // SETUP
        LocalDate now = LocalDate.now();
        when(mockLancamentoRepository.porDia(now)).thenReturn(mockLancamentoEstatisticaDia);

        // CALL
        List<LancamentoEstatisticaDia> response = lancamentoController.porDia();

        // ASSERTS
        assertEquals(mockLancamentoEstatisticaDia, response);
        verify(mockLancamentoRepository, times(1)).porDia(now);
    }

    @Test
    void porCategoriaTest() {
    	
        // SETUP
        LocalDate now = LocalDate.now();
        when(mockLancamentoRepository.porCategoria(now)).thenReturn(mockLancamentoEstatisticaCategoria);

        // CALL
        List<LancamentoEstatisticaCategoria> response = lancamentoController.porCategoria();

        // ASSERTS
        assertEquals(mockLancamentoEstatisticaCategoria, response);
        verify(mockLancamentoRepository, times(1)).porCategoria(now);
    }

    @Test
    void pesquisarTest() {
    	
        // SETUP
        LancamentoFilter lancamentoFilter = new LancamentoFilter();
        Pageable pageable = Pageable.unpaged();
        when(mockLancamentoRepository.filtrar(lancamentoFilter, pageable)).thenReturn(mockPageLancamento);

        // CALL
        Page<Lancamento> response = lancamentoController.pesquisar(lancamentoFilter, pageable);

        // ASSERTS
        assertEquals(mockPageLancamento, response);
        verify(mockLancamentoRepository, times(1)).filtrar(lancamentoFilter, pageable);
    }

    @Test
    void resumirTest() {
    	
        // SETUP
        LancamentoFilter lancamentoFilter = new LancamentoFilter();
        Pageable pageable = Pageable.unpaged();
        when(mockLancamentoRepository.resumir(lancamentoFilter, pageable)).thenReturn(mockPageResumoLancamento);

        // CALL
        Page<ResumoLancamento> response = lancamentoController.resumir(lancamentoFilter, pageable);

        // ASSERTS
        assertEquals(mockPageResumoLancamento, response);
        verify(mockLancamentoRepository, times(1)).resumir(lancamentoFilter, pageable);
    }

    @Test
    void buscarPeloCodigoOkTest() {
    	
        // SETUP
        Long id = 1L;
        when(mockLancamentoRepository.findById(id)).thenReturn(Optional.of(mockLancamento));

        // CALL
        ResponseEntity<?> response = lancamentoController.buscarPeloCodigo(id);

        // ASSERTS
        assertEquals(ResponseEntity.ok(Optional.of(mockLancamento)), response);
        verify(mockLancamentoRepository, times(1)).findById(id);
    }

    @Test
    void buscarPeloCodigoNotFoundTest() {
    	
        // SETUP
        Long id = 1L;
        when(mockLancamentoRepository.findById(id)).thenReturn(null);

        // CALL
        ResponseEntity<?> response = lancamentoController.buscarPeloCodigo(id);

        // ASSERTS
        assertEquals(ResponseEntity.notFound().build(), response);
        verify(mockLancamentoRepository, times(1)).findById(id);
    }

    @Test
    void criarTest() {
    	
        // SETUP
        when(mockLancamentoService.salvar(mockLancamento)).thenReturn(mockLancamento);

        // CALL
        ResponseEntity<Lancamento> response = lancamentoController.criar(mockLancamento, mockHttpServletResponse);

        // ASSERTS
        assertEquals(ResponseEntity.created(null).body(mockLancamento), response);
        verify(mockLancamentoService, times(1)).salvar(mockLancamento);

    }

    @Test
    void removerTest() {
    	
        // SETUP
        Long id = 1L;

        // CALL
        lancamentoController.remover(id);

        // ASSERTS
        verify(mockLancamentoRepository, times(1)).deleteById(id);
    }

    @Test
    void atualizarOkTest() {
    	
        // SETUP
        Long id = 1L;
        when(mockLancamentoService.atualizar(id, mockLancamento)).thenReturn(mockLancamento);

        // CALL
        ResponseEntity<?> response = lancamentoController.atualizar(id, mockLancamento);

        // ASSERTS
        assertEquals(ResponseEntity.ok(mockLancamento), response);
        verify(mockLancamentoService, times(1)).atualizar(id, mockLancamento);
    }

    @Test
    void atualizarNotFoundTest() {
    	
        // SETUP
        Long id = 1L;
        when(mockLancamentoService.atualizar(id, mockLancamento)).thenThrow(new IllegalArgumentException());

        // CALL
        ResponseEntity<?> response = lancamentoController.atualizar(id, mockLancamento);

        // ASSERTS
        assertEquals(ResponseEntity.notFound().build(), response);
        verify(mockLancamentoService, times(1)).atualizar(id, mockLancamento);
    }
}