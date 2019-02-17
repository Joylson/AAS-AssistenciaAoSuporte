package br.com.aas.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.aas.dto.DatabaseConfigDTO;
import br.com.aas.entities.DatabaseConfig;
import br.com.aas.services.DataBaseConfigService;

@RestController
@RequestMapping(value = "/DataBaseConfig")
public class DataBaseConfigResource {

	@Autowired
	private DataBaseConfigService service;

	@GetMapping()
	public ResponseEntity<List<DatabaseConfig>> getAll() {
		return ResponseEntity.ok(service.list());
	}

	@GetMapping(params = "active")
	public ResponseEntity<List<DatabaseConfig>> getByActive(@RequestParam("active") boolean active) {
		List<DatabaseConfig> dbs = service.findByActive(active);
		return ResponseEntity.ok(dbs);
	}
	
	
	@GetMapping(value="/active/{id}")
	public ResponseEntity<DatabaseConfig> activeDataBase(@PathVariable("id") long id) {
		DatabaseConfig db = service.activeDataConfig(id);
		return ResponseEntity.ok(db);
	}

	@PostMapping
	public ResponseEntity<DatabaseConfig> post(@Valid @RequestBody DatabaseConfigDTO dbDTO) {
		DatabaseConfig db = dbDTO.toEntity();
		service.save(db);
		return ResponseEntity.ok(db);
	}

	@PutMapping(value="/{id}")
	public ResponseEntity<DatabaseConfig> put(@Valid @RequestBody DatabaseConfigDTO dbDTO, @PathVariable("id") long id) {
		DatabaseConfig db = dbDTO.toEntity();
		db.setId(id);
		service.update(db);
		return ResponseEntity.ok(db);
	}
}
