package com.condigence.medicare.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.condigence.medicare.dto.AppointmentDTO;
import com.condigence.medicare.model.Appointment;
import com.condigence.medicare.model.Patient;
import com.condigence.medicare.model.User;
import com.condigence.medicare.repository.AppointmentRepository;
import com.condigence.medicare.repository.PatientRepository;
import com.condigence.medicare.repository.UserRepository;
import com.condigence.medicare.services.AppointmentService;

@RestController
@RequestMapping("/search")
public class SearchController {

	public static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	
	@Autowired
	AppointmentService appointmentService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/{entity}/by/{attribute}/{string}", method = RequestMethod.GET)
	public ResponseEntity<List<?>> listAllAppointments(@PathVariable("entity") String entity,
			@PathVariable("attribute") String attribute, @PathVariable("string") String string) {

		// entity = "appointment";
		// attribute = "contactNo";
		// string = "9742086574";
		// http://localhost:8080/search/appointment/by/contactNo/9742083546

		List<?> objList = null;

		if (entity.equalsIgnoreCase("appointment")) {
			objList = new ArrayList<>();
			if (attribute.equalsIgnoreCase("name")) {
				objList = (ArrayList<AppointmentDTO>) appointmentService.findAppointmentsByPatientName(string);
			} else if (attribute.equalsIgnoreCase("contactNo")) {
				objList = (ArrayList<AppointmentDTO>) appointmentService.findAppointmentsByPatientContact(string);
			}
			return new ResponseEntity<List<?>>(objList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<?>>(objList, HttpStatus.NOT_FOUND);
		}

	}

}
