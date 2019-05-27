package com.condigence.medicare.repository;

import org.springframework.data.repository.CrudRepository;

import com.condigence.medicare.model.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Integer> {

}
