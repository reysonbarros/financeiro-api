package com.financeiro.api.categoria;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financeiro.api.config.ContainersConfig;
import com.financeiro.api.model.Categoria;
import com.financeiro.api.scenario.header.ScenarioHeader;
import com.financeiro.api.scenario.request.ScenarioRequest;
import com.financeiro.api.utils.TokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
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

import static com.financeiro.api.utils.ConstantUtil.ENDPOINT_AUTH;
import static com.financeiro.api.utils.ConstantUtil.ENDPOINT_CATEGORIAS;
import static com.financeiro.api.utils.ConstantUtil.COLON_CHAR;
import static com.financeiro.api.utils.ConstantUtil.CREATE_CATEGORIA_URI;
import static com.financeiro.api.utils.ConstantUtil.GET_ACCESS_TOKEN_URI;
import static com.financeiro.api.utils.ConstantUtil.INTEGRATION_TEST_PROFILE;
import static com.financeiro.api.utils.ConstantUtil.SERVER_HOST;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import({ContainersConfig.class})
@ActiveProfiles(INTEGRATION_TEST_PROFILE)
public class CategoriaScenarioOkIT {

    @LocalServerPort
    private int SERVER_PORT;

    private static final RestTemplate restTemplate = new RestTemplate();

    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    @BeforeEach
    public void setUp() {
        ENDPOINT_CATEGORIAS = SERVER_HOST.concat(COLON_CHAR).concat(String.valueOf(SERVER_PORT))
                .concat(CREATE_CATEGORIA_URI);

        ENDPOINT_AUTH = SERVER_HOST.concat(COLON_CHAR).concat(String.valueOf(SERVER_PORT))
                .concat(GET_ACCESS_TOKEN_URI);
    }

    @ParameterizedTest
    @MethodSource
    void whenCreateCategoriaThenOk(Categoria request) throws Exception {
        String jsonString = objectMapper.writeValueAsString(request);
        var requestEntity = new HttpEntity<>(jsonString, ScenarioHeader.DefaultHeaders.requiredHeadersBearerAuth(TokenUtil.getToken()));
        
        ResponseEntity<Categoria> response = restTemplate.exchange(
                ENDPOINT_CATEGORIAS,
                HttpMethod.POST,
                requestEntity,
                Categoria.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(Objects.requireNonNull(response.getBody()).getCodigo());
    }

    static Stream<Categoria> whenCreateCategoriaThenOk() {
        List<Categoria> requests = new ArrayList<>();
        requests.add(ScenarioRequest.categoria.requiredFields());
        return requests.stream();
    }
}
