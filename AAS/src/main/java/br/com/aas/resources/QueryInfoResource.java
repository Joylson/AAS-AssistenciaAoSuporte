package br.com.aas.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aas.entities.QueryInfo;
import br.com.aas.services.QueryInfoService;

@RestController
@RequestMapping(value = "/QueryInfo")
public class QueryInfoResource {

	@Autowired
	private QueryInfoService service;

	@GetMapping
	public ResponseEntity<List<QueryInfo>> get() {
		List<QueryInfo> querys = service.findAll();
		return ResponseEntity.ok(querys);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<QueryInfo> get(@PathVariable("id") long id){
		QueryInfo query = service.findById(id);
		return ResponseEntity.ok(query);
	}

}
