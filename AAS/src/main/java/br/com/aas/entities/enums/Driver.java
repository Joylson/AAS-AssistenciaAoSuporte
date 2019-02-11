package br.com.aas.entities.enums;

public enum Driver {
	POSTGRES("org.postgresql.Driver"), MYSQL("com.mysql.jdbc.Drive"), SQLSERVER("com.microsoft.sqlserver.jdbc.SQLServerDriver");

	private String descrica;

	Driver(String decricao) {
		this.descrica = decricao;
	}

	public String getDescrica() {
		return descrica;
	}

	public void setDescrica(String descrica) {
		this.descrica = descrica;
	}

}
