package com.financeiro.api.model;

public enum PerfilPessoa {
	
	PF("PF"),
	PJ("PJ");

	private final String descricao;
	
	private PerfilPessoa(String descricao) {
		this.descricao = descricao;		
	}
	
	public String getDescricao() {
		return descricao;
	}

}
