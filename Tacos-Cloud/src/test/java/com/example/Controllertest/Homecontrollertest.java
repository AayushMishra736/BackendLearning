package com.example.Controllertest;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static
org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static
org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static
org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static
org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.Controller.Homecontroller;

@RunWith(SpringRunner.class)
@WebMvcTest(Homecontroller.class)
public class Homecontrollertest {
    
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testHomePage() throws Exception{
		 mockMvc.perform(get("/"))
		 .andExpect(status().isOk())
		 .andExpect(view().name("home"))
		 .andExpect(content().string(
		 containsString("Welcome to...")));
		 }
	}

