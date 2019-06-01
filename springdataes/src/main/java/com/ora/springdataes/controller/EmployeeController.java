package com.ora.springdataes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ora.springdataes.model.Employee;
import com.ora.springdataes.model.User;
import com.ora.springdataes.repo.EmployeeRepository;
import com.ora.springdataes.repo.UserRepository;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 4800, allowCredentials = "false")
public class EmployeeController {

	public static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeRepository employeeRepository;

	@PostMapping(value = "/employees")
	public ResponseEntity<?> createUser(@RequestBody Employee empDTO, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Employee : {}", empDTO);

		Employee employee = new Employee();
		employee.setName(empDTO.getName());

		employeeRepository.save(employee);

		List<Employee> employees = (ArrayList<Employee>) employeeRepository.findAll();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value = "/employees")
	public ResponseEntity<List<Employee>> listAllUsers(HttpServletResponse response) {

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

		List<Employee> empList = (ArrayList<Employee>) employeeRepository.findAll();
		if (empList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
	}

}
