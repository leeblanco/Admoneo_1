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
 * This is an entity class for Users table on TODO Tests DB
 * 
 * @author Lee
 */

@Entity
@Table(name = "Users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "userId")
   int userId;

   @Column(name = "firstname")
   String firstname;

   @Column(name = "lastname")
   String lastname;

   @Column(name = "email")
   String email;

   @Column(name = "token")
   String token;

   @Column(name = "createdDate")
   Date createdDate;

   @Column(name = "updatedDate")
   Date updatedDate;

   public int getUserId() {

      return userId;
   }

   public void setUserId(int userId) {

      this.userId = userId;
   }

   public String getFirstname() {

      return firstname;
   }

   public void setFirstname(String firstname) {

      this.firstname = firstname;
   }

   public String getLastname() {

      return lastname;
   }

   public void setLastname(String lastname) {

      this.lastname = lastname;
   }

   public String getEmail() {

      return email;
   }

   public void setEmail(String email) {

      this.email = email;
   }

   public String getToken() {

      return token;
   }

   public void setToken(String token) {

      this.token = token;
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
