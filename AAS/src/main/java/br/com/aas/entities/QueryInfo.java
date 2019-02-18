package br.com.aas.entities;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;

@Entity
public class QueryInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "select_info", nullable = false)
	private String select;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "alias_table", joinColumns=@JoinColumn(name="id"))
	@MapKeyColumn(name = "coluna", length = 50)
	@Column(name = "alias", length = 100)
	private Map<String, String> alias = new HashMap<String, String>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public void setAlias(HashMap<String, String> alias) {
		this.alias = alias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((select == null) ? 0 : select.hashCode());
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
		QueryInfo other = (QueryInfo) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		if (id != other.id)
			return false;
		if (select == null) {
			if (other.select != null)
				return false;
		} else if (!select.equals(other.select))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Query [id=");
		builder.append(id);
		builder.append(", select=");
		builder.append(select);
		builder.append(", alias=");
		builder.append(alias);
		builder.append("]");
		return builder.toString();
	}

}
