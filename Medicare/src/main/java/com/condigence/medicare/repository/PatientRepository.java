package com.condigence.medicare.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.condigence.medicare.model.Patient;

public interface PatientRepository extends CrudRepository<Patient, Integer> {

	public List<Patient> findByFirstName(String firstName);

	public List<Patient> findByContactNo(Long contact);

	public List<Patient> findByEmail(String email);

}
