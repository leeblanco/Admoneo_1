package com.gre.dao.util;

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
         configuration.configure();

         serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

         sessionFactory = configuration.buildSessionFactory(serviceRegistry);
      }

      return sessionFactory;
   }

   /**
    * Use getCurrentSession and let Hibernate manage the opening and closing of session
    * 
    * @author Lee
    * @return Hibernate session
    * */
   public Session getSession() {

         session = initSessionFactory().getCurrentSession();

      return session;
   }

   public void shutdown() {

      initSessionFactory().close();
   }
}
