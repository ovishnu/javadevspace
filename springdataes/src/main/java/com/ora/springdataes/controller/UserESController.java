package com.ora.springdataes.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ora.springdataes.model.UserDoc;
import com.ora.springdataes.repo.UserESRepository;

@RestController
public class UserESController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserESRepository repository;



	@PostMapping("/user/add")
	public UserDoc addStudent(@RequestBody UserDoc user ){
		System.out.println("********************************#################"+user);
		return repository.save(user);
	}
//	
	@GetMapping("/user/all")
	public List<UserDoc> getStudents(){
		 Iterator<UserDoc> iterator= repository.findAll().iterator();
		 List<UserDoc> students=new ArrayList<UserDoc>();
		 while(iterator.hasNext()){
			 students.add(iterator.next());
		 }
		 return students;
	}
//	
//
//	@GetMapping("/user/{id}")
//	public Optional<UserDoc> getStudent(@PathVariable Long id){
//		return repository.findById(id);
//	}
//	
//	
//	@PutMapping("/user/{id}")
//	   public UserDoc updateStudent(@PathVariable Long id,@RequestBody UserDoc user){
//		   Optional<UserDoc> std= repository.findById(id);
//		   if(std.isPresent()){
//			   UserDoc s=std.get();
//			   s.setName(user.getName());
//		   return repository.save(s);
//		   }
//		   else
//			   return null;
//	   }
//	
//	@DeleteMapping("/user/{id}")
//	   public String deleteStudent(@PathVariable Long id){
//		  repository.deleteById(id);
//		  return "Document Deleted";
//	   }
//
//	
}
