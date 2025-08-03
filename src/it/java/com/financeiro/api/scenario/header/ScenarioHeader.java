package com.financeiro.api.scenario.header;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static com.financeiro.api.utils.ConstantUtil.ACCEPT_ENCODING_HEADER_VALUE;
import static com.financeiro.api.utils.ConstantUtil.ACCEPT_HEADER_VALUE;
import static com.financeiro.api.utils.ConstantUtil.BEARER_AUTH_FIELD;
import static com.financeiro.api.utils.ConstantUtil.CACHE_CONTROL_HEADER_VALUE;
import static com.financeiro.api.utils.ConstantUtil.CONNECTION_HEADER_VALUE;
import static com.financeiro.api.utils.ConstantUtil.HOST_HEADER_VALUE;
import static com.financeiro.api.utils.ConstantUtil.SPACE_CHAR;

public class ScenarioHeader {

    public static class DefaultHeaders {
        public static HttpHeaders requiredHeadersBearerAuth(String accessToken) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            headers.add(HttpHeaders.AUTHORIZATION, BEARER_AUTH_FIELD.concat(SPACE_CHAR).concat(accessToken));
            return headers;
        }

        public static HttpHeaders requiredHeadersAccessToken() {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.ACCEPT, ACCEPT_HEADER_VALUE);
            headers.add(HttpHeaders.CACHE_CONTROL, CACHE_CONTROL_HEADER_VALUE);
            headers.add(HttpHeaders.HOST, HOST_HEADER_VALUE);
            headers.add(HttpHeaders.ACCEPT_ENCODING, ACCEPT_ENCODING_HEADER_VALUE);
            headers.add(HttpHeaders.CONNECTION, CONNECTION_HEADER_VALUE);
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

            return headers;
        }
    }
}
