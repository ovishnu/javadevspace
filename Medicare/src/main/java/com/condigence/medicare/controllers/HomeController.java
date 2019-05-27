package com.condigence.medicare.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*",  maxAge = 3600)
public class HomeController {

	@RequestMapping("/chart")
	public String chart() {
		return "chart";
	}

	@RequestMapping("/dashboard")
	public String dashboard() {
		return "dashboard";
	}

	@RequestMapping("/user")
	public String user() {
		return "user";
	}

	@RequestMapping("/appointment")
	public String appointment() {
		return "appointment";
	}

	@RequestMapping("/doctor")
	public String doctor() {
		return "doctor";
	}

	@RequestMapping("/patient")
	public String patient() {
		return "patient";
	}

	@RequestMapping("/user-type")
	public String userType() {
		return "user-type";
	}

	@RequestMapping("/service-type")
	public ModelAndView serviceType() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("service");
		return mv;
	}

	@RequestMapping("/commission")
	public String commission() {
		return "commission";
	}

	@RequestMapping("/billing")
	public String billing() {
		return "billing";
	}

	@RequestMapping("/report")
	public String report() {
		return "report";
	}

	@RequestMapping("/print")
	public String print() {
		return "print";
	}

}
