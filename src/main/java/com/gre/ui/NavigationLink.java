package com.gre.ui;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class NavigationLink extends Panel {

   private static final long serialVersionUID = 1L;

   public NavigationLink(String id) {

      super(id);

      Link home = new Link("Home") {

         @Override
         public void onClick() {

            this.setResponsePage(new HomePage());
         }
      };

      Link task = new Link("Task") {

         @Override
         public void onClick() {

            this.setResponsePage(new ReminderPage());

         }
      };
      
      Link status = new Link("Status"){
         
         @Override
         public void onClick() {
            
            this.setResponsePage(new Status());
            
         }
      };

      this.add(home);
      this.add(task);
      this.add(status);
      
   }
}
