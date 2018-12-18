package com.teixeiradiego.contactlist.unit.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.teixeiradiego.contactlist.models.Person;
import com.teixeiradiego.contactlist.repositories.PersonRepository;
import com.teixeiradiego.contactlist.services.PersonService;

@ActiveProfiles("unit-test")
@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonServiceTest {

	@Autowired
	private PersonService service;
	
	@MockBean
	private PersonRepository repository;

	private static Page<Person> page;
	private static Integer pageSize = 10;
	private static Long totalElements = 15L;
	private static String name = "Person";
	private static String url = "http://url.com";
	
	@BeforeClass
	public static void beforeClass() {
		
		List<Person> people = new ArrayList<>();
		for(int i = 1; i <= 10; i++) {
			
			Person person = new Person();
			person.setId(i);
			person.setName(name);
			person.setPhotoUrl(url);
			
			people.add(person);
			
		}
		
		page = new PageImpl<>(people, PageRequest.of(0, pageSize), totalElements);
		
	}
	
	@Before
	public void before() {
		
		doReturn(page).when(repository).findByNameIgnoreCaseContaining(
				ArgumentMatchers.anyString(), 
				ArgumentMatchers.any(Pageable.class));
		
	}

	@Test
	public void shouldFindUnpagedIfNoPageNumber() {
		
		service.find(name, null, pageSize);
		
		verify(repository).findByNameIgnoreCaseContaining(ArgumentMatchers.anyString(), 
				ArgumentMatchers.eq(Pageable.unpaged()));
		
	}
	
	@Test
	public void shouldFindUnpagedIfNoPageSize() {
		
		service.find(name, 0, null);
		
		verify(repository).findByNameIgnoreCaseContaining(ArgumentMatchers.anyString(), 
				ArgumentMatchers.eq(Pageable.unpaged()));
		
	}
	
	@Test
	public void shouldFindPagedIfPageSizeAndPageNumber() {
		
		service.find(name, 0, pageSize);
		
		verify(repository).findByNameIgnoreCaseContaining(ArgumentMatchers.anyString(), 
				ArgumentMatchers.eq(PageRequest.of(0, pageSize)));
		
	}
	
	@Test
	public void shouldFindWithFilter() {
		
		service.find(name, null, null);
		
		verify(repository).findByNameIgnoreCaseContaining(ArgumentMatchers.eq(name), 
				ArgumentMatchers.any(Pageable.class));
		
	}
	
	@Test
	public void shouldReturnThePageFromRepository() {
		
		Page<Person> returnPage = service.find(name, null, null);
		
		assertNotNull(returnPage);
		assertEquals(totalElements, Long.valueOf(returnPage.getTotalElements()));
		assertNotNull(returnPage.getContent());
		assertEquals(pageSize, Integer.valueOf(returnPage.getContent().size()));
		
	}
	
}
