package com.financeiro.api.lancamento;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.financeiro.api.config.ContainersConfig;
import com.financeiro.api.model.Categoria;
import com.financeiro.api.model.Lancamento;
import com.financeiro.api.model.Pessoa;
import com.financeiro.api.repository.CategoriaRepository;
import com.financeiro.api.repository.PessoaRepository;
import com.financeiro.api.scenario.header.ScenarioHeader;
import com.financeiro.api.scenario.request.ScenarioRequest;
import com.financeiro.api.utils.TokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static com.financeiro.api.utils.ConstantUtil.COLON_CHAR;
import static com.financeiro.api.utils.ConstantUtil.CREATE_CATEGORIA_URI;
import static com.financeiro.api.utils.ConstantUtil.CREATE_LANCAMENTO_URI;
import static com.financeiro.api.utils.ConstantUtil.CREATE_PESSOA_URI;
import static com.financeiro.api.utils.ConstantUtil.ENDPOINT_AUTH;
import static com.financeiro.api.utils.ConstantUtil.ENDPOINT_CATEGORIAS;
import static com.financeiro.api.utils.ConstantUtil.ENDPOINT_LANCAMENTOS;
import static com.financeiro.api.utils.ConstantUtil.ENDPOINT_PESSOAS;
import static com.financeiro.api.utils.ConstantUtil.GET_ACCESS_TOKEN_URI;
import static com.financeiro.api.utils.ConstantUtil.INTEGRATION_TEST_PROFILE;
import static com.financeiro.api.utils.ConstantUtil.SERVER_HOST;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import({ContainersConfig.class})
@ActiveProfiles(INTEGRATION_TEST_PROFILE)
// @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) # Keep database clean for each test, so it is a lazy process
// For MySQL container access via DBeaver you should use root(username) and test(password)
public class LancamentoScenarioOkIT {

    @LocalServerPort
    private int SERVER_PORT;

    private static final RestTemplate restTemplate = new RestTemplate();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    PessoaRepository pessoaRepository;
    
    @Autowired
    CategoriaRepository categoriaRepository;

    @BeforeEach
    public void setUp() {

        ENDPOINT_LANCAMENTOS = SERVER_HOST.concat(COLON_CHAR).concat(String.valueOf(SERVER_PORT))
                .concat(CREATE_LANCAMENTO_URI);

        ENDPOINT_PESSOAS = SERVER_HOST.concat(COLON_CHAR).concat(String.valueOf(SERVER_PORT))
                .concat(CREATE_PESSOA_URI);

        ENDPOINT_CATEGORIAS = SERVER_HOST.concat(COLON_CHAR).concat(String.valueOf(SERVER_PORT))
                .concat(CREATE_CATEGORIA_URI);

        ENDPOINT_AUTH = SERVER_HOST.concat(COLON_CHAR).concat(String.valueOf(SERVER_PORT))
                .concat(GET_ACCESS_TOKEN_URI);
    }

    @ParameterizedTest
    @MethodSource
    void whenCreateLancamentoThenOk(Lancamento request) throws Exception{

        buildPessoaAndCategoria();

        String jsonString = objectMapper.writeValueAsString(request);

        var requestEntity = new HttpEntity<>(jsonString, ScenarioHeader.DefaultHeaders.requiredHeadersBearerAuth(TokenUtil.getToken()));
        ResponseEntity<Lancamento> response = restTemplate.exchange(
                ENDPOINT_LANCAMENTOS,
                HttpMethod.POST,
                requestEntity,
                Lancamento.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(Objects.requireNonNull(response.getBody()).getCodigo());

    }

    static Stream<Lancamento> whenCreateLancamentoThenOk() {
        List<Lancamento> requests = new ArrayList<>();
        requests.add(ScenarioRequest.lancamento.requiredFields());
        requests.add(ScenarioRequest.lancamento.withTipoReceita());
        requests.add(ScenarioRequest.lancamento.withoutObservacao());
        requests.add(ScenarioRequest.lancamento.withoutAnexo());
        requests.add(ScenarioRequest.lancamento.withoutUrlAnexo());
        requests.add(ScenarioRequest.lancamento.withoutDataPagamento());
        

        return requests.stream();
    }

    private void createPessoa() throws Exception{

        String jsonString = objectMapper.writeValueAsString(ScenarioRequest.pessoa.requiredFields());

        var requestEntity = new HttpEntity<>(jsonString, ScenarioHeader.DefaultHeaders.requiredHeadersBearerAuth(TokenUtil.getToken()));
        ResponseEntity<Pessoa> response = restTemplate.exchange(
                ENDPOINT_PESSOAS,
                HttpMethod.POST,
                requestEntity,
                Pessoa.class
        );

    }

    private void createCategoria() throws Exception{

        String jsonString = objectMapper.writeValueAsString(ScenarioRequest.categoria.requiredFields());

        var requestEntity = new HttpEntity<>(jsonString, ScenarioHeader.DefaultHeaders.requiredHeadersBearerAuth(TokenUtil.getToken()));
        ResponseEntity<Categoria> response = restTemplate.exchange(
                ENDPOINT_CATEGORIAS,
                HttpMethod.POST,
                requestEntity,
                Categoria.class
        );

    }

    private void buildPessoaAndCategoria() throws Exception {
        createPessoa();
        createCategoria();
    }

}
