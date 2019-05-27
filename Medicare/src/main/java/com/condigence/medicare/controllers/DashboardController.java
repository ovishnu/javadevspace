package com.condigence.medicare.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.condigence.medicare.dto.Dashboard;
import com.condigence.medicare.services.StatService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	public static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	StatService statService;

	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	public ResponseEntity<Dashboard> listAllAppointments() {
		Dashboard dashboard = new Dashboard();
		dashboard = statService.getStats();
		return new ResponseEntity<Dashboard>(dashboard, HttpStatus.OK);
	}

}
