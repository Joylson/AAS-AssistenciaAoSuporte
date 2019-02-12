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

	private void validateDataConfig(DatabaseConfig dataConfig) throws BusinessException {
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

	public void save(DatabaseConfig dataConfig) throws BusinessException {
		validateDataConfig(dataConfig);

		dataConfig.setId(0);
		repository.save(dataConfig);
	}

	public void update(DatabaseConfig dataConfig) throws BusinessException {
		if (dataConfig.getId() == 0) {
			throw new BusinessException("Informe o id do usuario!!");
		}
		validateDataConfig(dataConfig);

		repository.save(dataConfig);
	}

	public List<DatabaseConfig> list() {
		return repository.findAll();
	}

	public DatabaseConfig find(long id) {
		if (id == 0) {
			throw new BusinessException("Informe um id valido!!");
		}
		return repository.findById(id)
				.orElseThrow(() -> new BusinessException("Usuario não encontrado com o filtro especificado!!"));
	}

}
