package com.ora.springdataes;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ora.springdataes.repo.UserRepository;

@SpringBootApplication(scanBasePackages = { "com.ora.springdataes" })
public class SpringdataesApplication  {

	@Resource
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringdataesApplication.class, args);
	}
}
