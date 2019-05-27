package com.condigence.medicare.dto;

public class ReportDTO {

	private String serviceName;
	
	private double perServiceCost;
	
	private int noOfPatient;
	
	private double totalServiceCost;
	
	private int noOfRefDoctor;
	
	private long doctorCommission;
	
	private double totalAmount;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public double getPerServiceCost() {
		return perServiceCost;
	}

	public void setPerServiceCost(double perServiceCost) {
		this.perServiceCost = perServiceCost;
	}

	public int getNoOfPatient() {
		return noOfPatient;
	}

	public void setNoOfPatient(int noOfPatient) {
		this.noOfPatient = noOfPatient;
	}

	public double getTotalServiceCost() {
		return totalServiceCost;
	}

	public void setTotalServiceCost(double totalServiceCost) {
		this.totalServiceCost = totalServiceCost;
	}

	public int getNoOfRefDoctor() {
		return noOfRefDoctor;
	}

	public void setNoOfRefDoctor(int noOfRefDoctor) {
		this.noOfRefDoctor = noOfRefDoctor;
	}

	public long getDoctorCommission() {
		return doctorCommission;
	}

	public void setDoctorCommission(long doctorCommission) {
		this.doctorCommission = doctorCommission;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	
	
	
	
	
}
