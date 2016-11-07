package com.gre.dao.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * This class is used to initialize Hibernate SessionFactory and Session classes
 * for object relational mapping with Postgresql DB. The class observes the
 * singleton pattern which only initializes one SessionFactory throughout the
 * duration of the application.
 * 
 * @author Lee
 */
public class HibernateSession {

   private static SessionFactory sessionFactory;
   private static ServiceRegistry serviceRegistry;
   private static Session session;

   /**
    * This method will initialize SessionFactory
    * 
    * @author Lee
    * @return SessionFactory
    */
   private synchronized SessionFactory initSessionFactory() {

      if (sessionFactory == null) {
         Configuration configuration = new Configuration();
         // configuration.addResource("com/gre/model/Users.hbm.xml");
         // configuration.addResource("com/gre/model/Priorities.hbm.xml");
         // configuration.addResource("com/gre/model/Todo.hbm.xml");
         // configuration.addResource("com/gre/model/Reason.hbm.xml");
         // configuration.addResource("com/gre/model/Status.hbm.xml");
         configuration.configure();

         serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

         sessionFactory = configuration.buildSessionFactory(serviceRegistry);
      }

      return sessionFactory;
   }
   
 /*  static {

      try {
         
         Configuration configuration = new Configuration();
         configuration.configure();

         serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

         sessionFactory = configuration.buildSessionFactory(serviceRegistry);
      
      }catch(Throwable ex){
         
         throw new ExceptionInInitializerError(ex);
         
      }
   }*/

   /**
    * Retrieves the current hibernate session
    * 
    * @author Lee
    */
   
   public Session getSession() {

      if (session == null) {

         session = initSessionFactory().openSession();

      } else {

         session = initSessionFactory().getCurrentSession();

      }

      return session;
   } 
   

/*   public static SessionFactory getSession() throws HibernateException {
      return sessionFactory;
   }*/
   
   /**
    * This method will close the current Hibernate session
    * 
    * @author Lee
    */
/*   public void shutdown() {

      initSessionFactory().close();
   }*/
}
