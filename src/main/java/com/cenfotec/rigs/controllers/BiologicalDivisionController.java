package com.cenfotec.rigs.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.rigs.models.BiologicalDivision;
import com.cenfotec.rigs.repository.BiologicalDivisionRepository;

@RestController
@RequestMapping({"/biological_divisions"})
public class BiologicalDivisionController {
	private BiologicalDivisionRepository repository;

	BiologicalDivisionController(BiologicalDivisionRepository biologicalDivisionRepository) {
		this.repository = biologicalDivisionRepository;
	}
	
	@GetMapping
	public List findAll(){
		return repository.findAll();
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<BiologicalDivision> findById(@PathVariable long id){
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record)).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public BiologicalDivision create(@RequestBody BiologicalDivision entity){
		return repository.save(entity);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<BiologicalDivision> update (@PathVariable("id") long id,	@RequestBody BiologicalDivision entity){
		return repository.findById(id).map(obj -> {
			obj.setName(entity.getName());
			obj.setId_rig(entity.getId_rig());
			BiologicalDivision updated = repository.save(entity);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity<?> delete(@PathVariable("id")	long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}		
}
