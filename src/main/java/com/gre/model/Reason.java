package com.gre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import java.util.Date;

/**
 * 
 * This is an entity class for ProjectReason table on TODO Tests DB
 * 
 * @author Lee
 * */

@Entity
@Table(name="ProjectReason")
public class Reason {

    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="reasonId")
    int reasonId;
    
    @Column(name="reason")
    String reason;
    
    @Column(name="isActive")
    boolean isActive;
    
    @Column(name="createdBy")
    String createdBy;
    
    @Column(name="updatedBy")
    String updatedBy;
    
    @Column(name="createdDate")
    Date createdDate;
    
    @Column(name="updatedDate")
    Date updatedDate;
    
    
	public int getReasonId() {
		return reasonId;
	}
	public void setReasonId(int reasonId) {
		this.reasonId = reasonId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public boolean getIsActiveValue() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
    
    
}
