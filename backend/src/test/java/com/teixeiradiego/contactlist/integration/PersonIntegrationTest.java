package com.teixeiradiego.contactlist.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("integration-test")
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PersonIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testFind() throws Exception {
		
		this.mockMvc.perform(get("/people")
				.param("pageNumber", "1")
				.param("pageSize", "10"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.totalElements", is(817)))
				.andExpect(jsonPath("$.content", hasSize(10)))
				.andExpect(jsonPath("$.content[0].id", not(isEmptyString())));
		
	}
	
}

