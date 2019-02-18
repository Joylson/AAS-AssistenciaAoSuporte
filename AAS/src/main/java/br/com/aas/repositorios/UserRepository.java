package br.com.aas.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aas.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public Optional<User> findByLogin(String login);
	
}
