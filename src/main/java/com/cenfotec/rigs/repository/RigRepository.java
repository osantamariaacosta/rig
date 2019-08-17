package com.cenfotec.rigs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cenfotec.rigs.models.Rig;

@Repository
public interface RigRepository extends JpaRepository<Rig, Long>{

}
