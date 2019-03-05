package br.com.aas.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.aas.dto.QueryInfoDTO;
import br.com.aas.entities.QueryInfo;
import br.com.aas.services.QueryInfoService;

@RestController
@RequestMapping(value = "/QueryInfo")
public class QueryInfoResource {

	@Autowired
	private QueryInfoService service;

	@GetMapping()
	public ResponseEntity<List<QueryInfo>> get() {
		List<QueryInfo> querys = service.findAll();
		return ResponseEntity.ok(querys);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<QueryInfo> get(@PathVariable("id") long id) {
		QueryInfo query = service.findById(id);
		return ResponseEntity.ok(query);
	}

	@GetMapping(value = "/page")
	public @ResponseBody ResponseEntity<Page<QueryInfo>> get(@RequestParam(value = "page", defaultValue="0") Integer page,
			@RequestParam(value = "size", defaultValue = "24") Integer size,
			@RequestParam(value = "orderby", defaultValue = "id") String orderby,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<QueryInfo> pages = service.findAll(page, size, orderby, direction);
		return ResponseEntity.ok(pages);
	}
	
	@PreAuthorize("hasAnyRole('PROGRAMADOR')")
	@PostMapping
	public ResponseEntity<QueryInfo> post(@Valid @RequestBody QueryInfoDTO dto) {
		QueryInfo qi = dto.toEntity();
		service.save(qi);
		return ResponseEntity.ok(qi);
	}
	
	@PreAuthorize("hasAnyRole('PROGRAMADOR')")
	@PutMapping(value="{id}")
	public ResponseEntity<QueryInfo> put(@Valid @RequestBody QueryInfoDTO dto, @PathVariable("id") long id){
		QueryInfo qi = dto.toEntity();
		qi.setId(id);
		
		service.update(qi);
		return ResponseEntity.ok(qi);
	}

}
