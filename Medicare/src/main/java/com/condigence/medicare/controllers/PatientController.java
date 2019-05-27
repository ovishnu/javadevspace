package com.condigence.medicare.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.condigence.medicare.model.Patient;
import com.condigence.medicare.repository.PatientRepository;
import com.condigence.medicare.util.CustomErrorType;

@RestController
@RequestMapping("/patient")
public class PatientController {

	public static final Logger logger = LoggerFactory.getLogger(DoctorController.class);

	@Autowired
	PatientRepository patientRepository;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> createPatient(@RequestBody Patient patient, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Patient : {}", patient);

		if (StringUtils.isEmpty(patient.getEmail()) && patientRepository.findByEmail(patient.getEmail()) != null) {
			logger.error("Unable to User. A User with name {} already	 exist", patient.getFirstName());
			
			return new ResponseEntity(
					new CustomErrorType("Unable to create. A Users with name " + patient.getFirstName() + " already exist."),
					HttpStatus.CONFLICT);
		}
		patientRepository.save(patient);

		HttpHeaders headers = new HttpHeaders();
		// headers.setLocation(ucBuilder.path("/api/addAppointment/{id}").buildAndExpand(patient.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPatient(@PathVariable("id") int id) {
		logger.info("Fetching Patient with id {}", id);
		Patient patient = patientRepository.findOne(id);
		if (patient == null) {
			logger.error("User Type with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Patient with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Patient>(patient, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePatient(@PathVariable("id") int id, @RequestBody Patient patient) {
		logger.info("Updating Patient with id {}", id);

		//Patient patient = patientRepository.findOne(id);

		/*if (patient1 == null) {
			logger.error("Unable to update. Patient with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Patient with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}*/

		//patient.setContactNo(patient1.getContactNo());

		patientRepository.save(patient);
		return new ResponseEntity<Patient>(patient, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePatient(@PathVariable("id") int id) {
		logger.info("Fetching & Deleting Patient with id {}", id);

		Patient patient = patientRepository.findOne(id);
		if (patient == null) {
			logger.error("Unable to delete. patient with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete.patient with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		patientRepository.delete(id);
		return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Patient>> listAllPatients() {
		List<Patient> patientList = (ArrayList<Patient>) patientRepository.findAll();
		if (patientList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Patient>>(patientList, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
	public ResponseEntity<Patient> deleteAllPatient() {
		logger.info("Deleting All Appointments");
		patientRepository.deleteAll();
		return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
	}

}
