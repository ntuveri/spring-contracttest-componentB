package com.example.componentB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("alligators")
public class AlligatorController {

	private AlligatorRepository repository;

	@Autowired
	public AlligatorController(AlligatorRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/{name}")
	public ResponseEntity<Alligator> getAlligators(@PathVariable String name) {
		Alligator alligator = this.repository.findByName(name);
		if(alligator == null) {
			return new ResponseEntity<Alligator>(alligator, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Alligator>(alligator, HttpStatus.OK);
	}
}
