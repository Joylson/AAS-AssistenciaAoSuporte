package br.com.aas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aas.config.exception.model.BusinessException;
import br.com.aas.entities.DatabaseConfig;
import br.com.aas.repositorios.DataBaseConfigRepository;

@Service
public class DataBaseConfigService {

	@Autowired
	private DataBaseConfigRepository repository;

	private void validateDataConfig(DatabaseConfig dataConfig) {
		if (dataConfig.getDriver() == null) {
			throw new BusinessException("Informe o drive do banco!!");
		}
		if (dataConfig.getPassword() == null || dataConfig.getPassword().equals("")) {
			throw new BusinessException("Informe o senha do banco!!");
		}
		if (dataConfig.getUrl() == null || dataConfig.getUrl().equals("")) {
			throw new BusinessException("Informe o URL do banco!!");
		}
		if (dataConfig.getUser() == null || dataConfig.getUser().equals("")) {
			throw new BusinessException("Informe o usuario do banco!!");
		}
	}

	public DatabaseConfig save(DatabaseConfig dataConfig) {
		validateDataConfig(dataConfig);

		dataConfig.setId(0);
		dataConfig.setActive(false);

		return repository.save(dataConfig);
	}

	public DatabaseConfig update(DatabaseConfig dataConfig) {
		if (dataConfig.getId() == 0) {
			throw new BusinessException("Informe o identificador do usuario!!");
		}
		validateDataConfig(dataConfig);

		DatabaseConfig dbBase = repository.findById(dataConfig.getId())
				.orElseThrow(() -> new NullPointerException("Banco de dados não encotrada"));

		dataConfig.setActive(dbBase.isActive());

		return repository.save(dataConfig);
	}

	public List<DatabaseConfig> list() {
		return repository.findAll();
	}

	public DatabaseConfig findById(long id) {
		if (id == 0) {
			throw new BusinessException("Informe um identificador valido!!");
		}
		return repository.findById(id).orElseThrow(
				() -> new NullPointerException("Banco de dados não encontrado com o filtro especificado!!"));
	}

	public List<DatabaseConfig> findByActive(boolean active) {
		return repository.findByActive(active);
	}

	public DatabaseConfig activeDataConfig(long id) {
		if (id == 0) {
			throw new BusinessException("Informe um identificador valido!!");
		}

		DatabaseConfig db = repository.findById(id)
				.orElseThrow(() -> new NullPointerException("Banco de dados não encotrada"));

		if (db.isActive()) {
			throw new BusinessException("Banco de dados já activa!!");
		}

		List<DatabaseConfig> dbs = repository.findByActive(true);
		if (dbs.size() > 1) {
			throw new BusinessException("Incosistencia no banco!!");
		}

		if (dbs.size() > 0) {
			dbs.get(0).setActive(false);
			repository.save(dbs.get(0));
		}

		db.setActive(true);
		return repository.save(db);
	}

	public boolean validateActive(long id) {
		if (id == 0) {
			throw new BusinessException("Informe um identificador valido!!");
		}

		return repository.validateActive(id);
	}

}
