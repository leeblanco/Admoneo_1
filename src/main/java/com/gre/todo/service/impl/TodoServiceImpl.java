package com.gre.todo.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;

import com.gre.model.Todo;
import com.gre.todo.dao.TodoDao;
import com.gre.todo.dao.impl.TodoDaoImpl;
import com.gre.todo.service.TodoService;

/**
 * This class contains methods to insert update and retrieve tasks from Todo
 * table.
 * 
 * @author Lee
 */
public class TodoServiceImpl implements TodoService {
   
   private static final Logger logger = Logger.getLogger(TodoServiceImpl.class);

   private TodoDao dao = new TodoDaoImpl();

   /**
    * Task is inserted into Todo Table using database call TodoDao
    * This method will return true if the object was added successfully
    * otherwise it will return false.
    * 
    * @author Lee
    * @param todo   object to be inserted on Todo table
    * @return txStatus   returns true or false
    * */
   @Override
   public boolean addTodo(Todo todo) {

      boolean txStatus = false;
      Todo taskToSave = new Todo();

      taskToSave.setProjectName(todo.getProjectName());
      taskToSave.setProjectOwner(todo.getProjectOwner());
      taskToSave.setReasonId(todo.getReasonId());
      taskToSave.setStatusId(todo.getStatusId());
      taskToSave.setPriority(todo.getPriority());
      taskToSave.setCompletionDate(setCompletionDate(todo.getPriority()));
      taskToSave.setCreatedDate(new Date());
      taskToSave.setUpdatedDate(new Date());
      
      txStatus = dao.addTodo(taskToSave);

      return txStatus;
   }

   /**
    * Todo table is updated using database call TodoDao interface.
    * This method will return true if the object was added successfully
    * otherwise it will return false.
    * 
    * @author Lee
    * @param todo   object to be inserted on Todo table
    * @return txStatus   returns true or false
    * */
   @Override
   public boolean updateTodo(Todo todo) {

      boolean txStatus = false;
      Todo taskToSave = new Todo();

      taskToSave.setProjectName(todo.getProjectName());
      taskToSave.setProjectOwner(todo.getProjectOwner());
      taskToSave.setReasonId(todo.getReasonId());
      taskToSave.setStatusId(todo.getStatusId());
      taskToSave.setPriority(todo.getPriority());
      taskToSave.setCompletionDate(setCompletionDate(todo.getPriority()));
      taskToSave.setCreatedDate(new Date());
      taskToSave.setUpdatedDate(new Date());
      
      txStatus = dao.updateTodo(taskToSave);

      return txStatus;
      
   }

   @Override
   public List<Todo> retrieveTodo() {

      TodoDao dao = new TodoDaoImpl();
      
      List<Todo> listOfTodo = new ArrayList<Todo>();
      
      logger.info("Retrieving all tasks in todo table");
      
      listOfTodo.addAll(dao.retrieveTodo());
      
      logger.info("Total number of tasks: "+ listOfTodo.size());
      
      return listOfTodo;
   }

   @Override
   public List<Todo> searchTodoByProjectName(String projectName) {

      TodoDao dao = new TodoDaoImpl();
      
      List<Todo> listOfTodo = new ArrayList<Todo>();
      
      logger.info("Retrieve task by Project Name " );
      listOfTodo.addAll(dao.searchTodoByProjectName(projectName));
      
      logger.info("Number of tasks by project name "+ listOfTodo.size());
      
      return listOfTodo;
   }

   @Override
   public List<Todo> searchTodoByProjectOwner(String ownerName) {

      TodoDao dao = new TodoDaoImpl();
      
      List<Todo> listOfTodo = new ArrayList<Todo>();
      
      logger.info("Retrieve task by Project Owner " );
      listOfTodo.addAll(dao.searchTodoByProjectOwner(ownerName));
      
      logger.info("Number of tasks by project owner "+ listOfTodo.size());
      
      return listOfTodo;
   }

   @Override
   public List<Todo> searchTodoByDate(Date fromDate, Date toDate) {

      // TODO Auto-generated method stub
      return null;
   }

   /**
    * Sets the completion date according to the priority entered
    * High is equal to 1 day, Medium is equal to 2 days, Low is 
    * equal to 3 days and all else its 5 days. 
    * 
    * @author Lee
    * */
   public Date setCompletionDate(String priority) {
      
      logger.info("Set Completion Date ");
      int noOfDays = 0;
      
      Calendar completionDate = Calendar.getInstance();
      completionDate.setTime(new Date());
      
      if ("High".equalsIgnoreCase(priority)) {
         
         noOfDays = 1;
         
      }else if ("Medium".equalsIgnoreCase(priority)) {
         
         noOfDays = 2;
         
      }else if ("Low".equalsIgnoreCase(priority)) {
      
         noOfDays = 3;
      
      }else{
         
         noOfDays = 5;
         
      }
      
      logger.info("Priority : "+ priority+ " Number of Days: "+ noOfDays);
      
      completionDate.add(Calendar.DATE, noOfDays);
      
      logger.info("Completion Date value : " + completionDate.toString());
      
      return completionDate.getTime();
      
   }
}
