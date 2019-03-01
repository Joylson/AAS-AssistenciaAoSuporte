package br.com.aas.resources;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.aas.entities.QueryResult;
import br.com.aas.services.QueryResultService;

@RestController
@RequestMapping(value = "/Query")
public class QueryResultResource {

	@Autowired
	private QueryResultService service;

	@PostMapping
	public ResponseEntity<Object[][]> get(@Valid @RequestBody Select select) {
		Object[][] qr = service.validQuery(select.getSql(), 1, 20);
		return ResponseEntity.ok(qr);
	}

	@GetMapping
	public ResponseEntity<QueryResult> get(@RequestParam(value = "id") long id,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "30") int rows) {
		QueryResult qr = service.returnQuery(id, page, rows);
		return ResponseEntity.ok(qr);
	}

	static class Select {

		@Length(max = 9000, message = "Consulta excede o limite de 9000 caracter!!")
		private String sql;

		public Select() {

		}

		public String getSql() {
			return sql;
		}

		public void setSql(String sql) {
			this.sql = sql;
		}

	}

}
