package com.gre.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.jboss.logging.Logger;

import com.gre.dao.util.DateUtility;
import com.gre.model.Todo;
import com.gre.service.TodoService;
import com.gre.service.impl.TodoServiceImpl;

public class Status extends WebPage {

   private static final Logger logger = Logger.getLogger(Status.class);

   private static final long serialVersionUID = 1L;

   RepeatingView tasks;

   /**
    * This constructor will initialize the navigation page home links
    * as well as the populate the table with data from retrieved from
    * database/
    * 
    * @author Lee
    * 
    * */
   public Status() {

      logger.info("Setting Status Page nagivation panel");
      
      Link home = new Link("Home") {

         @Override
         public void onClick() {

            this.setResponsePage(new HomePage());

         }
      };

      Link status = new Link("Status") {

         @Override
         public void onClick() {

            this.setResponsePage(new Status());

         }
      };

      Link task = new Link("Task") {

         @Override
         public void onClick() {

            this.setResponsePage(new ReminderPage());

         }
      };
      
      List<String[]> allTask = loadTaskFromDB();
      ListDataProvider<String[]> taskDataProvider = new ListDataProvider<String[]>(allTask);

      DataView<String []> viewData = new DataView<String[]>("rows", taskDataProvider) {
         
         @Override
         protected void populateItem(Item<String[]> item) {
            
            String[] taskArray = item.getModelObject();
            
            RepeatingView rView = new RepeatingView("dataRow");
            
            for (int x=0; x<taskArray.length; x++){
               
               rView.add(new Label( rView.newChildId(), taskArray[x]));
               
            }
            item.add(rView);
         }
      };
      
      viewData.setItemsPerPage(10);
      
      add(home);
      add(status);
      add(task);
      add(viewData);
      add(new PagingNavigator("pagingNavigator", viewData));
   }

   /**
    * First attempt at repeatingview not working
    * 
    * @deprecated
    * */
   public RepeatingView populateTasks() {

      AbstractItem entries = new AbstractItem(tasks.newChildId());

      TodoService todoMgr = new TodoServiceImpl();

      List<Todo> listOfTasks = new ArrayList<Todo>();

      listOfTasks.addAll(todoMgr.retrieveTodo());

      for (Todo taskEntry : listOfTasks) {

         entries.add(new Label("taskid", String.valueOf(taskEntry.getTodoId())));
         entries.add(new Label("projectname", taskEntry.getProjectName()));
         entries.add(new Label("projectowner", taskEntry.getProjectOwner()));
         entries.add(new Label("statusid", taskEntry.getStatusId()));
         entries.add(new Label("reasonid", taskEntry.getReasonId()));
         entries.add(new Label("description", taskEntry.getDescription()));
         entries.add(new Label("priority", taskEntry.getPriority()));
         entries.add(new Label("completiondate", taskEntry.getCompletionDate()));
         entries.add(new Label("createddate", taskEntry.getCreatedDate()));
         entries.add(new Label("updateddate", taskEntry.getUpdatedDate()));
         entries.add(new Label("createdby", taskEntry.getCreatedBy()));
         entries.add(new Label("updatedby", taskEntry.getUpdatedBy()));

      }

      tasks.add(entries);

      return tasks;
   }

   /**
    * Retrieves all tasks by calling todo service method. The method
    * returns a list of String array which was obtained from the List<Todo>
    * returned from Task Service implementation
    * 
    * @author Lee
    * @return List<String[]>
    * 
    * */
   public List<String[]> loadTaskFromDB() {

      logger.info("Loading data from Todo Table ");
      
      DateUtility dateUtil = new DateUtility();
      TodoService todoMgr = new TodoServiceImpl();

      List<Todo> listOfTasks = new ArrayList<Todo>();
      List<String[]> tasksArray = new ArrayList<String[]>();

      listOfTasks.addAll(todoMgr.retrieveTodo());
      logger.info("Data retrieved there are : "+ listOfTasks.size()+ " tasks");
      
      logger.info("Converting Todo object into String array ");
      for (Todo entry: listOfTasks){
         
         String [] taskArr = new String[]{String.valueOf(entry.getTodoId()), entry.getProjectName(),
               entry.getProjectOwner(), String.valueOf(entry.getStatusId()), String.valueOf(entry.getReasonId()),
               entry.getDescription(), entry.getPriority(), dateUtil.formatDateToString(entry.getCompletionDate()),
               dateUtil.formatDateToString(entry.getCreatedDate()),dateUtil.formatDateToString(entry.getUpdatedDate()),
               entry.getCreatedBy(),entry.getUpdatedBy() };
         
         tasksArray.add(taskArr);
      }
      
      return tasksArray;

   }

}
