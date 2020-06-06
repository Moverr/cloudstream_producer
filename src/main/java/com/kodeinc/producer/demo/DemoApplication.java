package com.kodeinc.producer.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DemoApplication {
    
    @PostMapping("greet/{name}")
    public void publish(@PathVariable String name){
        String greeting =  "Hello "+name;
    }

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
