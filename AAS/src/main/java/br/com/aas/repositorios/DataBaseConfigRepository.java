package br.com.aas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aas.entities.DatabaseConfig;

public interface DataBaseConfigRepository extends JpaRepository<DatabaseConfig, Long>{

}
