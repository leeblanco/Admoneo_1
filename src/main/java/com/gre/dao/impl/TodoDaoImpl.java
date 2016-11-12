package com.gre.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;

import com.gre.dao.TodoDao;
import com.gre.dao.util.DaoUtil;
import com.gre.dao.util.HibernateSession;
import com.gre.model.Todo;

public class TodoDaoImpl extends HibernateSession implements TodoDao {

   final static Logger logger = Logger.getLogger(UserDaoImpl.class);

   private DaoUtil daoUtil = new DaoUtil();

   /**
    * Adds new task in Todo table using the input param task. Returns true if
    * the insert is successful and false if the insert fails or status object is
    * null.
    * 
    * @author Lee
    * @param task
    *           updates a tasks using all the information on tasks object
    * @return update returns true if update is successful otherwise false
    */
   public boolean addTodo(Todo task) {

      boolean update = false;

      if (task != null) {

         logger.info("Insert New task in Todo Table ");
         Session session = getSession();

         try {

            Transaction trans = session.beginTransaction();

            // Set newStatus object with the new input parameters from
            // status input param
            logger.info("Insert task Object with new values ");
            Todo newTask = new Todo();

            newTask.setProjectName(task.getProjectName());
            newTask.setProjectOwner(task.getProjectOwner());
            newTask.setDescription(task.getDescription());
            newTask.setReasonId(task.getReasonId());
            newTask.setStatusId(task.getStatusId());
            newTask.setPriority(task.getPriority());
            newTask.setCompletionDate(task.getCompletionDate());
            newTask.setCreatedDate(task.getCreatedDate());
            newTask.setCreatedBy(task.getCreatedBy());
            newTask.setUpdatedDate(task.getUpdatedDate());

            logger.info("Insert newTask object into Database");
            session.save(newTask);
            trans.commit();

            // set update to true successfully committed transaction
            update = true;
            logger.debug("Setting update to " + update);

         } catch (Exception hEx) {

            logger.error("Error encountered in inserting newTask Object");
            // Status Update failed
            update = false;
            logger.debug("Setting update to " + update);

            if (session.getTransaction().isActive()) {
               session.getTransaction().rollback();
               logger.info("Transaction rollback, newTask object is not inserted");
            }

            logger.error(hEx);
         }
      } else {

         logger.info("Task object is null : " + task);
         update = false;
      }
      return update;
   }

   /**
    * Adds new task in Todo table using the input param Returns true if the
    * insert is successful and false if the insert fails or status object is
    * null.
    * 
    * @author Lee
    * @param task
    *           updates a tasks using all the information on tasks object
    * @return update returns true if update is successful otherwise false
    */
   public boolean updateTodo(Todo task) {

      boolean update = false;

      if (task != null) {

         logger.info("Update existing task in Todo Table ");
         Session session = getSession();

         try {

            Transaction trans = session.beginTransaction();

            // Set Task object with the new input parameters from status
            // input param
            logger.info("Update task Object with new values ");
            Todo newTask = new Todo();

            newTask.setProjectName(task.getProjectName());
            newTask.setProjectOwner(task.getProjectOwner());
            newTask.setDescription(task.getDescription());
            newTask.setReasonId(task.getReasonId());
            newTask.setStatusId(task.getStatusId());
            newTask.setPriority(task.getPriority());
            newTask.setCompletionDate(task.getCompletionDate());
            newTask.setCreatedDate(task.getCreatedDate());
            newTask.setCreatedBy(task.getCreatedBy());
            newTask.setUpdatedDate(task.getUpdatedDate());

            logger.info("Update newTask object into Database");
            session.update(newTask);
            trans.commit();

            // set update to true successfully committed transaction
            update = true;
            logger.debug("Setting update to " + update);

         } catch (Exception hEx) {

            logger.error("Error encountered in updating newTask Object");
            // Status Update failed
            update = false;
            logger.debug("Setting update to " + update);

            if (session.getTransaction().isActive()) {
               session.getTransaction().rollback();
               logger.info("Transaction rollback, existing task was not updated");
            }

            logger.error(hEx);

         }
      } else {

         logger.info("Task object is null : " + task);
         update = false;

      }
      return update;
   }

