package com.teixeiradiego.contactlist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teixeiradiego.contactlist.models.Person;
import com.teixeiradiego.contactlist.services.PersonService;

@RestController
@RequestMapping(path = "people")
public class PersonController {

	@Autowired
	private PersonService service;
	
	@GetMapping
	public Page<Person> find(
			@RequestParam(defaultValue="") String filter, 
			@RequestParam(required=false) Integer pageNumber, 
			@RequestParam(required=false) Integer pageSize) {
		
		return service.find(filter, pageNumber, pageSize); 
		
	}
	
}
