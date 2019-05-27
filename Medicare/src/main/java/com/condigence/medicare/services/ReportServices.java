package com.condigence.medicare.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condigence.medicare.dto.ReportDTO;
import com.condigence.medicare.model.Appointment;
import com.condigence.medicare.model.Doctor;
import com.condigence.medicare.model.Invoice;
import com.condigence.medicare.model.Patient;
import com.condigence.medicare.model.ServiceType;
import com.condigence.medicare.repository.AppointmentRepository;
import com.condigence.medicare.repository.InvoiceRepository;

@Service
public class ReportServices {

	public static final Logger logger = LoggerFactory.getLogger(ReportServices.class);
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	InvoiceRepository invoiceRepository;
	
	
	public List<ReportDTO> getStats(Timestamp timestamp, Timestamp timestamp1) {

		 List<ReportDTO> listOfReport = new ArrayList<ReportDTO>();
		
		// Timestamp timestamp = new java.sql.Timestamp("2017-07-09 22:33:35.468");
		logger.info(" Search between two date: "+ timestamp +" yesterdayDate : "+timestamp1 );
		
		List<Appointment>  listOfAppointment =   appointmentRepository.findByModifiedDateTimeBetween(timestamp,timestamp1);
		
		
	    Map<String,ReportDTO> abmap   =    getMapOfReport(listOfAppointment);
		
		
	    for (String key : abmap.keySet()) {
	    	logger.info("------------------------------------------------");
	    	logger.info("Iterating or looping map using java5 foreach loop");
	    	logger.info("key: " + key );
	    	logger.info("DoctorCommission of value: " + abmap.get(key).getDoctorCommission());
	    	logger.info("NoOfPatient of value: " + abmap.get(key).getNoOfPatient());
	    	logger.info("NoOfRefDoctor of value: " + abmap.get(key).getNoOfRefDoctor());
	    	logger.info("PerServiceCost of value: " + abmap.get(key).getPerServiceCost());
	    	logger.info("ServiceName of value: " + abmap.get(key).getServiceName());
	    	logger.info("TotalServiceCost of value: " + abmap.get(key).getTotalServiceCost());
	    	logger.info("TotalAmount of value: " + abmap.get(key).getTotalAmount());
	    	
	    	listOfReport.add(abmap.get(key));
	    	}
		return listOfReport;
	}




	private Map<String,ReportDTO> getMapOfReport(List<Appointment> listOfAppointment) {
    Map<String,ReportDTO> abmap = new HashMap<String,ReportDTO>();
		
		int appointmentId = 0;
		
		for(Appointment appointment : listOfAppointment){
			ReportDTO report = new ReportDTO();

			Set<ServiceType> servicetype = appointment.getServices();
			logger.info("   No. of Service... "+servicetype.size());
			
			for (ServiceType service:servicetype){

				if(abmap.containsKey(service.getName())){
				 report.setServiceName(service.getName());
				 report.setPerServiceCost(service.getPrice());
				 report.setNoOfPatient(abmap.get(service.getName()).getNoOfPatient()+1);
				 report.setDoctorCommission(abmap.get(service.getName()).getDoctorCommission() + appointment.getReferredByDoctor().getCommission());
				 report.setTotalServiceCost(abmap.get(service.getName()).getTotalServiceCost() + service.getPrice());
				 report.setNoOfRefDoctor(abmap.get(service.getName()).getNoOfRefDoctor()+1);
				 report.setTotalAmount(report.getTotalServiceCost() - report.getDoctorCommission());
				 abmap.put(service.getName(),report);
				 
				}else{
					report.setServiceName(service.getName());
					report.setTotalServiceCost(service.getPrice());
					report.setPerServiceCost(service.getPrice());
					if(appointmentId != appointment.getId()){
						report.setNoOfPatient(1);
						report.setDoctorCommission(appointment.getReferredByDoctor().getCommission());
						report.setNoOfRefDoctor(1);
						report.setTotalAmount(report.getTotalServiceCost() - report.getDoctorCommission());
					}
					
					abmap.put(service.getName(),report);
					appointmentId = appointment.getId();
				}
			}
		}
		return 	abmap;
	}

}
