package com.financeiro.api.storage;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectTaggingRequest;
import software.amazon.awssdk.services.s3.model.Tagging;

import static com.financeiro.api.constant.Constant.HTTP_FORMAT;
import static com.financeiro.api.constant.Constant.MSG_FAIL_S3_BUCKET;
import static com.financeiro.api.constant.Constant.MSG_SUCCESS_S3_BUCKET;
import static com.financeiro.api.constant.Constant.S3_AWS_DOMAIN;


@Component
public class S3 {

    private static final Logger logger = LoggerFactory.getLogger(S3.class);

    @Autowired
    private S3Client amazonS3;

    @Value("${spring.cloud.config.server.awss3.bucket}")
    private String s3Bucket;

    public String salvarTemporariamente(MultipartFile arquivo, String folder) {

        String nomeUnico = gerarNomeUnico(arquivo.getOriginalFilename(), folder);

        try {

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(s3Bucket)
                    .key(nomeUnico)
                    .contentType(arquivo.getContentType())
                    .build();

            amazonS3.putObject(putObjectRequest, RequestBody.fromInputStream(arquivo.getInputStream(),arquivo.getSize()));

            if (logger.isDebugEnabled()) {
                logger.debug(MSG_SUCCESS_S3_BUCKET,
                        arquivo.getOriginalFilename());
            }

            return nomeUnico;
        } catch (IOException e) {
            throw new RuntimeException(MSG_FAIL_S3_BUCKET, e);
        }
    }

    public String configurarUrl(String objeto) {
        return HTTP_FORMAT + s3Bucket +
                S3_AWS_DOMAIN + objeto;
    }


    public void salvar(String objeto) {
        PutObjectTaggingRequest putObjectTaggingRequest = PutObjectTaggingRequest.builder()
                .bucket(s3Bucket)
                .key(objeto)
                .tagging(Tagging.builder().tagSet(Collections.emptyList()).build())
                .build();

        amazonS3.putObjectTagging(putObjectTaggingRequest);

    }

    public void remover(String objeto) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(s3Bucket)
                .key(objeto)
                .build();

        amazonS3.deleteObject(deleteObjectRequest);
    }

    public void substituir(String objetoAntigo, String objetoNovo) {
        if (StringUtils.hasText(objetoAntigo)) {
            this.remover(objetoAntigo);
        }

        salvar(objetoNovo);
    }


    public String gerarNomeUnico(String originalFilename, String folder) {
        return folder.concat("/").concat(UUID.randomUUID().toString()).concat("_").concat(originalFilename);
    }


}