package com.cenfotec.rigs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cenfotec.rigs.models.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}