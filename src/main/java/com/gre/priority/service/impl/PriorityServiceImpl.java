package com.gre.priority.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import com.gre.model.Priority;
import com.gre.priority.dao.PriorityDao;
import com.gre.priority.dao.impl.PriorityDaoImpl;
import com.gre.priority.service.PriorityService;

public class PriorityServiceImpl implements PriorityService{

   private static final Logger logger = Logger.getLogger(PriorityServiceImpl.class);
   
   private PriorityDao dao = new PriorityDaoImpl();
   
   @Override
   public List<Priority> retrievePriorities() {

      logger.info("Retrieving list of Priorities ");
      
      List<Priority> listOfPriorities = new ArrayList<Priority>();
      
      listOfPriorities.addAll(dao.retrievePriorities());
      
      for (Priority entry: listOfPriorities){
         
         logger.info("Priority ID: " + entry.getPriorityId());
         logger.info("Priority Name : "+ entry.getPriorityName());
         
      }
      return listOfPriorities;
   }

}
