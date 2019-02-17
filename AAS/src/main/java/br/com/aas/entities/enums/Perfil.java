package br.com.aas.entities.enums;

public enum Perfil {
	ADMIN("ROLE_ADMIN"), SUPORTE("ROLE_SUPORTE"), PROGRAMADOR("ROLE_POGRAMADOR");

	private String descricao;

	Perfil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
