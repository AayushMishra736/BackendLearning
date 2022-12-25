package com.example.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Homecontroller {

	@GetMapping("/get")
	public String Home() {
		return "This is homepage.";
	}
	
}
