package com.gre.dao;

import java.util.List;
import java.util.Random;

import org.jboss.logging.Logger;

import com.gre.model.Todo;
import com.gre.model.User;

public class TestUtil {

   private static final Logger logger = Logger.getLogger(TestUtil.class);
   
   /**
    * Utility to print all User field values
    * 
    * @author Lee
    * @param user
    *            object
    */
   public void userPrintUtil(User user) {

       logger.info("ID: " + user.getUserId());
       logger.info("FirstName: " + user.getFirstname());
       logger.info("LastName: " + user.getLastname());
       logger.info("Email: " + user.getEmail());
       logger.info("Token: " + user.getToken());
       logger.info("CreatedDate: " + user.getCreatedDate());
       logger.info("CreatedDate: " + user.getUpdatedDate());
       
   }

   /**
    * Utility to print random values used in testing
    * 
    * @author Lee
    * @return randomValue returns a random set of characters
    */
   public String randomDataGen() {

       String characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

       int stringLength = 8;

       StringBuilder randomString = new StringBuilder();

       Random randomize = new Random();

       for (int x = 0; x < stringLength; x++) {
           randomString.append(characterSet.charAt(randomize.nextInt(characterSet.length())));
       }

       logger.info("Random String Generated: " + randomString.toString());

       return randomString.toString();
       
   }
   
   /**
    * Utility method for printing Todo fields
    * 
    * @author Lee
    * 
    * */
   public void showTodoFields(List<Todo> listOfTodo) {

      for (Todo entry : listOfTodo) {

         logger.info("Project Owner: " + entry.getProjectOwner());
         logger.info("Project Name : " + entry.getProjectName());
         logger.info("Status : " + entry.getStatusId());
         logger.info("Reason : " + entry.getReasonId());
         logger.info("Completion Date: " + entry.getCompletionDate());
         logger.info("Priority: " + entry.getPriority());
         logger.info("Created Date: " + entry.getCreatedDate());
         logger.info("Updated Date: " + entry.getUpdatedDate());
         logger.info("Updated by: " + entry.getUpdatedBy());
         logger.info("Created by: " + entry.getCreatedBy());

      }
   }
}
