package br.com.aas.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aas.dto.UserDTO;
import br.com.aas.entities.User;
import br.com.aas.services.UserService;

@PreAuthorize("hasAnyRole('PROGRAMADOR')")
@RestController
@RequestMapping(value = "/User")
public class UserResource {

	@Autowired
	private UserService service;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping
	public ResponseEntity<List<User>> get() {
		List<User> users = service.findAll();
		return ResponseEntity.ok(users);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> get(@PathVariable("id") long id) {
		User user = service.findById(id);
		return ResponseEntity.ok(user);
	}

	@PostMapping
	public ResponseEntity<User> post(@Valid @RequestBody UserDTO dto) {
		dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
		User user = dto.toEntity();
		
		user = service.save(user);
		return ResponseEntity.ok(user);
	}

	@PutMapping(value="/{id}")
	public ResponseEntity<User> put(@Valid @RequestBody UserDTO dto, @PathVariable("id") long id) {
		User user = dto.toEntity();
		
		user.setId(id);
		user = service.update(user);
		return ResponseEntity.ok(user);
	}
}
