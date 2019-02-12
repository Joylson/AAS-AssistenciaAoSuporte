package br.com.aas.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aas.entities.DatabaseConfig;
import br.com.aas.services.DataBaseConfigService;


@RestController
@RequestMapping(value="/DataBaseConfig")
public class DataBaseConfigResource {
	
	@Autowired
	private DataBaseConfigService service;
	
	@GetMapping()
	public List<DatabaseConfig> getAll() {
		return service.list();
	}
}
