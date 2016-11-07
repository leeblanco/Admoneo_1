package com.gre.status.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gre.dao.util.HibernateSession;
import com.gre.model.Status;
import com.gre.status.dao.StatusDao;

public class StatusDaoImpl extends HibernateSession implements StatusDao {
    
	final static Logger logger = Logger.getLogger(StatusDaoImpl.class);
	
    /**
	 * Adds new status values in database taken from the 
	 * input parameter. Returns true if the insert is successful
	 * and false if the insert fails or status object is null.
	 * 
	 * @author Lee
	 * @param status status
	 * @return boolean
	 * */
	@Override
    public boolean add(Status status){
        
        boolean update = false;
        
        if (status != null){
        
            logger.info("Insert New Value in Status Table ");
            Session session = getSession();
        
            try{
		
    		    Transaction trans = session.getTransaction();
    	        trans.begin();
    	        
    	        //Set newStatus object with the new input parameters from status input param
    	        logger.info("Insert Status Object with new values ");
    	        Status newStatus = new Status();
    	        newStatus.setStatus(status.getStatus());
    	        newStatus.setIsActive(status.getIsActive());
    	        newStatus.setCreatedBy(status.getCreatedBy());
    	        newStatus.setUpdatedBy(status.getUpdatedBy());
    	        newStatus.setCreatedDate(status.getCreatedDate());
    	        newStatus.setUpdatedDate(status.getUpdatedDate());
    	        
    	        logger.info("Insert status data into Database");
    	        session.save(newStatus);
    	        trans.commit();
	            
	            //set update to true successfully committed transaction
	            update = true;
		
		    }catch(Exception hEx){
		
		        logger.error("Error encountered in inserting Status Object");
		        //Status Update failed
		        update = false;
		        
		        if (session.getTransaction().isActive()){
		    	    session.getTransaction().rollback();
		    	    logger.info("Transaction rollback, status object is not inserted");
                }
		    
		        logger.error(hEx);
            }        
        }else{
        	
            logger.info("status object is null : "+ status);
            update = false;
        }
        return update;
	}

    /**
	 * Updates new status fields taken from the 
	 * input parameter. Returns true if the update is successful
	 * and false if the update fails or status object is null.
	 * 
	 * @author Lee
	 * @param status status
	 * @return boolean
	 * */
	@Override
    public boolean update(Status status){
        
        boolean update = false;
        
        if (status != null){
        
            logger.info("Update status Table ");
            Session session = getSession();
        
            try{
		
    		    Transaction trans = session.getTransaction();
    	        trans.begin();
    	        
    	        //Set newstatus object with the new input parameters from status input
    	        logger.info("Update Status Object with new values ");
    	        Status newStatus = new Status();
    	        newStatus.setStatus(status.getStatus());
    	        newStatus.setIsActive(status.getIsActive());
    	        newStatus.setCreatedBy(status.getCreatedBy());
    	        newStatus.setUpdatedBy(status.getUpdatedBy());
    	        newStatus.setCreatedDate(status.getCreatedDate());
    	        newStatus.setUpdatedDate(status.getUpdatedDate());
    	        
    	        logger.info("Updating status ");
    	        session.update(newStatus);
    	        trans.commit();
	            
	            //set update to true successfully committed transaction
	            update = true;
		
		    }catch(Exception hEx){
		
		        logger.error("Error encountered in updating status Object");
		        //Status Update failed
		        update = false;
		        
		        if (session.getTransaction().isActive()){
		    	    session.getTransaction().rollback();
		    	    logger.info("Transaction rollback, status object is not updated");
                }
		    
		        logger.error(hEx);
            }        
        }else{
        	
            logger.info("status object is null : "+ status);
            update = false;
        }
        return update;
    }	
	
	/**
	 * This method will retrieve the status values to be displayed in
	 * dropdown.
	 * 
	 * @author Lee
	 * @return List<String>
	 * */
    public List<String> retrieveStatusValues() {
    
        List<String> listOfStatusNames = new ArrayList<String>();
        Session session = getSession();
    
        String sql = "select status from status";
        logger.info("Query used to retrieve status values" + sql);
        
        SQLQuery query = session.createSQLQuery(sql);
        logger.info("Query Executed");
        
        List<Object[]> status = query.list();
        
        //Loop over contents 
        for (Object [] entry: status){
            for(Object entryStatus: entry){
            
            	logger.info("List of statuss: "+ entryStatus.toString());
            	listOfStatusNames.add(entryStatus.toString());
            }	
        }
        
        return listOfStatusNames;
    }
}
