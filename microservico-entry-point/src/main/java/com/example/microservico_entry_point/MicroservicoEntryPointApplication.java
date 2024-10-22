package com.example.microservico_entry_point;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroservicoEntryPointApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicoEntryPointApplication.class, args);
	}

}
