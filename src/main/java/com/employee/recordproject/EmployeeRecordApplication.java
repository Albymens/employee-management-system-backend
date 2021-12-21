package com.employee.recordproject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class EmployeeRecordApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmployeeRecordApplication.class, args);
		log.info("server is running!!!");
	}

}
