package com.teixeiradiego.clientlist.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.teixeiradiego.clientlist.models.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

	Page<Client> findByNameIgnoreCaseContaining(String name, Pageable pageable);
	
}
