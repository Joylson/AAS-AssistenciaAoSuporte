package br.com.aas.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aas.config.exception.model.BusinessException;
import br.com.aas.config.exception.model.ValidationDataException;
import br.com.aas.entities.DatabaseConfig;
import br.com.aas.entities.QueryInfo;
import br.com.aas.entities.QueryResult;
import br.com.aas.repositorios.connection.ConnectionUtil;

@Service
public class QueryResultService {

	@Autowired
	private DataBaseConfigService dbServico;
	@Autowired
	private QueryInfoService qiServico;

	public Object[][] validQuery(String sql, int page, int rows) {
		if (sql == null || sql.equals("")) {
			throw new BusinessException("Informe um sql para validação!!");
		}
		List<DatabaseConfig> dbs = dbServico.findByActive(true);
		if (dbs == null || dbs.isEmpty()) {
			throw new ValidationDataException("Banco de para consulta não especificado!!");
		}
		if (dbs.size() > 1) {
			throw new ValidationDataException("Banco de para consulta Incossistente!!");
		}
		
		DatabaseConfig db = dbs.get(0);
		Connection conn;
		try {
			conn = ConnectionUtil.getConnection(db.getUser(), db.getPassword(), db.getDriver().getDescrica(), db.getUrl());
			return ConnectionUtil.getRowsValid(conn, sql, page, rows);
		} catch (ClassNotFoundException | SQLException e) {
			throw new ValidationDataException("Consulta invalida" + sql +  "!!");
		}
		
	}
	
	
	public QueryResult returnQuery(long id, int page, int rows) {
		if (id == 0) {
			throw new BusinessException("Informe um identificado para validação!!");
		}
		QueryInfo qi = qiServico.findById(id);		
		List<DatabaseConfig> dbs = dbServico.findByActive(true);
		if (dbs == null || dbs.isEmpty()) {
			throw new ValidationDataException("Banco de para consulta não especificado!!");
		}
		if (dbs.size() > 1) {
			throw new ValidationDataException("Banco de para consulta Incossistente!!");
		}
		
		DatabaseConfig db = dbs.get(0);
		Connection conn;
		try {
			conn = ConnectionUtil.getConnection(db.getUser(), db.getPassword(), db.getDriver().getDescrica(), db.getUrl());
			return orderQuery(ConnectionUtil.getQueryResult(conn, qi.getSelect(), page, rows), qi);
		} catch (ClassNotFoundException | SQLException e) {
			throw new ValidationDataException("Consulta invalida" + qi.getSelect() +  "!!");
		}
	}
	
	private QueryResult orderQuery(QueryResult qr, QueryInfo qi) {
		for (int i = 0; i < qr.getColumns().length; i++) {
			if(qi.getAlias().containsKey(qr.getColumns()[i])) {
				qr.getColumns()[i] = qi.getAlias().get(qr.getColumns()[i]);
			}
		}
		return qr;
	}

}
