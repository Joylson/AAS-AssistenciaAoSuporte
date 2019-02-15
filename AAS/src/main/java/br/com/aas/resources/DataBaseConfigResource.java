package br.com.aas.resources;

import java.util.List;
import java.util.zip.DataFormatException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.aas.config.exception.model.BusinessException;
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
		try {
			return ResponseEntity.ok(service.findByActive(active));
		} catch (BusinessException | NullPointerException | DataFormatException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping
	public ResponseEntity<Void> post(@Valid @RequestBody DatabaseConfigDTO dbDTO) {
		DatabaseConfig db = dbDTO.toEntity();
		try {
			service.save(db);
			return ResponseEntity.ok().build();
		} catch (BusinessException bx) {
			return ResponseEntity.badRequest().build();
		}
	}
}
