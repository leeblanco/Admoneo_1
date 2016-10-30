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
 * This is an entity class for ProjectStatus table on TODO Tests DB
 * 
 * @author Lee
 * 
 * */

@Entity
@Table(name="Todo")
public class Todo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="todoId")
    int todoId;
	
	@Column(name="projectName")
    String projectName;
	
	@Column(name="projectOwner")
    String projectOwner;
	
	@Column(name="statusId")
    int statusId;
	
	@Column(name="reasonId")
    int reasonId;
	
	@Column(name="description")
    String description;
	
	@Column(name="completionDate")
    Date completionDate;
	
	@Column(name="updatedBy")
    String updatedBy;
	
	@Column(name="createdBy")
    String createdBy;
	
	@Column(name="updatedDate")
    Date updatedDate;
	
	@Column(name="createdDate")
    Date createdDate;
    
	public int getTodoId() {
		return todoId;
	}
	
	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getProjectOwner() {
		return projectOwner;
	}
	
	public void setProjectOwner(String projectOwner) {
		this.projectOwner = projectOwner;
	}
	
	public int getStatusId() {
		return statusId;
	}
	
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
	public int getReasonId() {
		return reasonId;
	}
	
	public void setReasonId(int reasonId) {
		this.reasonId = reasonId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getCompletionDate() {
		return completionDate;
	}
	
	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}
	
	public String getUpdatedBy() {
		return updatedBy;
	}
	
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public Date getUpdatedDate() {
		return updatedDate;
	}
	
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
