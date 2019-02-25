package br.com.aas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aas.services.QueryResultService;

@RestController
@RequestMapping(value = "/Query")
public class QueryResultResource {

	@Autowired
	private QueryResultService service;

	@PostMapping
	public ResponseEntity<Object[][]> get(@RequestBody String sql) {
		Object[][] qr = service.validQuery(sql, 1, 20);
		return ResponseEntity.ok(qr);
	}
	
}
