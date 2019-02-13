package br.com.aas.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aas.entities.DatabaseConfig;

public interface DataBaseConfigRepository extends JpaRepository<DatabaseConfig, Long>{

	public List<DatabaseConfig> findByActive(boolean active);
}
