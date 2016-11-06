package com.gre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Priority")
public class Priority {
   
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="priorityId")
   int priorityId;
   
   @Column(name="priorityName")
   String priorityName;
   
   public int getPriorityId() {
   
      return priorityId;
   }
   
   public void setPriorityId(int priorityId) {
   
      this.priorityId = priorityId;
   }
   
   public String getPriorityName() {
   
      return priorityName;
   }
   
   public void setPriorityName(String priorityName) {
   
      this.priorityName = priorityName;
   }
   
   

}
