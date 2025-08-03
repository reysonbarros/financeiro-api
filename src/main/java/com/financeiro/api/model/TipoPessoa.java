package com.financeiro.api.model;

public enum TipoPessoa {
	
	FORNECEDOR("Fornecedor"),
	CLIENTE("Cliente");

	private final String descricao;
	
	private TipoPessoa(String descricao) {
		this.descricao = descricao;		
	}
	
	public String getDescricao() {
		return descricao;
	}

}
