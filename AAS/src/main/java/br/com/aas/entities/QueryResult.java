package br.com.aas.entities;

import java.util.Arrays;

public class QueryResult {
	private String[] table;
	private String[] columns;
	private Object[][] rows;
	private int count;
	private int page;
	private int pages;

	public String[] getTable() {
		return table;
	}

	public void setTable(String[] table) {
		this.table = table;
	}

	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
	}

	public Object[][] getRows() {
		return rows;
	}

	public void setRows(Object[][] rows) {
		this.rows = rows;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(columns);
		result = prime * result + count;
		result = prime * result + page;
		result = prime * result + pages;
		result = prime * result + Arrays.deepHashCode(rows);
		result = prime * result + Arrays.hashCode(table);
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
		QueryResult other = (QueryResult) obj;
		if (!Arrays.equals(columns, other.columns))
			return false;
		if (count != other.count)
			return false;
		if (page != other.page)
			return false;
		if (pages != other.pages)
			return false;
		if (!Arrays.deepEquals(rows, other.rows))
			return false;
		if (!Arrays.equals(table, other.table))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QueryResult [table=").append(Arrays.toString(table)).append(", columns=")
				.append(Arrays.toString(columns)).append(", rows=").append(Arrays.toString(rows)).append(", count=")
				.append(count).append(", page=").append(page).append(", pages=").append(pages).append("]");
		return builder.toString();
	}

}
