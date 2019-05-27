package com.condigence.medicare.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invoice")
public class Invoice implements Serializable{

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "invoice_id")
   private Integer	id;
	
   @Column(name = "appointment_id")
   private Integer appointmentId;
   
   @Column(name = "total_invoice")
   private Integer totalBill;
   
   @Column(name = "created_date_time")
   private Date createdDateTime;
   
   @Column(name = "modified_date_time")
   private Date modifiedDateTime;
   
   @Column(name = "created_by_user_id")
   private Integer createdByUserId;
   
   @Column(name = "status")
   private String status;

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public Integer getAppointmentId() {
	return appointmentId;
}

public void setAppointmentId(Integer appointmentId) {
	this.appointmentId = appointmentId;
}

public Integer getTotalBill() {
	return totalBill;
}

public void setTotalBill(Integer totalBill) {
	this.totalBill = totalBill;
}



public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public Date getCreatedDateTime() {
	return createdDateTime;
}

public void setCreatedDateTime(Date createdDateTime) {
	this.createdDateTime = createdDateTime;
}

public Date getModifiedDateTime() {
	return modifiedDateTime;
}

public void setModifiedDateTime(Date modifiedDateTime) {
	this.modifiedDateTime = modifiedDateTime;
}

public Integer getCreatedByUserId() {
	return createdByUserId;
}

public void setCreatedByUserId(Integer createdByUserId) {
	this.createdByUserId = createdByUserId;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((appointmentId == null) ? 0 : appointmentId.hashCode());
	result = prime * result + ((createdByUserId == null) ? 0 : createdByUserId.hashCode());
	result = prime * result + ((createdDateTime == null) ? 0 : createdDateTime.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((modifiedDateTime == null) ? 0 : modifiedDateTime.hashCode());
	result = prime * result + ((status == null) ? 0 : status.hashCode());
	result = prime * result + ((totalBill == null) ? 0 : totalBill.hashCode());
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
	Invoice other = (Invoice) obj;
	if (appointmentId == null) {
		if (other.appointmentId != null)
			return false;
	} else if (!appointmentId.equals(other.appointmentId))
		return false;
	if (createdByUserId == null) {
		if (other.createdByUserId != null)
			return false;
	} else if (!createdByUserId.equals(other.createdByUserId))
		return false;
	if (createdDateTime == null) {
		if (other.createdDateTime != null)
			return false;
	} else if (!createdDateTime.equals(other.createdDateTime))
		return false;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	if (modifiedDateTime == null) {
		if (other.modifiedDateTime != null)
			return false;
	} else if (!modifiedDateTime.equals(other.modifiedDateTime))
		return false;
	if (status == null) {
		if (other.status != null)
			return false;
	} else if (!status.equals(other.status))
		return false;
	if (totalBill == null) {
		if (other.totalBill != null)
			return false;
	} else if (!totalBill.equals(other.totalBill))
		return false;
	return true;
}

}
