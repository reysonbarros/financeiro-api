package com.financeiro.api.repository.listener;

import jakarta.persistence.PostLoad;

import org.springframework.util.StringUtils;

import com.financeiro.api.FinanceiroApiApplication;
import com.financeiro.api.model.Lancamento;
import com.financeiro.api.storage.S3;


public class LancamentoAnexoListener {
	
	@PostLoad
	public void postLoad(Lancamento lancamento) {
		if (StringUtils.hasText(lancamento.getAnexo())) {
			S3 s3 = FinanceiroApiApplication.getBean(S3.class);
			lancamento.setUrlAnexo(s3.configurarUrl(lancamento.getAnexo()));			
		}
	}

}