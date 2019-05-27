package com.condigence.medicare.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condigence.medicare.dto.AppointmentDTO;
import com.condigence.medicare.model.Appointment;
import com.condigence.medicare.model.Doctor;
import com.condigence.medicare.model.Patient;
import com.condigence.medicare.model.ServiceType;
import com.condigence.medicare.model.User;
import com.condigence.medicare.repository.AppointmentRepository;
import com.condigence.medicare.repository.PatientRepository;

@Service("appointmentService")
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	AppointmentRepository appointmentRepository;

	@Autowired
	PatientRepository patientRepository;

	@Override
	public List<AppointmentDTO> findAppointments() {
		List<Appointment> appointmentList = (ArrayList<Appointment>) appointmentRepository.findAll();
		List<AppointmentDTO> appointmentDTOs = new ArrayList<>();

		for (Appointment appointment : appointmentList) {
			AppointmentDTO appointmentDTO = new AppointmentDTO();
			appointmentDTO.setAppointmentId(appointment.getId());

			Doctor doctor = new Doctor();
			doctor.setId(appointment.getReferredByDoctor().getId());
			doctor.setName(appointment.getReferredByDoctor().getName());
			appointmentDTO.setReferredByDoctor(doctor);

			Set<ServiceType> set = new HashSet<>();

			for (ServiceType obj : appointment.getServices()) {
				ServiceType serviceType = new ServiceType();
				serviceType.setId(obj.getId());
				serviceType.setName(obj.getName());
				set.add(serviceType);
			}

			appointmentDTO.setServiceType(set);

			appointmentDTO.setDateTime(appointment.getDateTime());
			appointmentDTO.setSlot(appointment.getSlot());
			appointmentDTO.setStatus(appointment.getStatus());
			appointmentDTO.setToken(appointment.getToken());
			appointmentDTO.setCancled(appointment.isCancled());

			User user = new User();
			user.setId(appointment.getCreatedByUser().getId());
			user.setName(appointment.getCreatedByUser().getName());
			appointmentDTO.setCreatedByUserId(user);
			appointmentDTO.setDeleted(appointment.isDeleted());
			appointmentDTO.setModified(appointment.isModified());
			appointmentDTO.setModifiedDateTime(appointment.getModifiedDateTime());
			Patient patient = new Patient();
			patient.setAddress(appointment.getPatient().getAddress());
			patient.setAge(appointment.getPatient().getAge());
			patient.setContactNo(appointment.getPatient().getContactNo());
			patient.setEmail(appointment.getPatient().getEmail());
			patient.setFirstName(appointment.getPatient().getFirstName());
			patient.setGender(appointment.getPatient().getGender().toString());
			patient.setId(appointment.getPatient().getId());
			patient.setLastName(appointment.getPatient().getLastName());
			appointmentDTO.setPatient(patient);
			appointmentDTOs.add(appointmentDTO);
		}
		return appointmentDTOs;
	}

	@Override
	public void save(AppointmentDTO appointmentDto) {
		Appointment appointment = new Appointment();
		Patient patient = new Patient();
		appointment.setCancled(appointmentDto.isCancled());
		// appointment.setCreatedByUserId(appointmentDto.getCreatedByUserId());
		appointment.setDateTime(appointmentDto.getDateTime());
		appointment.setDeleted(appointmentDto.isDeleted());
		appointment.setModified(appointmentDto.isModified());
		appointment.setModifiedDateTime(appointmentDto.getModifiedDateTime());
		// appointment.setReferredByDoctorId(appointmentDto.getReferredByDoctorId());
		// appointment.setServiceTypeId(appointmentDto.getServiceTypeId());
		appointment.setSlot(appointmentDto.getSlot());
		appointment.setStatus(appointmentDto.getStatus());
		appointment.setToken(appointmentDto.getToken());
		patient.setAddress(appointmentDto.getAddress());
		patient.setAge(appointmentDto.getAge());
		patient.setContactNo(appointmentDto.getContactNo());
		patient.setEmail(appointmentDto.getEmail());
		patient.setFirstName(appointmentDto.getFirstName());
		patient.setGender(appointmentDto.getGender().toString());
		patient.setId(appointmentDto.getAppointmentId());
		patient.setLastName(appointmentDto.getLastName());
		appointment.setPatient(patient);
		appointmentRepository.save(appointment);
	}

	@Override
	public List<AppointmentDTO> findAppointmentsByPatientName(String name) {
		List<Appointment> appointments = appointmentRepository
				.findBypatient(patientRepository.findByFirstName(name).get(0));

		List<AppointmentDTO> appointmentDTOs = new ArrayList<>();
		for (Appointment appointment : appointments) {
			AppointmentDTO appointmentDTO = new AppointmentDTO();
			appointmentDTO.setAppointmentId(appointment.getId());

			Doctor doctor = new Doctor();
			doctor.setId(appointment.getReferredByDoctor().getId());
			doctor.setName(appointment.getReferredByDoctor().getName());
			appointmentDTO.setReferredByDoctor(doctor);

			Set<ServiceType> set = new HashSet<>();

			for (ServiceType obj : appointment.getServices()) {
				ServiceType serviceType = new ServiceType();
				serviceType.setId(obj.getId());
				serviceType.setName(obj.getName());
				set.add(serviceType);
			}

			appointmentDTO.setServiceType(set);

			appointmentDTO.setDateTime(appointment.getDateTime());
			appointmentDTO.setSlot(appointment.getSlot());
			appointmentDTO.setStatus(appointment.getStatus());
			appointmentDTO.setToken(appointment.getToken());
			appointmentDTO.setCancled(appointment.isCancled());

			User user = new User();
			user.setId(appointment.getCreatedByUser().getId());
			user.setName(appointment.getCreatedByUser().getName());
			appointmentDTO.setCreatedByUserId(user);
			appointmentDTO.setDeleted(appointment.isDeleted());
			appointmentDTO.setModified(appointment.isModified());
			appointmentDTO.setModifiedDateTime(appointment.getModifiedDateTime());
			Patient patient = new Patient();
			patient.setAddress(appointment.getPatient().getAddress());
			patient.setAge(appointment.getPatient().getAge());
			patient.setContactNo(appointment.getPatient().getContactNo());
			patient.setEmail(appointment.getPatient().getEmail());
			patient.setFirstName(appointment.getPatient().getFirstName());
			patient.setGender(appointment.getPatient().getGender().toString());
			patient.setId(appointment.getPatient().getId());
			patient.setLastName(appointment.getPatient().getLastName());
			appointmentDTO.setPatient(patient);
			appointmentDTOs.add(appointmentDTO);
		}

		return appointmentDTOs;
	}

	@Override
	public List<AppointmentDTO> findAppointmentsByPatientContact(String contact) {
		List<Appointment> appointments = appointmentRepository
				.findBypatient(patientRepository.findByContactNo(Long.parseLong(contact)).get(0));

		List<AppointmentDTO> appointmentDTOs = new ArrayList<>();
		for (Appointment appointment : appointments) {
			AppointmentDTO appointmentDTO = new AppointmentDTO();
			appointmentDTO.setAppointmentId(appointment.getId());

			Doctor doctor = new Doctor();
			doctor.setId(appointment.getReferredByDoctor().getId());
			doctor.setName(appointment.getReferredByDoctor().getName());
			appointmentDTO.setReferredByDoctor(doctor);

			Set<ServiceType> set = new HashSet<>();

			for (ServiceType obj : appointment.getServices()) {
				ServiceType serviceType = new ServiceType();
				serviceType.setId(obj.getId());
				serviceType.setName(obj.getName());
				set.add(serviceType);
			}

			appointmentDTO.setServiceType(set);

			appointmentDTO.setDateTime(appointment.getDateTime());
			appointmentDTO.setSlot(appointment.getSlot());
			appointmentDTO.setStatus(appointment.getStatus());
			appointmentDTO.setToken(appointment.getToken());
			appointmentDTO.setCancled(appointment.isCancled());

			User user = new User();
			user.setId(appointment.getCreatedByUser().getId());
			user.setName(appointment.getCreatedByUser().getName());
			appointmentDTO.setCreatedByUserId(user);
			appointmentDTO.setDeleted(appointment.isDeleted());
			appointmentDTO.setModified(appointment.isModified());
			appointmentDTO.setModifiedDateTime(appointment.getModifiedDateTime());
			Patient patient = new Patient();
			patient.setAddress(appointment.getPatient().getAddress());
			patient.setAge(appointment.getPatient().getAge());
			patient.setContactNo(appointment.getPatient().getContactNo());
			patient.setEmail(appointment.getPatient().getEmail());
			patient.setFirstName(appointment.getPatient().getFirstName());
			patient.setGender(appointment.getPatient().getGender().toString());
			patient.setId(appointment.getPatient().getId());
			patient.setLastName(appointment.getPatient().getLastName());
			appointmentDTO.setPatient(patient);
			appointmentDTOs.add(appointmentDTO);
		}

		return appointmentDTOs;
	}

}
