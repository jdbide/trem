/**
 * 
 */
package com.avancial.socle.init;

import java.io.IOException;
import java.util.logging.Level;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.quartz.SchedulerException;

import com.avancial.socle.resources.annotations.Role;
import com.avancial.socle.resources.constants.SOCLE_navigation;
import com.avancial.socle.scheduler.ISocleScheduler;
import com.avancial.socle.security.SecurityInterceptor;

/**
 * @author bruno.legloahec
 *
 */

@WebServlet(loadOnStartup = 1, urlPatterns = "/init")

public class SocleInit extends HttpServlet {

   @Inject
   ISocleScheduler            socleScheduler;

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /*
    * (non-Javadoc)
    * 
    * @see javax.servlet.GenericServlet#init()
    */
   
   @Override
   public void init() throws ServletException {
	   
	    
      super.init();
      java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
      java.util.logging.Logger.getLogger("om.sun.faces.config.ConfigureListener").setLevel(Level.SEVERE);
      java.util.logging.Logger.getLogger("org.apache.catalina.startup").setLevel(Level.SEVERE);

      System.out.println("**********************************************");
      System.out.println("********  Application initialization  ********");
      System.out.println("**********************************************");
      try {
         this.quartzInit();
         FacesContext.getCurrentInstance().getExternalContext().redirect(SOCLE_navigation.NAVIGATION_ACCUEIL.name());
      } catch (SchedulerException | IOException e) {
         e.printStackTrace();
      }
   }

   /**
    * @throws SchedulerException
    * 
    */
   private void quartzInit() throws SchedulerException {

      System.out.println("*****  SCHEDULER INIT  *****");
     this.socleScheduler.init();
      System.out.println("*****  SCHEDULER INITIALISED  *****");
  	System.out.println("*****   JOB RELOAD  *****");
    this.socleScheduler.reload();
    System.out.println("*****   JOBS RELOADED  *****");
   
   
   }
}
