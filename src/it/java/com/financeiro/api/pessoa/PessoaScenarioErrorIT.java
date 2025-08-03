package com.financeiro.api.pessoa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financeiro.api.config.AwsConfig;
import com.financeiro.api.config.ContainersConfig;
import com.financeiro.api.config.S3LocalStackConfig;
import com.financeiro.api.model.Pessoa;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.financeiro.api.utils.ConstantUtil.COLON_CHAR;
import static com.financeiro.api.utils.ConstantUtil.CREATE_PESSOA_URI;
import static com.financeiro.api.utils.ConstantUtil.ENDPOINT_AUTH;
import static com.financeiro.api.utils.ConstantUtil.ENDPOINT_PESSOAS;
import static com.financeiro.api.utils.ConstantUtil.GET_ACCESS_TOKEN_URI;
import static com.financeiro.api.utils.ConstantUtil.INTEGRATION_TEST_PROFILE;
import static com.financeiro.api.utils.ConstantUtil.SERVER_HOST;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import({ContainersConfig.class, AwsConfig.class, S3LocalStackConfig.class})
@ActiveProfiles(INTEGRATION_TEST_PROFILE)
public class PessoaScenarioErrorIT {

    @LocalServerPort
    private int SERVER_PORT;

    private static final RestTemplate restTemplate = new RestTemplate();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        ENDPOINT_PESSOAS = SERVER_HOST.concat(COLON_CHAR).concat(String.valueOf(SERVER_PORT))
                .concat(CREATE_PESSOA_URI);

        ENDPOINT_AUTH = SERVER_HOST.concat(COLON_CHAR).concat(String.valueOf(SERVER_PORT))
                .concat(GET_ACCESS_TOKEN_URI);
    }

    @ParameterizedTest
    @MethodSource
    void whenCreatePessoaThenError(Pessoa request) throws Exception{

        String jsonString = objectMapper.writeValueAsString(request);
        var requestEntity = new HttpEntity<>(jsonString, ScenarioHeader.DefaultHeaders.requiredHeadersBearerAuth(TokenUtil.getToken()));

        assertThrows(HttpClientErrorException.class, () -> {
            ResponseEntity<Pessoa> response = restTemplate.exchange(
                    ENDPOINT_PESSOAS,
                    HttpMethod.POST,
                    requestEntity,
                    Pessoa.class
            );
        });

    }

    static Stream<Pessoa> whenCreatePessoaThenError() {
        List<Pessoa> requests = new ArrayList<>();
        requests.add(ScenarioRequest.pessoa.withoutNome());
        requests.add(ScenarioRequest.pessoa.withoutPerfil());
        requests.add(ScenarioRequest.pessoa.withoutTipo());
        requests.add(ScenarioRequest.pessoa.withoutStatus());

        return requests.stream();
    }

}
