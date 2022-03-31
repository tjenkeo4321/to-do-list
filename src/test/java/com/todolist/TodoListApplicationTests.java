package com.todolist;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.dto.TodoListDto;
import com.todolist.service.TodoListService;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@SpringBootTest

@ExtendWith(MockitoExtension.class)

@TestMethodOrder(OrderAnnotation.class)

class TodoListApplicationTests {
	
	private static ObjectMapper mapper = new ObjectMapper();

	private MockMvc mockmvc;

	@Autowired
	private WebApplicationContext context;

	@MockBean
	private TodoListService todoListService;
	
	public void init() {
		if (mockmvc == null)
			mockmvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void getAllToDoListsTest() throws Exception {
		String uri = "/todoList/getAllTodoLists";

		init();
		MvcResult result = mockmvc.perform(get(uri)).andExpect(status().isOk()).andReturn();

		log.info(result.getResponse().getContentAsString());
	}
	
	@Test
	public void getAllTasksTest() throws Exception {
		String uri = "/todoList/getAllTasks";

		init();
		MvcResult result = mockmvc.perform(get(uri)).andExpect(status().isOk()).andReturn();

		log.info(result.getResponse().getContentAsString());
	}
	
	@Test
	public void getAllStatesTest() throws Exception {
		String uri = "/todoList/getAllStates";

		init();
		MvcResult result = mockmvc.perform(get(uri)).andExpect(status().isOk()).andReturn();

		log.info(result.getResponse().getContentAsString());
	}

	@Test
	public void postCreateTodoListTest() throws Exception {
		init();

		String uri = "/todoList/add";
		TodoListDto todoListDto = new TodoListDto();
		todoListDto.setName("Test Todolist");
		todoListDto.setDescription("Test todolist description");
		
		Mockito.when(todoListService.createTodolist(ArgumentMatchers.any())).thenReturn(todoListDto);	
		
		String json = mapper.writeValueAsString(todoListDto);
		  mockmvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
		    .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		    .andExpect(jsonPath("$.name", Matchers.contains("Test Todolist")))
		    .andExpect(jsonPath("$.description", Matchers.equalTo("Test todolist description")));
	  }
	
	@Test
	public void addTaskTest() throws Exception {
		init();

		String uri = "/todoList/getAllStates";
		TodoListDto todoListDto = new TodoListDto();
		todoListDto.setName("Test Todolist");
		todoListDto.setDescription("Test todolist description");
		
		Mockito.when(todoListService.createTodolist(ArgumentMatchers.any())).thenReturn(todoListDto);	
		
		String json = mapper.writeValueAsString(todoListDto);
		  mockmvc.perform(post("/todoList/add").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
		    .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		    .andExpect(jsonPath("$.name", Matchers.contains("Test Todolist")))
		    .andExpect(jsonPath("$.description", Matchers.equalTo("Test todolist description")));
	  }

	
	
}
