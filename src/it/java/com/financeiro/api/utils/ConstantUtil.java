package com.financeiro.api.utils;

public class ConstantUtil {

    public static final String MYSQL_IMAGE_TAG_NAME = "mysql:8.0.33";
    public static final String LOCAL_STACK_IMAGE_TAG_NAME = "localstack/localstack:3.0";
    public static final String SERVER_HOST = "http://localhost";
    public static final String CREATE_PESSOA_URI = "/pessoas";
    public static final String CREATE_CATEGORIA_URI = "/categorias";
    public static final String CREATE_LANCAMENTO_URI = "/lancamentos";
    public static final String GET_ACCESS_TOKEN_URI = "/auth";
    public static String ENDPOINT_AUTH;
    public static String ENDPOINT_PESSOAS;
    public static String ENDPOINT_CATEGORIAS;
    public static String ENDPOINT_LANCAMENTOS;
    public static final String EMAIL_BODY_FIELD = "email";
    public static final String EMAIL_BODY_VALUE = "teste@qualidade.com";
    public static final String PASSWORD_BODY_FIELD = "password";
    public static final String PASSWORD_BODY_VALUE = "12345678";
    public static final String SLASH_CHAR = "/";
    public static final String COLON_CHAR = ":";
    public static final String SPACE_CHAR = " ";
    public static final String SPRING_DS_URL_PROPERTY = "spring.datasource.url";
    public static final String SPRING_DS_DRIVER_CLASS_NAME_PROPERTY = "spring.datasource.driverClassName";
    public static final String SPRING_DS_USERNAME_PROPERTY = "spring.datasource.username";
    public static final String SPRING_DS_PASSWORD_PROPERTY = "spring.datasource.password";
    public static final String SPRING_FLYWAY_ENABLED_PROPERTY = "spring.flyway.enabled";
    public static final String INTEGRATION_TEST_PROFILE = "it";

    public static final String ACCEPT_HEADER_VALUE = "*/*";
    public static final String CACHE_CONTROL_HEADER_VALUE = "no-cache";
    public static final String HOST_HEADER_VALUE = "localhost" ;
    public static final String ACCEPT_ENCODING_HEADER_VALUE = "gzip, deflate, br";
    public static final String CONNECTION_HEADER_VALUE = "keep-alive";

    public static final String BEARER_AUTH_FIELD = "Bearer";




}
