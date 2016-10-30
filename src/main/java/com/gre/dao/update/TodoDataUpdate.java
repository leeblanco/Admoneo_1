package com.gre.dao.update;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

import com.gre.dao.util.HibernateSession;
import com.gre.model.Todo;

public class TodoDataUpdate extends HibernateSession implements DataUpdate<Todo> {

	final static Logger logger = Logger.getLogger(TodoDataUpdate.class);
	
    /**
	 * Update the TODO entity with new values
	 * received from the input parameter. Returns true 
	 * if the update is successful and false if the fails 
	 * or todo object is null.
	 * 
	 * @author Lee
	 * @param Todo todo
	 * @return boolean
	 * */
	@Override
    public boolean update(Todo todo){
        
        boolean update = false;
        
        if (todo != null){
        
            logger.info("Update Reason Table ");
            Session session = getSession();
        
            try{
		
        	    Transaction trans = session.getTransaction();
                trans.begin();
    	        
                //Set newReason object with the new input parameters from reason input
                logger.info("Update Status Object with new values ");
    	        Todo newTodo = new Todo();
    	        newTodo.setProjectName(todo.getProjectName());
    	        newTodo.setProjectOwner(todo.getProjectOwner());
    	        newTodo.setStatusId(todo.getStatusId());
    	        newTodo.setReasonId(todo.getReasonId());
    	        newTodo.setDescription(todo.getDescription());
    	        newTodo.setCompletionDate(todo.getCompletionDate());
    	        newTodo.setCreatedBy(todo.getCreatedBy());
    	        newTodo.setUpdatedBy(todo.getUpdatedBy());
    	        newTodo.setCreatedDate(todo.getCreatedDate());
    	        newTodo.setUpdatedDate(todo.getUpdatedDate());
    	        
    	        logger.info("Updating TODO ");
    	        session.save(newTodo);
    	        trans.commit();
	            
	            //set update to true successfully committed transaction
	            update = true;
		
		    }catch(Exception hEx){
		
		        logger.error("Error encountered in updating TODO Object");
		        //Status Update failed
		        update = false;
		        
		        if (session.getTransaction().isActive()){
		    	    session.getTransaction().rollback();
		    	    logger.info("Transaction rollback, todo object is not updated");
                }
		    
		        logger.error(hEx);
            }        
        }else{
        	
            logger.info("TODO object is null : "+ todo);
            update = false;
        }
        return update;
	}
}
