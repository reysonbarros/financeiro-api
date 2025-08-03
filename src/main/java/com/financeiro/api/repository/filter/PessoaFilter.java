package com.financeiro.api.repository.filter;

import com.financeiro.api.model.TipoPessoa;

public class PessoaFilter {
	
	private String nome;
	
	private TipoPessoa tipo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoPessoa getTipo() {
		return tipo;
	}

	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}	

	
	
}
