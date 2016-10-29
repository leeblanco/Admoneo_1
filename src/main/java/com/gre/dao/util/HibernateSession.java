package com.gre.dao.util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public enum HibernateSession {

    INSTANCE;
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    private static Session session;
	
    private synchronized SessionFactory initSessionFactory(){
    
    	if ( sessionFactory == null){
            Configuration configuration = new Configuration();
            configuration.configure();
            
            serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()).buildServiceRegistry();
            
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        
        return sessionFactory;
    }
    
    public Session getSession(){
        
        if (session == null){	
        	session = initSessionFactory().openSession();        	
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
