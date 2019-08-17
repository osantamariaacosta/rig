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

import com.cenfotec.rigs.models.Rig;
import com.cenfotec.rigs.repository.RigRepository;


@RestController
@RequestMapping({"/rigs"})
public class RigController {
	private RigRepository  repository;
	
	RigController(RigRepository rigRepository){
		this.repository = rigRepository;
	}
	
	@GetMapping
	public List findAll(){
		return repository.findAll();
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Rig> findById(@PathVariable long id){
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record)).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Rig create(@RequestBody Rig entity){
		return repository.save(entity);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Rig> update (@PathVariable("id") long id,	@RequestBody Rig entity){
		return repository.findById(id).map(rig -> {
			rig.setName(entity.getName());
			rig.setContinent(entity.getContinent());
			rig.setLand_surface(entity.getLand_surface());
			rig.setSea_surface(entity.getSea_surface());
			Rig updated = repository.save(entity);
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
