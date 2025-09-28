package com.chatt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

	@GetMapping
	public String hello() {
		return "Hello From Docker";
	}
	@GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return "Hello " + name + " bhai";
    }
	
}
