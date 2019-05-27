package com.condigence.medicare.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.condigence.medicare.dto.UserDTO;
import com.condigence.medicare.model.Role;
import com.condigence.medicare.model.User;
import com.condigence.medicare.repository.RoleRepository;
import com.condigence.medicare.repository.UserRepository;
import com.condigence.medicare.util.CustomErrorType;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 4800, allowCredentials = "false")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	RoleRepository roleRepository;

	/**
	 * Creates a new User
	 * 
	 * @param userdto
	 * @param ucBuilder
	 * @return
	 */
	//@CrossOrigin(origins = "http://example.com")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping(value = "/users")
	public ResponseEntity<?> createUser(@RequestBody UserDTO userdto, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", userdto);

		if (StringUtils.isEmpty(userdto.getEmail()) && userRepository.findByEmail(userdto.getEmail()) != null) {
			logger.error("Unable to User. A User with name {} already	 exist", userdto.getName());
			return new ResponseEntity(
					new CustomErrorType("Unable to create. A Users with name " + userdto.getName() + " already exist."),
					HttpStatus.CONFLICT);
		}

		User user = new User();
		user.setName(userdto.getName());
		user.setLastName(userdto.getLastName());
		user.setPassword(passwordEncoder.encode(userdto.getPassword()));
		user.setEmail(userdto.getEmail());
		user.setActive(true);
		Set<Role> roles = new HashSet<Role>();
		Role role = roleRepository.findByRole("ADMIN");
		roles.add(role);
		user.setRoles(roles);
		userRepository.save(user);
		// HttpHeaders headers = new HttpHeaders();
		// headers.setLocation(ucBuilder.path("/api/addAppointment/{id}").buildAndExpand(user.getId()).toUri());
		// return new ResponseEntity<String>("User created", HttpStatus.CREATED);

		List<User> users = (ArrayList<User>) userRepository.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping(value = "/users/{id}")
	public ResponseEntity<?> updateAppointment(@PathVariable("id") long id, @RequestBody UserDTO userdto) {
		logger.info("Updating User Type with id {}", id);

		User user = userRepository.findOne(id);

		if (user == null) {
			logger.error("Unable to update. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate.Appointment with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		user.setName(userdto.getName());		
		user.setLastName(userdto.getLastName());
		//user.setPassword(passwordEncoder.encode(userdto.getPassword()));
		user.setEmail(userdto.getEmail());
		user.setActive(true);
		Set<Role> roles = new HashSet<Role>();
		Role role = roleRepository.findByRole("ADMIN");
		roles.add(role);
		user.setRoles(roles);
		userRepository.save(user);
		List<User> users = (ArrayList<User>) userRepository.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping(value = "/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User Type with id {}", id);

		User user = userRepository.findOne(id);
		if (user == null) {
			logger.error("Unable to delete. User Type with id {} not found.", id);
			return new ResponseEntity(
					new CustomErrorType("Unable to delete. User	 Type with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		userRepository.delete(id);
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
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		logger.info("Deleting All Users");
		userRepository.deleteAll();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

}
