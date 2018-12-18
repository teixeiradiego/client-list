package com.teixeiradiego.contactlist.unit.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.teixeiradiego.contactlist.models.Person;
import com.teixeiradiego.contactlist.services.PersonService;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PersonService service;
	
	private static Page<Person> page;
	private static Integer pageSize = 10;
	private static Integer totalElements = 15;
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
		
		doReturn(page).when(service).find(ArgumentMatchers.anyString(), 
				ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt());
		
		
	}
	
	@Test
	public void shouldAcceptNoParams() throws Exception {
		
		this.mockMvc.perform(get("/people"))
				.andExpect(status().isOk());
		
		verify(service, only()).find(ArgumentMatchers.eq(""), 
				ArgumentMatchers.isNull(), ArgumentMatchers.isNull());
		
	}
	
	@Test
	public void shouldAcceptOnlyFilter() throws Exception {
		
		this.mockMvc.perform(get("/people")
				.param("filter", "Person"))
				.andExpect(status().isOk());
		
		verify(service, only()).find(ArgumentMatchers.eq("Person"), 
				ArgumentMatchers.isNull(), ArgumentMatchers.isNull());
		
	}
	
	@Test
	public void shouldAcceptOnlyPaging() throws Exception {
		
		this.mockMvc.perform(get("/people")
				.param("pageNumber", "1")
				.param("pageSize", pageSize.toString()))
				.andExpect(status().isOk());
		
		verify(service, only()).find(ArgumentMatchers.eq(""), 
				ArgumentMatchers.eq(1), ArgumentMatchers.eq(pageSize));
		
	}
	
	@Test
	public void shouldAcceptAllParams() throws Exception {
		
		this.mockMvc.perform(get("/people")
				.param("filter", "Person")
				.param("pageNumber", "1")
				.param("pageSize", pageSize.toString()))
				.andExpect(status().isOk());
		
		verify(service, only()).find(ArgumentMatchers.eq("Person"), 
				ArgumentMatchers.eq(1), ArgumentMatchers.eq(pageSize));
		
	}
	
	@Test
	public void shouldReturnPersonPage() throws Exception {
		
		this.mockMvc.perform(get("/people")
				.param("pageNumber", "1")
				.param("pageSize", "10"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalElements").exists())
				.andExpect(jsonPath("$.totalElements").isNotEmpty())
				.andExpect(jsonPath("$.totalElements").isNumber())
				.andExpect(jsonPath("$.totalElements").value(totalElements))
				.andExpect(jsonPath("$.content").exists())
				.andExpect(jsonPath("$.content").isNotEmpty())
				.andExpect(jsonPath("$.content").isArray())
				.andExpect(jsonPath("$.content").value(hasSize(pageSize)))
				.andExpect(jsonPath("$.content[0].id").exists())
				.andExpect(jsonPath("$.content[0].id").isNotEmpty())
				.andExpect(jsonPath("$.content[0].id").isNumber())
				.andExpect(jsonPath("$.content[0].name").exists())
				.andExpect(jsonPath("$.content[0].name").isNotEmpty())
				.andExpect(jsonPath("$.content[0].name").isString())
				.andExpect(jsonPath("$.content[0].name").value(name))
				.andExpect(jsonPath("$.content[0].photoUrl").exists())
				.andExpect(jsonPath("$.content[0].photoUrl").isNotEmpty())
				.andExpect(jsonPath("$.content[0].photoUrl").isString())
				.andExpect(jsonPath("$.content[0].photoUrl").value(url));
		
	}
	
}
