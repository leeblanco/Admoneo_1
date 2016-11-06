package com.gre.dao;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.junit.Test;

import com.gre.model.Priority;
import com.gre.priority.service.PriorityService;
import com.gre.priority.service.impl.PriorityServiceImpl;

public class TestPriorityServices {

   private final static Logger logger = Logger.getLogger(TestPriorityServices.class);
   
   PriorityService dao = new PriorityServiceImpl();
   
   @Test
   public void testRetrievePriorities() {

      logger.info("Retrieving list of Priorities ");
      
      List<Priority> listOfPriorities = new ArrayList<Priority>();
      
      listOfPriorities.addAll(dao.retrievePriorities());
      
      for (Priority entry: listOfPriorities){
         
         logger.info("Priority ID: " + entry.getPriorityId());
         logger.info("Priority Name : "+ entry.getPriorityName());
         
      }
      
   }
}
