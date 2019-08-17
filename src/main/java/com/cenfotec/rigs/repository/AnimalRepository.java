package com.cenfotec.rigs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfotec.rigs.models.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
