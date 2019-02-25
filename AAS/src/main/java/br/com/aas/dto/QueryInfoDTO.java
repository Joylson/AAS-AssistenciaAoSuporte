package br.com.aas.dto;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import br.com.aas.entities.QueryInfo;

public class QueryInfoDTO {

	@NotEmpty(message = "Informe o titulo da consulta")
	@Length(max = 80, message = "Seu titulo excede o limite de 80 caracter")
	private String title;

	@NotEmpty(message = "Informe o select de consulta")
	@Length(max = 9000, message = "Seu select excedeu o limite 9000 caracters")
	private String select;

	private Map<@NotEmpty(message = "Alias com colunas não definidas") @Length(max = 100, message = "sua coluna excedeu o limite de 100 caracters") String, @NotEmpty(message = "Alias com alias não definidas") @Length(max = 100, message = "sua alias excedeu o limite de 100 caracters") String> alias = new HashMap<String, String>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public Map<String, String> getAlias() {
		return alias;
	}

	public void setAlias(Map<String, String> alias) {
		this.alias = alias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		result = prime * result + ((select == null) ? 0 : select.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QueryInfoDTO other = (QueryInfoDTO) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		if (select == null) {
			if (other.select != null)
				return false;
		} else if (!select.equals(other.select))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QueryInfoDTO [title=");
		builder.append(title);
		builder.append(", select=");
		builder.append(select);
		builder.append(", alias=");
		builder.append(alias);
		builder.append("]");
		return builder.toString();
	}

	public QueryInfo toEntity() {
		ModelMapper map = new ModelMapper();
		return map.map(this, QueryInfo.class);
	}

}
