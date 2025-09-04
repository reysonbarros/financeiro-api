package com.financeiro.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.NoSuchBucketException;

import java.net.URI;


@Configuration
public class S3Config {


    @Value("${spring.cloud.config.server.awss3.region}")
    private String s3Region;

    @Value("${spring.cloud.config.server.awss3.bucket}")
    private String s3Bucket;

    @Value("${spring.cloud.config.server.awss3.endpoint}")
    private String s3Endpoint;

    @Bean
    public S3Client amazonS3() {

        S3Client amazonS3 = S3Client.builder()
                .endpointOverride(URI.create(s3Endpoint))
                .region(Region.of(s3Region))
                .build();

        HeadBucketRequest bucketRequest = HeadBucketRequest.builder()
                .bucket(s3Bucket).build();

        try {
            amazonS3.headBucket(bucketRequest); // Check if bucket exists
        } catch (NoSuchBucketException e) {
            amazonS3.createBucket(CreateBucketRequest.builder().bucket(s3Bucket).build()); // Create the bucket if not exists
        }

        return amazonS3;
    }
}