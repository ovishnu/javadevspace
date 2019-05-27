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

import com.condigence.medicare.model.Invoice;
import com.condigence.medicare.repository.InvoiceRepository;
import com.condigence.medicare.util.CustomErrorType;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);	
	
	@Autowired
	InvoiceRepository invoiceRepository;
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody Invoice invoice, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", invoice);
		invoiceRepository.save(invoice);
		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(ucBuilder.path("/add/{id}").buildAndExpand(invoice.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getInvoice(@PathVariable("id") int id) {
		logger.info("Fetching Appointment with id {}", id);
		Invoice invoice = invoiceRepository.findOne(id);
		if (invoice == null) {
			logger.error("User Type with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Appointment with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAppointment(@PathVariable("id") int id, @RequestBody Invoice invoice1) {
		logger.info("Updating User Type with id {}", id);

		Invoice invoice = invoiceRepository.findOne(id);

		if (invoice1 == null) {
			logger.error("Unable to update. Appointment with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Appointment with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		invoice.setTotalBill(invoice1.getTotalBill());

		invoiceRepository.save(invoice);
		return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteInvoice(@PathVariable("id") int id) {
		logger.info("Fetching & Deleting User Type with id {}", id);

		Invoice invoice = invoiceRepository.findOne(id);
		if (invoice == null) {
			logger.error("Unable to delete. Invoice with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. User Type with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		invoiceRepository.delete(id);
		return new ResponseEntity<Invoice>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Invoice>> listAllInvoices() {
		List<Invoice> invoiceList = (ArrayList<Invoice>) invoiceRepository.findAll();
		if (invoiceList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Invoice>>(invoiceList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
	public ResponseEntity<Invoice> deleteAllInvoices() {
		logger.info("Deleting All Invoices");
		invoiceRepository.deleteAll();
		return new ResponseEntity<Invoice>(HttpStatus.NO_CONTENT);
	}
	
	
}
