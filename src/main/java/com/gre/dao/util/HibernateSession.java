package com.gre.dao.util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
<<<<<<< HEAD
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public enum HibernateSession {

    INSTANCE;
=======
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * This class is used to initialize Hibernate SessionFactory and Session classes
 * for object relational mapping with Postgresql DB. 
 * The class observes the singleton pattern which only initializes one SessionFactory
 * throughout the duration of the application. 
 * 
 * @author Lee
 * */
public class HibernateSession {

//    INSTANCE;
>>>>>>> f7af7277285c5a439ea1bb656a7bfeee4fec74ef
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    private static Session session;
	
    private synchronized SessionFactory initSessionFactory(){
    
    	if ( sessionFactory == null){
            Configuration configuration = new Configuration();
            configuration.configure();
<<<<<<< HEAD
            
            serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()).buildServiceRegistry();
            
=======

            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();

>>>>>>> f7af7277285c5a439ea1bb656a7bfeee4fec74ef
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        
        return sessionFactory;
    }
    
<<<<<<< HEAD
    public Session getSession(){
        
        if (session == null){	
        	session = initSessionFactory().openSession();        	
=======
    /**
     * Retrieves the current hibernate session 
     * 
     * @author Lee
     * */
    public Session getSession(){
        
        if (session == null){	
            session = initSessionFactory().openSession();        	
>>>>>>> f7af7277285c5a439ea1bb656a7bfeee4fec74ef
        }
    
        return session;
    }
    
    /**
     * This method will close the current Hibernate session
     * 
     * @author Lee
     * */
    public void shutdown(){
    	initSessionFactory().close();
    }
}
