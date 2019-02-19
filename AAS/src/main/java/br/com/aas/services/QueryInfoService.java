package br.com.aas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aas.config.exception.model.BusinessException;
import br.com.aas.entities.QueryInfo;
import br.com.aas.repositorios.QueryInfoRepository;

@Service
public class QueryInfoService {

	@Autowired
	private QueryInfoRepository repository;

	private void validateQueryInfo(QueryInfo queryinfo) throws BusinessException {
		if (queryinfo.getSelect() == null || queryinfo.getSelect().equals("")) {
			throw new BusinessException("Informe o sql de consulta!!");
		}
	}

	public QueryInfo save(QueryInfo queryinfo) {
		validateQueryInfo(queryinfo);

		queryinfo.setId(0);
		return repository.save(queryinfo);
	}

	public QueryInfo update(QueryInfo queryinfo) {
		if (queryinfo.getId() == 0) {
			throw new BusinessException("Informe o identificador da query!!");
		}
		validateQueryInfo(queryinfo);

		repository.findById(queryinfo.getId())
				.orElseThrow(() -> new NullPointerException("Banco de dados não encotrada"));

		return repository.save(queryinfo);
	}

	public List<QueryInfo> findAll() {
		return repository.findAll();
	}

	public QueryInfo findById(long id) {
		if (id == 0) {
			throw new BusinessException("Informe um identificador valido!!");
		}
		return repository.findById(id)
				.orElseThrow(() -> new NullPointerException("Query não encontrado com o filtro especificado!!"));
	}
}
