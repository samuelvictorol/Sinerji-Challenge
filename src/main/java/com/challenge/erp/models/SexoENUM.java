package com.challenge.erp.models;

public enum SexoENUM {
	
	MASC("Masculino"), 
	FEM("Feminino"),
	NO("Prefiro não responder");
	
	private String descricao;

	SexoENUM(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}