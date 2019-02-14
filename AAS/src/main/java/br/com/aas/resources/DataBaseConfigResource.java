package br.com.aas.resources;

import java.util.List;
import java.util.zip.DataFormatException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.aas.config.exception.model.BusinessException;
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
	
	@GetMapping(params="active")
	public List<DatabaseConfig> getByActive(@RequestParam("active") boolean active) {
		try {
			return service.findByActive(active);
		} catch (BusinessException | NullPointerException | DataFormatException e) {
			e.printStackTrace();
			return null;
		}
	}	 
	
}
