package com.condigence.medicare.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.condigence.medicare.model.ServiceType;
import com.condigence.medicare.repository.ServiceTypeRepository;
import com.condigence.medicare.util.CustomErrorType;

@RestController
@RequestMapping("/servicetype")
public class ServiceTypeController {

	public static final Logger logger = LoggerFactory.getLogger(ServiceTypeController.class);

	@Autowired
	ServiceTypeRepository serviceTypeRepository;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<ServiceType>> listAllServiceTypes() {
		List<ServiceType> serviceTypes = (ArrayList<ServiceType>) serviceTypeRepository.findAll();
		if (serviceTypes.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ServiceType>>(serviceTypes, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getServiceType(@PathVariable("id") int id) {
		logger.info("Fetching Service Type with id {}", id);
		ServiceType servicetype = serviceTypeRepository.findOne(id);
		if (servicetype == null) {
			logger.error("Service Type with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("ServiceType with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ServiceType>(servicetype, HttpStatus.OK);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<List<ServiceType>> createServiceType(@RequestBody ServiceType servicetype,
			UriComponentsBuilder ucBuilder) {
		logger.info("Creating Service Type : {}", servicetype);
		serviceTypeRepository.save(servicetype);
		List<ServiceType> serviceTypes = (ArrayList<ServiceType>) serviceTypeRepository.findAll();
		return new ResponseEntity<List<ServiceType>>(serviceTypes, HttpStatus.OK);
	}

	//
	// // ------------------- Update a Service Type
	// // ------------------------------------------------
	//
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<List<ServiceType>> updateServiceType(@PathVariable("id") int id,
			@RequestBody ServiceType serviceType) {
		logger.info("Updating Service Type with id {}", id);
		ServiceType serviceTypeNew = serviceTypeRepository.findOne(id);

		serviceTypeNew.setName(serviceType.getName());
		serviceTypeNew.setPrice(serviceType.getPrice());
		serviceTypeRepository.save(serviceTypeNew);
		List<ServiceType> serviceTypes = (ArrayList<ServiceType>) serviceTypeRepository.findAll();
		return new ResponseEntity<List<ServiceType>>(serviceTypes, HttpStatus.OK);
	}

	// // ------------------- Delete a Service Type -------------------------
	/**
	 * This method will delete service type by it's id value.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<List<ServiceType>> deleteServiceType(@PathVariable("id") int id) {
		logger.info("Fetching & Deleting Service Type with id {}", id);

		ServiceType serviceType = serviceTypeRepository.findOne(id);
		serviceTypeRepository.delete(serviceType.getId());
		List<ServiceType> serviceTypes = (ArrayList<ServiceType>) serviceTypeRepository.findAll();
		if (serviceTypes.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ServiceType>>(serviceTypes, HttpStatus.OK);
	}

	// // ------------------- Delete All ServiceTypes Type -----------------

	@RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
	public ResponseEntity<List<ServiceType>> deleteAllServiceType() {
		logger.info("Deleting All ServiceTypes");
		serviceTypeRepository.deleteAll();
		List<ServiceType> serviceTypes = (ArrayList<ServiceType>) serviceTypeRepository.findAll();
		if (serviceTypes.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ServiceType>>(serviceTypes, HttpStatus.OK);
	}

}
