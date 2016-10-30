package com.gre.dao.update;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

import com.gre.dao.util.HibernateSession;
import com.gre.model.User;

public class UserDataUpdate extends HibernateSession implements DataUpdate<User> {

    final static Logger logger = Logger.getLogger(UserDataUpdate.class);

    /**
	 * Update the User entity with the new values
	 * received via input parameter. Returns true 
	 * if the update is successful and false if the fails 
	 * or User object is null.
	 * 
	 * @author Lee
	 * @param Reason reason
	 * @return boolean
	 * */
	@Override
    public boolean update(User user){
        
        boolean update = false;
        
        if (user != null){
        
            logger.info("Update User Table ");
            Session session = getSession();
        
            try{
		
    		    Transaction trans = session.getTransaction();
    	        trans.begin();
    	        
    	        //Set User object with the new input parameters from User input
    	        logger.info("Update User Object with new values ");
    	        
    	        User newUser = new User();
                newUser.setFirstname(user.getFirstname());
                newUser.setLastname(user.getLastname());
                newUser.setEmail(user.getEmail());
    	        newUser.setCreatedDate(user.getCreatedDate());
    	        newUser.setUpdatedDate(user.getUpdatedDate());
    	        
    	        logger.info("Updating User ");
    	        session.save(newUser);
    	        trans.commit();
	            
	            //set update to true successfully committed transaction
	            update = true;
		
		    }catch(Exception hEx){
		
		        logger.error("Error encountered in updating User Object");
		        //Status Update failed
		        update = false;
		        
		        if (session.getTransaction().isActive()){
		    	    session.getTransaction().rollback();
		    	    logger.info("Transaction rollback, User object is not updated");
                }
		    
		        logger.error(hEx);
            }        
        }else{
        	
            logger.info("User object is null : "+ user);
            update = false;
        }
        return update;
	}    		
}
