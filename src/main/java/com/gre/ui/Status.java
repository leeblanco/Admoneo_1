package com.gre.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.jboss.logging.Logger;

import com.gre.model.Todo;
import com.gre.service.TodoService;
import com.gre.service.impl.TodoServiceImpl;

public class Status extends WebPage {

   private static final Logger logger = Logger.getLogger(Status.class);
   
   private static final long serialVersionUID = 1L;

   RepeatingView tasks;
   
   public Status() {

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

      this.add(home);
      this.add(status);
      this.add(task);
      
      tasks = new RepeatingView("taskList");
      this.add(tasks);

   }
   
   
   public RepeatingView populateTasks(){
      
      AbstractItem entries = new AbstractItem(tasks.newChildId());
      tasks.add(entries);
      
      TodoService todoMgr = new TodoServiceImpl();
      
      List<Todo> listOfTasks = new ArrayList<Todo>();
      
      listOfTasks.addAll(todoMgr.retrieveTodo());
      
      for (Todo taskEntry: listOfTasks){
         
         tasks.add(new Label("todoid", taskEntry.getTodoId()));
         tasks.add(new Label("projectname", taskEntry.getProjectName()));
         tasks.add(new Label("projectowner", taskEntry.getProjectOwner()));
         tasks.add(new Label("statusid", taskEntry.getStatusId()));
         tasks.add(new Label("reasonid", taskEntry.getReasonId()));
         tasks.add(new Label("description", taskEntry.getDescription()));
         tasks.add(new Label("priority", taskEntry.getPriority()));
         tasks.add(new Label("completiondate", taskEntry.getCompletionDate()));
         tasks.add(new Label("createddate", taskEntry.getCreatedDate()));
         tasks.add(new Label("updateddate", taskEntry.getUpdatedDate()));
         tasks.add(new Label("createdby", taskEntry.getCreatedBy()));
         tasks.add(new Label("updatedby", taskEntry.getUpdatedBy()));
         
      }
      
      return tasks;
   }

}
