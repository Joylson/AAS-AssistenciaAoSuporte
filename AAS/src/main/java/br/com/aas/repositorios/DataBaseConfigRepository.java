package br.com.aas.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.aas.entities.DatabaseConfig;

public interface DataBaseConfigRepository extends JpaRepository<DatabaseConfig, Long>{

	public List<DatabaseConfig> findByActive(boolean active);
	
	@Query("select db.active from DatabaseConfig db where db.id = :id")
	public boolean validateActive(@Param("id") long id);
	
}
