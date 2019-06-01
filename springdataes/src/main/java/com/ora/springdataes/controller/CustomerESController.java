package com.ora.springdataes.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ora.springdataes.model.Customer;
import com.ora.springdataes.repo.CustomerESRepository;

@RestController
@RequestMapping("/customer")
public class CustomerESController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	CustomerESRepository repository;

	@PostMapping("/add")
	public Customer addCustomer(@RequestBody Customer customer) {
		System.out.println("****####" + customer);
		return repository.save(customer);
	}

	/**
	 * Method to fetch the customer details on the basis of address.
	 * 
	 * @param address
	 * @return
	 */
	@GetMapping(value = "/findbyaddress/{employee-address}")
	public Iterable<Customer> getByDesignation(@PathVariable(name = "employee-address") String address) {
		return repository.findByAddress(address);
	}

	@GetMapping("/all")
	public List<Customer> getStudents() {
		Iterator<Customer> iterator = repository.findAll().iterator();
		List<Customer> customers = new ArrayList<Customer>();
		while (iterator.hasNext()) {
			customers.add(iterator.next());
		}
		return customers;
	}
	
	

}
