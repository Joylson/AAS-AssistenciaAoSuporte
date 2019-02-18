package br.com.aas.services;

import java.util.List;

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
		
		if(repository.findByLogin(user.getLogin()).isPresent()){
			throw new BusinessException("Usuario ja cadastrado!!");
		}
		
		user.setId(0);
		return repository.save(user);
	}

	public User update(User user) {
		validateUser(user);

		repository.findById(user.getId()).orElseThrow(() -> new BusinessException("Usuario não cadastrado!!"));

		return repository.save(user);
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(long id) {
		if (id == 0) {
			throw new BusinessException("Informe um identificador valido!!");
		}

		return repository.findById(id).orElseThrow(() -> new BusinessException("Usuario não encontrado!!"));
	}

}
