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

import com.ora.springdataes.model.User;
import com.ora.springdataes.repo.UserRepository;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 4800, allowCredentials = "false")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRepository userRepository;

	@PostMapping(value = "/users")
	public ResponseEntity<?> createUser(@RequestBody User userdto, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", userdto);

		User user = new User();
		user.setName(userdto.getName());

		user.setEmail(userdto.getEmail());

		userRepository.save(user);

		List<User> users = (ArrayList<User>) userRepository.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);

	}

	@PutMapping(value = "/users/{id}")
	public ResponseEntity<?> updateAppointment(@PathVariable("id") long id, @RequestBody User userdto) {
		logger.info("Updating User Type with id {}", id);

		Optional<User> user = userRepository.findById(id);

		user.get().setName(userdto.getName());
		user.get().setEmail(userdto.getEmail());

		userRepository.save(user.get());
		List<User> users = (ArrayList<User>) userRepository.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@DeleteMapping(value = "/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User Type with id {}", id);

		userRepository.deleteById(id);
		List<User> users = (ArrayList<User>) userRepository.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value = "/users")
	public ResponseEntity<List<User>> listAllUsers(HttpServletResponse response) {

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

		List<User> userList = (ArrayList<User>) userRepository.findAll();
		if (userList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

	@GetMapping(value = "/users/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
		logger.info("Fetching user with id {}", id);
		Optional<User> user = userRepository.findById(id);
		return new ResponseEntity<User>(user.get(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/users/")
	public ResponseEntity<User> deleteAllUsers() {
		logger.info("Deleting All Users");
		userRepository.deleteAll();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

}
