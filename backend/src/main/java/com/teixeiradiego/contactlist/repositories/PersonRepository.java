package com.teixeiradiego.contactlist.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.teixeiradiego.contactlist.models.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

	Page<Person> findByNameIgnoreCaseContaining(String name, Pageable pageable);
	
}
