package br.com.aas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aas.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
