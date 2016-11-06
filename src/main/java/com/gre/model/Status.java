package com.gre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import java.util.Date;

/**
 * This is an entity class for ProjectStatus table on TODO Tests DB
 * 
 * @author Lee
 * 
 */

@Entity
@Table(name = "ProjectStatus")
public class Status {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "statusId")
   int statusId;

   @Column(name = "status")
   String status;

   @Column(name = "isActive")
   boolean isActive;

   @Column(name = "updatedBy")
   String updatedBy;

   @Column(name = "createdBy")
   String createdBy;

   @Column(name = "updatedDate")
   Date updatedDate;

   @Column(name = "createdDate")
   Date createdDate;

   public int getStatusId() {

      return statusId;
   }

   public void setStatusId(int statusId) {

      this.statusId = statusId;
   }

   public String getStatus() {

      return status;
   }

   public void setStatus(String status) {

      this.status = status;
   }

   public boolean getIsActiveValue() {

      return isActive;
   }

   public void setActive(boolean isActive) {

      this.isActive = isActive;
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
