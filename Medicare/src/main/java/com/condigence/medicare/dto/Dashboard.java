 package com.condigence.medicare.dto;

public class Dashboard {

	private String totalAppointments;
	private String cancelAppointments;
	private String totalUsers;
	private String totalDoctors;
	private String totalServices;
	private String totalPatients;

	public String getTotalPatients() {
		return totalPatients;
	}

	public void setTotalPatients(String totalPatients) {
		this.totalPatients = totalPatients;
	}

	public String getTotalAppointments() {
		return totalAppointments;
	}

	public void setTotalAppointments(String totalAppointments) {
		this.totalAppointments = totalAppointments;
	}

	public String getCancelAppointments() {
		return cancelAppointments;
	}

	public void setCancelAppointments(String cancelAppointments) {
		this.cancelAppointments = cancelAppointments;
	}

	public String getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(String totalUsers) {
		this.totalUsers = totalUsers;
	}

	public String getTotalDoctors() {
		return totalDoctors;
	}

	public void setTotalDoctors(String totalDoctors) {
		this.totalDoctors = totalDoctors;
	}

	public String getTotalServices() {
		return totalServices;
	}

	public void setTotalServices(String totalServices) {
		this.totalServices = totalServices;
	}
}
