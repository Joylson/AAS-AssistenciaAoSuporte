package br.com.aas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aas.entities.QueryInfo;

public interface QueryInfoRepository extends JpaRepository<QueryInfo, Long>{
	

}
