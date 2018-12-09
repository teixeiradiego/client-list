package com.teixeiradiego.clientlist.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.teixeiradiego.clientlist.models.Client;
import com.teixeiradiego.clientlist.repositories.ClientRepository;

@Service
public class ClientService {

	@Value("${app.storage}")
	private String storagePath;
	
	@Value("${app.storage.clients.photos}")
	private String photosStoragePath;
	
	@Autowired
	private ClientRepository repository;
	
	public Page<Client> findClients(String filter, Integer currentPage, Integer pageSize) {
		
		return repository.findByNameIgnoreCaseContaining(filter, PageRequest.of(currentPage, pageSize));
		
	}
	
	public InputStream getPhoto(Integer id) throws FileNotFoundException {
		
		Client client = repository.findById(id).orElse(null);
		
		File file = new File(storagePath + File.pathSeparator + photosStoragePath + File.pathSeparator + client.getId() + "png");
	
		if(!file.exists()) {
			return null;
		}		
		
		FileInputStream inputStream = new FileInputStream(file);
		
		return inputStream;
		
	} 
	
}
