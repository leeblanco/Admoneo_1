package com.gre.priority.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

import com.gre.dao.util.HibernateSession;
import com.gre.model.Priority;
import com.gre.priority.dao.PriorityDao;

public class PriorityDaoImpl extends HibernateSession implements PriorityDao {

   private static final Logger logger = Logger.getLogger(PriorityDaoImpl.class);
   
   /**
    * This method will retrieve all priority records in table
    * 
    * @author Lee
    * @return listOfPriorities returns all priorities retrieved from Priority table
    */
   @Override
   public List<Priority> retrievePriorities() {

      List<Priority> listOfPriorities = new ArrayList<Priority>();

      Session session = getSession();
      try {

         Transaction tx = session.getTransaction();
         tx.begin();

         listOfPriorities = session.createQuery("from Priority").list();

         tx.commit();

      } catch (HibernateException hEx) {

         logger.error("Error encountered in retrieving user data " + hEx);

      } finally {

         session.close();
      }

      return listOfPriorities;
   }
}
