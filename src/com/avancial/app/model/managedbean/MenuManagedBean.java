package com.avancial.app.model.managedbean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.avancial.app.resources.constants.APP_MENU_constants;
import com.avancial.socle.model.managedbean.SocleMenuManagedBean;

/**
 * Managed Bean de gestion de la page liste utilisateurs public
 * 
 * @author guillaume.bouziou
 * 
 */
@Named("menuApp")
@SessionScoped
public class MenuManagedBean extends SocleMenuManagedBean {
   private static final long serialVersionUID = 1L;
   
   public static String goTest() {
	   return APP_MENU_constants.NAVIGATION_TEST.toString();
   }
   
   public static String goImportData() {
	   return APP_MENU_constants.NAVIGATION_IMPORT_DATA.toString();
   }
}
