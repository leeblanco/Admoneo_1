package com.gre.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="todoid")
    int todoId;
	
	@Column(name="projectname")
    String projectName;
	
	@Column(name="projectowner")
    String projectOwner;
	
	@Column(name="statusId")
    int statusId;
	
	@Column(name="reasonId")
    int reasonId;
	
	@Column(name="description")
    String description;
	
	@Column(name="priority")
	String priority;
	
	@Column(name="completiondate")
    Date completionDate;
	
	@Column(name="updatedby")
    String updatedBy;
	
	@Column(name="createdby")
    String createdBy;
	
	@Column(name="updateddate")
    Date updatedDate;
	
	@Column(name="createddate")
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
	
	public void setPriority(String priority){
	   this.priority=priority;
	}
	
	public String getPriority(){
	   return priority;
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
