package com.gre.reason.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

import com.gre.dao.util.HibernateSession;
import com.gre.model.Reason;
import com.gre.reason.dao.ReasonDao;

public class ReasonDaoImpl extends HibernateSession implements ReasonDao{

    final static Logger logger = Logger.getLogger(ReasonDaoImpl.class);
	
    /**
	 * Adds new Reason values in database taken from the 
	 * input parameter. Returns true if the insert is successful
	 * and false if the insert fails or reason object is null.
	 * 
	 * @author Lee
	 * @param Reason reason
	 * @return boolean
	 * */
	@Override
    public boolean add(Reason reason){
        
        boolean update = false;
        
        if (reason != null){
        
            logger.info("Insert New Value in Reason Table ");
            Session session = getSession();
        
            try{
		
    		    Transaction trans = session.getTransaction();
    	        trans.begin();
    	        
    	        //Set newReason object with the new input parameters from reason input
    	        logger.info("Insert Status Object with new values ");
    	        Reason newReason = new Reason();
    	        newReason.setReason(reason.getReason());
    	        newReason.setIsActive(reason.getIsActive());
    	        newReason.setCreatedBy(reason.getCreatedBy());
    	        newReason.setUpdatedBy(reason.getUpdatedBy());
    	        newReason.setCreatedDate(reason.getCreatedDate());
    	        newReason.setUpdatedDate(reason.getUpdatedDate());
    	        
    	        logger.info("Inserting Reason data into Database");
    	        session.save(newReason);
    	        trans.commit();
	            
	            //set update to true successfully committed transaction
	            update = true;
		
		    }catch(Exception hEx){
		
		        logger.error("Error encountered in inserting Reason Object");
		        //Status Update failed
		        update = false;
		        
		        if (session.getTransaction().isActive()){
		    	    session.getTransaction().rollback();
		    	    logger.info("Transaction rollback, reason object is not inserted");
                }
		    
		        logger.error(hEx);
            }        
        }else{
        	
            logger.info("Reason object is null : "+ reason);
            update = false;
        }
        return update;
	}

    /**
	 * Updates new Reason fields taken from the 
	 * input parameter. Returns true if the update is successful
	 * and false if the update fails or reason object is null.
	 * 
	 * @author Lee
	 * @param Reason reason
	 * @return boolean
	 * */
	@Override
    public boolean update(Reason reason){
        
        boolean update = false;
        
        if (reason != null){
        
            logger.info("Update Reason Table ");
            Session session = getSession();
        
            try{
		
    		    Transaction trans = session.getTransaction();
    	        trans.begin();
    	        
    	        //Set newReason object with the new input parameters from reason input
    	        logger.info("Update Status Object with new values ");
    	        Reason newReason = new Reason();
    	        newReason.setReason(reason.getReason());
    	        newReason.setIsActive(reason.getIsActive());
    	        newReason.setCreatedBy(reason.getCreatedBy());
    	        newReason.setUpdatedBy(reason.getUpdatedBy());
    	        newReason.setCreatedDate(reason.getCreatedDate());
    	        newReason.setUpdatedDate(reason.getUpdatedDate());
    	        
    	        logger.info("Updating Reason ");
    	        session.update(newReason);
    	        trans.commit();
	            
	            //set update to true successfully committed transaction
	            update = true;
		
		    }catch(Exception hEx){
		
		        logger.error("Error encountered in updating Reason Object");
		        //Status Update failed
		        update = false;
		        
		        if (session.getTransaction().isActive()){
		    	    session.getTransaction().rollback();
		    	    logger.info("Transaction rollback, reason object is not updated");
                }
		    
		        logger.error(hEx);
            }        
        }else{
        	
            logger.info("Reason object is null : "+ reason);
            update = false;
        }
        return update;
    }	

	/**
	 * This method will retrieve the reason values to be displayed in
	 * dropdown.
	 * 
	 * @author Lee
	 * @return List<String>
	 * */
    @Override
    public List<String> retrieveReasonValues() {
    
        List<String> listOfReasonNames = new ArrayList<String>();
        Session session = getSession();
    
        String sql = "select reason from reason";
        logger.info("Query used to retrieve reason values" + sql);
        
        SQLQuery query = session.createSQLQuery(sql);
        logger.info("Query Executed");
        
        List<Object[]> reasons = query.list();
        
        //Loop over contents 
        for (Object [] entry: reasons){
            for(Object entryReason: entry){
            
            	logger.info("List of Reasons: "+ entryReason.toString());
            	listOfReasonNames.add(entryReason.toString());
            }	
        }
        
        return listOfReasonNames;
    }
}
