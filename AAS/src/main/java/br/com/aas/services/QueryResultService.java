package br.com.aas.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aas.config.exception.model.BusinessException;
import br.com.aas.entities.DatabaseConfig;
import br.com.aas.repositorios.connection.ConnectionUtil;

@Service
public class QueryResultService {

	@Autowired
	private DataBaseConfigService dbServico;

	public Object[][] validQuery(String sql, int page, int rows) {
		if (sql == null || sql.equals("")) {
			throw new BusinessException("Informe um sql para validação!!");
		}
		List<DatabaseConfig> dbs = dbServico.findByActive(true);
		if (dbs == null || dbs.isEmpty()) {
			throw new BusinessException("Banco de para consulta não especificado!!");
		}
		if (dbs.size() > 1) {
			throw new BusinessException("Banco de para consulta Incossistente!!");
		}
		
		DatabaseConfig db = dbs.get(0);
		Connection conn;
		try {
			conn = ConnectionUtil.getConnection(db.getUser(), db.getPassword(), db.getDriver().getDescrica(), db.getUrl());
			return ConnectionUtil.getRowsValid(conn, sql, page, rows);
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Consulta invalida" + sql +  "!!");
		}
		
	}

}
