package com.financeiro.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

import static com.financeiro.api.utils.ConstantUtil.LOCAL_STACK_IMAGE_TAG_NAME;
import static com.financeiro.api.utils.ConstantUtil.MYSQL_IMAGE_TAG_NAME;
import static com.financeiro.api.utils.ConstantUtil.SPRING_CLOUD_AWS_S3_BUCKET;
import static com.financeiro.api.utils.ConstantUtil.SPRING_CLOUD_AWS_S3_ENDPOINT;
import static com.financeiro.api.utils.ConstantUtil.SPRING_CLOUD_AWS_S3_PREFIX;
import static com.financeiro.api.utils.ConstantUtil.SPRING_CLOUD_AWS_S3_REGION;
import static com.financeiro.api.utils.ConstantUtil.SPRING_DS_PASSWORD_PROPERTY;
import static com.financeiro.api.utils.ConstantUtil.SPRING_DS_URL_PROPERTY;
import static com.financeiro.api.utils.ConstantUtil.SPRING_DS_USERNAME_PROPERTY;
import static com.financeiro.api.utils.ConstantUtil.SPRING_FLYWAY_ENABLED_PROPERTY;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;

@TestConfiguration(proxyBeanMethods = false)
public class ContainersConfig {

    private static final String S3_BUCKET = "s3-financeiro-arquivos-test";
    private static final String S3_PREFIX = "lancamento-anexo-test";

    @Bean
    @ServiceConnection
    MySQLContainer mysql(){
        return new MySQLContainer<>(DockerImageName.parse(MYSQL_IMAGE_TAG_NAME));
    }

    @Bean
    LocalStackContainer localstackContainer(DynamicPropertyRegistry registry){
        LocalStackContainer localStack = new LocalStackContainer(DockerImageName
                .parse(LOCAL_STACK_IMAGE_TAG_NAME)).withServices(S3);

        return localStack;
    }

    @Bean
    DynamicPropertyRegistrar apiPropertiesRegistrar(MySQLContainer mysql, LocalStackContainer localStackContainer) {

        return registry -> {
            registry.add(SPRING_DS_URL_PROPERTY, mysql::getJdbcUrl);
            registry.add(SPRING_DS_USERNAME_PROPERTY, mysql::getUsername);
            registry.add(SPRING_DS_PASSWORD_PROPERTY, mysql::getPassword);
            registry.add(SPRING_FLYWAY_ENABLED_PROPERTY, () -> Boolean.TRUE);
            registry.add(SPRING_CLOUD_AWS_S3_REGION, localStackContainer::getRegion);
            registry.add(SPRING_CLOUD_AWS_S3_ENDPOINT, () -> localStackContainer.getEndpointOverride(LocalStackContainer.Service.S3).toString());
            registry.add(SPRING_CLOUD_AWS_S3_BUCKET, () -> S3_BUCKET);
            registry.add(SPRING_CLOUD_AWS_S3_PREFIX, () -> S3_PREFIX);

        };

    }

}