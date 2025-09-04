package com.financeiro.api.constant;

public class Constant {

    public static final String CLIENT_ID = "client_id";
    public static final String PREFIX_ROLE = "ROLE_";
    public static final String REALM_ACCESS = "realm_access";
    public static final String RESOURCE_ACCESS = "resource_access";
    public static final String ROLES = "roles";
    public static final String PUBLIC_ENDPOINT = "/public/**";
    public static final String ACTUATOR_HEALTH_ENDPOINT = "/actuator/health";
    public static final String ACTUATOR_METRICS_ENDPOINT = "/actuator/metrics";
    public static final String ACTUATOR_PROMETHEUS_ENDPOINT = "/actuator/prometheus";

    public static final String HTTP_FORMAT = "http://";
    public static final String S3_AWS_DOMAIN = ".s3.amazonaws.com/";

    public static final String MSG_SUCCESS_S3_BUCKET = "Arquivo {} enviado com sucesso para o S3.";
    public static final String MSG_FAIL_S3_BUCKET = "Problemas ao tentar enviar o arquivo para o S3.";


}