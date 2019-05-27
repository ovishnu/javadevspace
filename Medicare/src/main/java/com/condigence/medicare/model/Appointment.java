package com.condigence.medicare.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_id")
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id")
	private Patient patient;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "referred_by_doctor_id")
	private Doctor referredByDoctor;


	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "appointment_services", joinColumns = @JoinColumn(name = "appointment_id"), inverseJoinColumns = @JoinColumn(name = "service_type_id"))
	private Set<ServiceType> services;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "created_by_user_id")
	private User createdByUser;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Column(name = "is_modified")
	private boolean isModified;

	@Column(name = "is_cancled")
	private boolean isCancled;

	@Column(name = "token")
	private int token;

	@Column(name = "date_time")
	private Date dateTime;

	@Column(name = "modified_date_time")
	private Date modifiedDateTime;

	@Column(name = "status")
	private String status;

	@Column(name = "slot")
	private int slot;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean isModified() {
		return isModified;
	}

	public void setModified(boolean isModified) {
		this.isModified = isModified;
	}

	public boolean isCancled() {
		return isCancled;
	}

	public void setCancled(boolean isCancled) {
		this.isCancled = isCancled;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Date getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(Date modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Patient getPatient() {
		return patient;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdByUser == null) ? 0 : createdByUser.hashCode());
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isCancled ? 1231 : 1237);
		result = prime * result + (isDeleted ? 1231 : 1237);
		result = prime * result + (isModified ? 1231 : 1237);
		result = prime * result + ((modifiedDateTime == null) ? 0 : modifiedDateTime.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		result = prime * result + ((referredByDoctor == null) ? 0 : referredByDoctor.hashCode());
		result = prime * result + ((services == null) ? 0 : services.hashCode());
		result = prime * result + slot;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + token;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		if (createdByUser == null) {
			if (other.createdByUser != null)
				return false;
		} else if (!createdByUser.equals(other.createdByUser))
			return false;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isCancled != other.isCancled)
			return false;
		if (isDeleted != other.isDeleted)
			return false;
		if (isModified != other.isModified)
			return false;
		if (modifiedDateTime == null) {
			if (other.modifiedDateTime != null)
				return false;
		} else if (!modifiedDateTime.equals(other.modifiedDateTime))
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		if (referredByDoctor == null) {
			if (other.referredByDoctor != null)
				return false;
		} else if (!referredByDoctor.equals(other.referredByDoctor))
			return false;
		if (services == null) {
			if (other.services != null)
				return false;
		} else if (!services.equals(other.services))
			return false;
		if (slot != other.slot)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (token != other.token)
			return false;
		return true;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getReferredByDoctor() {
		return referredByDoctor;
	}

	public void setReferredByDoctor(Doctor referredByDoctor) {
		this.referredByDoctor = referredByDoctor;
	}

	public User getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
	}

	public int getSlot() {
		return slot;
	}

	public Set<ServiceType> getServices() {
		return services;
	}

	public void setServices(Set<ServiceType> services) {
		this.services = services;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

}