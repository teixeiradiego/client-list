package com.teixeiradiego.clientlist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teixeiradiego.clientlist.models.Client;
import com.teixeiradiego.clientlist.services.ClientService;

@RestController
@RequestMapping(path = "clients")
public class ClientController {

	@Autowired
	private ClientService service;
	
	@GetMapping
	public Page<Client> findClients(@RequestParam String filter, @RequestParam Integer currentPage, 
			@RequestParam Integer pageSize) {
		
		return service.findClients(filter, currentPage, pageSize); 
		
	}
	
}
