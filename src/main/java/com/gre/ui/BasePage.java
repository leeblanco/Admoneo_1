package com.gre.ui;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Base page sets the main navigational panel such as Home
 * Task and Status links to be available on each page. Instead
 * of coding them to each class.
 * 
 * @author Lee
 * */
public class BasePage extends WebPage {

   public BasePage() {

      this(new PageParameters());

   }

   public BasePage(final PageParameters pageParam) {
      
      super(pageParam);
      
   }
   
   public BasePage(IModel model) {

      super(model);
      this.add(new NavigationLink("navigation"));
      
   }
   
}
