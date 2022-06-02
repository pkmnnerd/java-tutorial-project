package com.jumpydoll.pkmnnerd.javatutorialproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class HelloController {

	public String index() {
		return "Greetings from Spring Boot!";
	}

}
