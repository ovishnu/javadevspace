package com.condigence.medicare.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.condigence.medicare.model.Commission;
import com.condigence.medicare.repository.CommissionRepository;
import com.condigence.medicare.util.CustomErrorType;

@RestController
@RequestMapping("/commission")
public class CommissionController {

	public static final Logger logger = LoggerFactory.getLogger(DoctorController.class);

	@Autowired
	CommissionRepository commissionRepository;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> createCommission(@RequestBody Commission commission, UriComponentsBuilder ucBuilder) {
		logger.info("Creating commission : {}", commission);
		commissionRepository.save(commission);
		HttpHeaders headers = new HttpHeaders();
		// headers.setLocation(ucBuilder.path("/api/addAppointment/{id}").buildAndExpand(patient.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCommission(@PathVariable("id") int id) {
		logger.info("Fetching Patient with id {}", id);
		Commission commission = commissionRepository.findOne(id);
		if (commission == null) {
			logger.error("Commission with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Commission with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Commission>(commission, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCommission(@PathVariable("id") int id, @RequestBody Commission commission1) {
		logger.info("Updating commission with id {}", id);

		Commission commission = commissionRepository.findOne(id);

		if (commission1 == null) {
			logger.error("Unable to update. Commission with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Commission with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		commissionRepository.save(commission);
		return new ResponseEntity<Commission>(commission, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePatient(@PathVariable("id") int id) {
		logger.info("Fetching & Deleting commission with id {}", id);

		Commission commission = commissionRepository.findOne(id);
		if (commission == null) {
			logger.error("Unable to delete. commission with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete.commission with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		commissionRepository.delete(id);
		return new ResponseEntity<Commission>(HttpStatus.NO_CONTENT);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Commission>> listAllCommissions() {
		List<Commission> patientList = (ArrayList<Commission>) commissionRepository.findAll();
		if (patientList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Commission>>(patientList, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
	public ResponseEntity<Commission> deleteAllCommission() {
		logger.info("Deleting All Commissions");
		commissionRepository.deleteAll();
		return new ResponseEntity<Commission>(HttpStatus.NO_CONTENT);
	}

}
