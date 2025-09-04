package com.financeiro.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import lombok.Setter;

@ConfigurationProperties("financeiro")
@Data
public class FinanceiroApiProperty {

    private String originPermitida = "http://localhost:9191";

    private final Seguranca seguranca = new Seguranca();

    private final Jwt jwt = new Jwt();

    public void setOriginPermitida(String originPermitida) {

        this.originPermitida = originPermitida;
    }

    @Setter
    public static class Seguranca {

        private boolean enableHttps;

        public boolean isEnableHttps() {
            return enableHttps;
        }

    }


    @Data
    public static class Jwt {

        private String secretKey;
        private String issuer;
        private String timeZone;
        private long expirationSeconds;

    }


}