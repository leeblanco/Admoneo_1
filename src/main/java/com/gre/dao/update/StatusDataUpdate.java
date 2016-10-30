package com.gre.dao.update;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

import com.gre.dao.util.HibernateSession;
import com.gre.model.Status;

public class StatusDataUpdate extends HibernateSession implements DataUpdate<Status>  {

    final static Logger logger = Logger.getLogger(StatusDataUpdate.class);
    
	/**
	 * Update the Status entity with the new values
	 * received in the input parameter. Returns true 
	 * if the update is successful and false if the fails 
	 * or status object is null.
	 * 
	 * @author Lee
	 * @param Status status
	 * @return boolean
	 * */
	@Override
    public boolean update(Status status){
        
        boolean update = false;
        
        if (status != null){
        
            logger.info("Update Status Table ");
            Session session = getSession();
        
            try{
		
		        Transaction trans = session.getTransaction();
	            trans.begin();
	        
	            //Set newStatus object with the new input parameters from status input
	            logger.info("Update Status Object with new values ");
	            Status newStatus = new Status();
	            newStatus.setStatus(status.getStatus());
	            newStatus.setActive(status.getIsActiveValue());
	            newStatus.setCreatedBy(status.getCreatedBy());
	            newStatus.setUpdatedBy(status.getUpdatedBy());
	            newStatus.setCreatedDate(status.getCreatedDate());
	            newStatus.setUpdatedDate(status.getUpdatedDate());
	        
	            logger.info("Updating Status ");
	            session.save(status);
	            trans.commit();
	            
	            //set update to true successfully committed transaction
	            update = true;
		
		    }catch(Exception hEx){
		
		        logger.error("Error encountered in updating Status Object");
		        //Status Update failed
		        update = false;
		        
		        if (session.getTransaction().isActive()){
		    	    session.getTransaction().rollback();
		    	    logger.info("Transaction rollback, Status object is not updated");
                }
		    
		        logger.error(hEx);
            }        
        }else{
        	
        	logger.info("Status object is null : "+ status);
        	update = false;
        }
        return update;
	}
}
