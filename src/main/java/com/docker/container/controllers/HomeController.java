package com.docker.container.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

	@GetMapping(value="/home")
	public String getWelcome() {
		return "Hello OAuth";
	}
	
	
	@GetMapping(value="/home/example")
	public void getWelcome2() {
		System.err.println("JUST EXAMPLE");
	}


}
