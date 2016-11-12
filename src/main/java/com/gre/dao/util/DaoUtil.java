package com.gre.dao.util;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gre.model.Todo;

public class DaoUtil {

   private DateUtility dateUtil = new DateUtility();
   
   /**
    * Populates Todo object used in DAO classes for Todo, User, Reason and
    * Status object
    * 
    * @author Lee
    * @return List<Todo>
    * */
   public List<Todo> populateTodoList(List<Object> retrievedTodo) {

      Iterator iterHibObjList = retrievedTodo.iterator();
      List<Todo> todoList = new ArrayList<Todo>();
      
      while (iterHibObjList.hasNext()) {
         
         Object[] obj = (Object[]) iterHibObjList.next();
         
         Todo todo = new Todo();
         
         todo.setProjectName(String.valueOf(obj[0]));
         todo.setProjectOwner(String.valueOf(obj[1]));
         todo.setStatusId(Integer.valueOf(String.valueOf(obj[2])));
         todo.setReasonId(Integer.valueOf(String.valueOf(obj[3])));
         todo.setDescription(String.valueOf(obj[4]));
         todo.setPriority(String.valueOf(obj[5]));
         
         Date completionDate = Date.valueOf(dateUtil.formatStringToLocalDate(String.valueOf(obj[6])));
         Date createdDate = Date.valueOf(dateUtil.formatStringToLocalDate(String.valueOf(obj[7])));
         Date updatedDate = Date.valueOf(dateUtil.formatStringToLocalDate(String.valueOf(obj[8])));
         
         todo.setCompletionDate(completionDate);
         todo.setCreatedDate( createdDate );
         todo.setUpdatedDate( updatedDate );
         todo.setCreatedBy(String.valueOf(obj[9]));
         todo.setUpdatedBy(String.valueOf(obj[10]));
         
         todoList.add(todo);
      }
      
      return todoList;
   }

}
