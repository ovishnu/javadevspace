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

import com.condigence.medicare.dto.AppointmentDTO;
import com.condigence.medicare.model.Appointment;
import com.condigence.medicare.repository.AppointmentRepository;
import com.condigence.medicare.services.AppointmentService;
import com.condigence.medicare.util.CustomErrorType;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	public static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

	@Autowired
	AppointmentRepository appointmentRepository;

	@Autowired
	AppointmentService appointmentService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> createAppointment(@RequestBody AppointmentDTO appointment,
			UriComponentsBuilder ucBuilder) {
		logger.info("Creating Appointment : {}", appointment);
		appointmentService.save(appointment);

		HttpHeaders headers = new HttpHeaders();
		// headers.setLocation(ucBuilder.path("/home"));
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAppointment(@PathVariable("id") int id) {
		logger.info("Fetching Appointment with id {}", id);
		Appointment appointment = appointmentRepository.findOne(id);
		if (appointment == null) {
			logger.error("User Type with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Appointment with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAppointment(@PathVariable("id") int id, @RequestBody Appointment appointment1) {
		logger.info("Updating User Type with id {}", id);

		Appointment appointment = appointmentRepository.findOne(id);

		if (appointment1 == null) {
			logger.error("Unable to update. Appointment with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Appointment with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		appointmentRepository.save(appointment1);
		return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAppointment(@PathVariable("id") int id) {
		logger.info("Fetching & Deleting User Type with id {}", id);

		Appointment appointment = appointmentRepository.findOne(id);
		if (appointment == null) {
			logger.error("Unable to delete. User Type with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. User Type with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		appointmentRepository.delete(id);
		return new ResponseEntity<Appointment>(HttpStatus.NO_CONTENT);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<AppointmentDTO>> listAllAppointments() {
		List<AppointmentDTO> appointmentList = (ArrayList<AppointmentDTO>) appointmentService.findAppointments();

		if (appointmentList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<AppointmentDTO>>(appointmentList, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
	public ResponseEntity<Appointment> deleteAllUserType() {
		logger.info("Deleting All UserTypes");
		appointmentRepository.deleteAll();
		return new ResponseEntity<Appointment>(HttpStatus.NO_CONTENT);
	}

}
