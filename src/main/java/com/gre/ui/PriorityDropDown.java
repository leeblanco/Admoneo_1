package com.gre.ui;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.PropertyModel;
import org.jboss.logging.Logger;

import com.gre.model.Priority;
import com.gre.priority.service.PriorityService;
import com.gre.priority.service.impl.PriorityServiceImpl;

public class PriorityDropDown extends WebPage {

   private static final long serialVersionUID = 1L;

   private static final Logger logger = Logger.getLogger(PriorityDropDown.class);
   
   private String selected = "Medium";
   
   public PriorityDropDown(String id) {
    
      List<Priority> priorities = listOfPriorities();
      
      DropDownChoice prioritiesListDropDown = 
            new DropDownChoice(id, new PropertyModel(this, "selected"), priorities );
      
      Form formPriority = new Form("priorityForm") {
         
         @Override
         public void onSubmit() {
            
         }
      };
      
      add(formPriority);
      
      formPriority.add(prioritiesListDropDown);
      
   }
   
   /**
    * Retrieves the list of priorities listed in Database;
    * 
    * @author Lee
    * */
   public List<Priority> listOfPriorities() {
      
      PriorityService priorityMgr = new PriorityServiceImpl();
      
      return priorityMgr.retrievePriorities();
      
   }
   
   /**
    * Returns the selected value from dropdown
    * 
    * @author Lee
    * */
   public String getSelectedValue() {
      
      logger.info("Selected value : "+ selected);
      
      return selected;
      
   }
}
