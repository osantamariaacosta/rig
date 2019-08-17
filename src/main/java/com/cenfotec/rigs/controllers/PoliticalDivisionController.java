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

import com.cenfotec.rigs.models.PoliticalDivision;
import com.cenfotec.rigs.repository.PoliticalDivisionRepository;


@RestController
@RequestMapping({"/political_divisions"})
public class PoliticalDivisionController {
	private PoliticalDivisionRepository repository;

	PoliticalDivisionController(PoliticalDivisionRepository politicalDivisionRepository) {
		this.repository = politicalDivisionRepository;
	}
	
	@GetMapping
	public List findAll(){
		return repository.findAll();
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<PoliticalDivision> findById(@PathVariable long id){
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record)).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public PoliticalDivision create(@RequestBody PoliticalDivision entity){
		return repository.save(entity);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<PoliticalDivision> update (@PathVariable("id") long id,	@RequestBody PoliticalDivision entity){
		return repository.findById(id).map(obj -> {
			obj.setName(entity.getName());
			obj.setType(entity.getType());
			obj.setId_rig(entity.getId_rig());
			PoliticalDivision updated = repository.save(entity);
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
