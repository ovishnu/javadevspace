package com.condigence.medicare.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.condigence.medicare.model.Appointment;
import com.condigence.medicare.model.Patient;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {	
	
	List<Appointment> findBypatient(Patient patient);
	
	List<Appointment> findByModifiedDateTimeBetween(Timestamp startDate,Timestamp endDate );
	
}
