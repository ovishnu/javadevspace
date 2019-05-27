package com.condigence.medicare.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.condigence.medicare.model.UserType;

public interface UserTypeRepository extends CrudRepository<UserType, Integer> {

	List<UserType> findById(String id);

	List<UserType> findByType(String type);
}
