package com.financeiro.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financeiro.api.dto.AuthReq;
import com.financeiro.api.dto.RecoveryJwtToken;
import com.financeiro.api.model.Categoria;
import com.financeiro.api.scenario.header.ScenarioHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static com.financeiro.api.utils.ConstantUtil.EMAIL_BODY_FIELD;
import static com.financeiro.api.utils.ConstantUtil.EMAIL_BODY_VALUE;
import static com.financeiro.api.utils.ConstantUtil.ENDPOINT_AUTH;
import static com.financeiro.api.utils.ConstantUtil.PASSWORD_BODY_FIELD;
import static com.financeiro.api.utils.ConstantUtil.PASSWORD_BODY_VALUE;
import static java.util.Collections.singletonList;

public class TokenUtil {

    public static String getToken() throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();

        AuthReq authReq = new AuthReq(EMAIL_BODY_VALUE,PASSWORD_BODY_VALUE);

        ObjectMapper objectMapper = new ObjectMapper();

        String jsonString = objectMapper.writeValueAsString(authReq);

        var request = new HttpEntity<>(jsonString, ScenarioHeader.DefaultHeaders.requiredHeadersAccessToken());
        RecoveryJwtToken jwt = restTemplate.postForObject(
                ENDPOINT_AUTH,
                request,
                RecoveryJwtToken.class
        );

        assert jwt != null;
        return jwt.token();
    }
}