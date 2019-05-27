package com.ora.springdataes.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ora.springdataes.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	
}
