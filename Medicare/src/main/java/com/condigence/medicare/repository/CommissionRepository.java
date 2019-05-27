package com.condigence.medicare.repository;

import org.springframework.data.repository.CrudRepository;

import com.condigence.medicare.model.Commission;

public interface CommissionRepository extends CrudRepository<Commission, Integer> {

	public Commission findById(String id);
}
