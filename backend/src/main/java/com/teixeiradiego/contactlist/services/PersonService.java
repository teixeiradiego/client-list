package com.teixeiradiego.contactlist.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.teixeiradiego.contactlist.models.Person;
import com.teixeiradiego.contactlist.repositories.PersonRepository;

@Service
public class PersonService {

	@Value("${app.storage}")
	private String storagePath;
	
	@Value("${app.storage.people.photos}")
	private String photosStoragePath;
	
	@Autowired
	private PersonRepository repository;
	
	public Page<Person> find(String filter, Integer currentPage, Integer pageSize) {
		
		return repository.findByNameIgnoreCaseContaining(filter, PageRequest.of(currentPage, pageSize));
		
	}
	
	public InputStream getPhoto(Integer id) throws FileNotFoundException {
		
		Person person = repository.findById(id).orElse(null);
		
		File file = new File(storagePath + File.pathSeparator + photosStoragePath + File.pathSeparator + person.getId() + "png");
	
		if(!file.exists()) {
			return null;
		}		
		
		FileInputStream inputStream = new FileInputStream(file);
		
		return inputStream;
		
	} 
	
}
