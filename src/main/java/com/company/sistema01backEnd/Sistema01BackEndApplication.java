package com.company.sistema01backEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan()
public class Sistema01BackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sistema01BackEndApplication.class, args);
	}

}
