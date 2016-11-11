package com.gre.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.junit.Test;

import com.gre.model.Todo;
import com.gre.service.TodoService;
import com.gre.service.impl.TodoServiceImpl;

public class TestTodoServices {

   private static final Logger logger = Logger.getLogger(TestTodoServices.class);

   TestUtil util = new TestUtil();
   
   @Test
   public void TestRetrieveAllTask() {

      TodoService todoMgr = new TodoServiceImpl();

      List<Todo> listOfTodo = new ArrayList<Todo>();

      listOfTodo.addAll(todoMgr.retrieveTodo());

      util.showTodoFields(listOfTodo);

   }

   @Test
   public void TestRetrieveByProjectOwner() {
      
      TodoService todoMgr = new TodoServiceImpl();

      List<Todo> listOfTodo = new ArrayList<Todo>();

      listOfTodo.addAll(todoMgr.searchTodoByProjectOwner("Lee"));

      util.showTodoFields(listOfTodo);
      
   }
   
   @Test
   public void TestRetrievalByProjectName() {
      
      TodoService todoMgr = new TodoServiceImpl();

      List<Todo> listOfTodo = new ArrayList<Todo>();

      listOfTodo.addAll(todoMgr.searchTodoByProjectName("Gandam"));

      util.showTodoFields(listOfTodo);
      
   }
   
   @Test
   public void TestInsertTaskService() {
      
      TodoService todoMgr = new TodoServiceImpl();
      
      Todo task = new Todo();
      
      boolean status = false;
      
      task.setProjectName("Testing");
      task.setProjectOwner(util.randomDataGen());
      task.setReasonId(2);
      task.setStatusId(1);
      task.setPriority("High");
      task.setCompletionDate(new Date());
      task.setCreatedBy("Dimas");
      task.setUpdatedBy(util.randomDataGen());
      task.setCreatedDate(new Date());
      task.setUpdatedDate(new Date());
      
      status = todoMgr.addTodo(task);
      
      logger.info("Status : "+ status);
      
   }
}
