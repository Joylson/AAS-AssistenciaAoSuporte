package br.com.aas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aas.config.exception.model.BusinessException;
import br.com.aas.entities.User;
import br.com.aas.repositorios.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	private void validateUser(User user) throws BusinessException {
		if (user.getName() == null || user.getName().equals("")) {
			throw new BusinessException("Informe o nome do usuario!!");
		}
		if (user.getLogin() == null || user.getLogin().equals("")) {
			throw new BusinessException("Informe o login do usuario!!");
		}
		if (user.getPassword() == null || user.getPassword().equals("")) {
			throw new BusinessException("Informe a senha do usuario!!");
		}
	}

	public User save(User user) {
		
		validateUser(user);

		user.setId(0);
		return repository.save(user);
	}

}
