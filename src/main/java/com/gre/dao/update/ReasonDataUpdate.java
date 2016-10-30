package com.gre.dao.update;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

import com.gre.dao.util.HibernateSession;
import com.gre.model.Reason;
import com.gre.model.Status;

public class ReasonDataUpdate extends HibernateSession implements DataUpdate<Reason>{

    final static Logger logger = Logger.getLogger(ReasonDataUpdate.class);
	
    /**
	 * Update the Reason entity with the new values
	 * received in the input parameter. Returns true 
	 * if the update is successful and false if the fails 
	 * or reason object is null.
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
    	        newReason.setActive(reason.getIsActiveValue());
    	        newReason.setCreatedBy(reason.getCreatedBy());
    	        newReason.setUpdatedBy(reason.getUpdatedBy());
    	        newReason.setCreatedDate(reason.getCreatedDate());
    	        newReason.setUpdatedDate(reason.getUpdatedDate());
    	        
    	        logger.info("Updating Reason ");
    	        session.save(newReason);
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
}