   /**
    * Search all tasks in Todo table
    * 
    * @author Lee
    * @return todoList returns the list of tasks or todo from todo table
    */
   public List<Todo> retrieveTodo() {

      List<Todo> todoList = new ArrayList<Todo>();

      Session session = getSession();

      try {

         Transaction tx = session.beginTransaction();

         logger.info("Retrieve all tasks from Todo table ");

         StringBuffer sql = new StringBuffer();
         sql.append("select projectName, projectOwner, statusId, reasonId, description, priority, ");
         sql.append("completionDate, createdDate, updatedDate, createdBy, updatedBy from Todo");
         // todoList = session.createQuery("from Todo").list();

         Query query = session.createQuery(sql.toString());
         List<Object> retrievedTodo = (List<Object>) query.list();
         logger.debug("RetrievedUserList contains : " + retrievedTodo.size());

         todoList.addAll(daoUtil.populateTodoList(retrievedTodo));

         tx.commit();

      } catch (HibernateException hEx) {

         logger.error("Error in retrieving tasks from todo table " + hEx);

      }
      return todoList;
   }

   /**
    * Retrieves a todo task using project search parameter. This method returns
    * lists of tasks that matches the project name provided.
    * 
    * @param String
    * @param projectName
    *           name of the project to be searched
    * @return listOfTasks returns all tasks from todo table
    * @author Lee
    */
   public List<Todo> searchTodoByProjectName(String projectName) {

      Session session = getSession();
      List<Todo> listOfTasks = new ArrayList<Todo>();

      try {

         Transaction tx = session.beginTransaction();

         StringBuilder sql = new StringBuilder();
         sql.append("select projectName, projectOwner, statusId, reasonId,");
         sql.append("description, priority, completionDate, createdDate, updatedDate,");
         sql.append("createdBy, updatedBy from com.gre.model.Todo where projectName like :projectname");

         logger.info("SQL Query to retrieve list of tasks based on project name search parameter: " + sql.toString());

         Query query = session.createQuery(sql.toString());
         query.setParameter("projectname", "%" + projectName + "%");

         List<Object> retrievedTodo = (List<Object>) query.list();
         logger.debug("RetrievedUserList contains : " + retrievedTodo.size());

         listOfTasks.addAll(daoUtil.populateTodoList(retrievedTodo));

         tx.commit();

      } catch (HibernateException hEx) {

         logger.error("There is a problem retrieving tasks using projectname " + hEx);

      }

      return listOfTasks;
   }

   /**
    * Retrieves a todo task using project search parameter. This method returns
    * lists of tasks that matches the project name provided.
    * 
    * @author Lee
    * @param projectName
    *           name of the project to look for in todo table
    * @return listOfTasks returns list of tasks retrieved from todo table
    */
   public List<Todo> searchTodoByProjectOwner(String projectOwner) {

      Session session = getSession();
      List<Todo> listOfTasks = new ArrayList<Todo>();

      try {

         Transaction tx = session.beginTransaction();

         StringBuilder sql = new StringBuilder();
         sql.append("select projectName, projectOwner, statusId, reasonId,");
         sql.append("description, priority, completionDate, createdDate, updatedDate,");
         sql.append("createdBy, updatedBy from Todo where projectOwner like :projectowner");

         logger.info("SQL Query to retrieve list of tasks based on project name search parameter: " + sql.toString());

         Query query = session.createQuery(sql.toString());
         query.setParameter("projectowner", "%" + projectOwner + "%");

         List<Object> retrievedTodo = (List<Object>) query.list();
         logger.debug("RetrievedUserList contains : " + retrievedTodo.size());

         listOfTasks.addAll(daoUtil.populateTodoList(retrievedTodo));

         tx.commit();

      } catch (HibernateException hEx) {

         logger.error("There is a problem retrieving tasks using project Owner " + hEx);

      }

      return listOfTasks;
   }

   /**
    * Retrieves tasks based on range of date specified on parameters
    * 
    * @param Date
    *           fromDate
    * @param Date
    *           toDate
    * @return List<Todo>
    * 
    * @author Lee
    */
   public List<Todo> searchTodoByDate(Date fromDate, Date toDate) {

      Session session = getSession();
      List<Todo> listOfTasks = new ArrayList<Todo>();

      try {

         Transaction tx = session.beginTransaction();

         Criteria criteria = session.createCriteria(Todo.class);
         criteria.add(Restrictions.between("date", fromDate, toDate));
         criteria.addOrder(Order.desc("date"));

         logger.info("SQL Query to retrieve list of tasks fromDate: " + fromDate.toString() + " toDate: "
               + toDate.toString());

         listOfTasks = criteria.list();

         int noOfResult = listOfTasks.size();
         logger.debug("Number of results returned: " + noOfResult);

         tx.commit();

      } catch (HibernateException hEx) {

         logger.error("There is a problem retrieving tasks using project Owner " + hEx);

      }

      return listOfTasks;
   }
}
