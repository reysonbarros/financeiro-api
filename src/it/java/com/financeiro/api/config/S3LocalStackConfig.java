package com.financeiro.api.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.containers.localstack.LocalStackContainer;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;

@TestConfiguration(proxyBeanMethods = false)
public class S3LocalStackConfig {

   public S3Client s3Client(AwsCredentialsProvider credentialsProvider, LocalStackContainer localstack){
       return S3Client.builder()
               .endpointOverride(localstack.getEndpointOverride(S3))
               .credentialsProvider(credentialsProvider)
               .build();
   }

}
