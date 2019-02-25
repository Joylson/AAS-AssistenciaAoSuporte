package br.com.aas.repositorios.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.aas.entities.QueryResult;

public class ConnectionUtil {

	public static Connection getConnection(String user, String pass, String driver, String url)
			throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		return DriverManager.getConnection(url, user, pass);
	}

	public static QueryResult getQueryResult(Connection conn, String sql, int page, int rows) throws SQLException {
		Statement smtp = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = smtp.executeQuery(sql);
		QueryResult query_result = new QueryResult();
		query_result.setTable(getTables(rs));
		query_result.setColumns(getColumns(rs));
		query_result.setRows(getRows(rs, page, rows));
		query_result.setCount(getCountRows(rs));
		query_result.setPage(page);
		query_result.setPages(query_result.getCount() / rows);
		return query_result;
	}

	public static Object[][] getRowsValid(Connection conn, String sql, int page, int rows) throws SQLException {
		Statement smtp = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = smtp.executeQuery(sql);
		return getRows(rs, page, rows);
	}

	private static String[] getTables(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		String[] tables = new String[count];
		int count_result = 0;
		for (int i = 1; i <= count; i++) {
			boolean valid_table = true;
			for (String table : tables) {
				if (table != null && table.equals(rsmd.getTableName(i))) {
					valid_table = false;
				}
			}
			if (valid_table) {
				tables[i - 1] = rsmd.getTableName(i);
				count_result++;
			}
		}
		String[] tables_result = new String[count_result];
		count_result = 0;
		for (String table : tables) {
			if (table != null) {
				tables_result[count_result] = table;
				count_result++;
			}
		}
		return tables_result;
	}

	private static String[] getColumns(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		String[] columns = new String[count];
		for (int i = 1; i <= count; i++) {
			columns[i - 1] = rsmd.getColumnName(i);
		}
		return columns;
	}

	private static Object[][] getRows(ResultSet rs, int page, int rows_p) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int count_col = rsmd.getColumnCount();
		rs.last();
		int count_row = rs.getRow();
		rs.beforeFirst();
		Object[][] rows = new Object[count_row][count_col];

		int count_row_seq = 0;
		page = (page - 1) * rows_p;
		int pages = page + rows_p;
		while (rs.next() || count_row_seq == pages) {
			if (count_row_seq == page) {
				for (int i = 1; i <= count_col; i++) {
					rows[count_row_seq][i - 1] = rs.getObject(i);
				}
			}
			count_row_seq++;
		}

		return rows;
	}

	private static int getCountRows(ResultSet rs) throws SQLException {
		rs.last();
		int count = rs.getRow();
		rs.beforeFirst();
		return count;
	}

}
