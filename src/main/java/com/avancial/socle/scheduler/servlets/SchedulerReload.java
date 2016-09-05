package com.avancial.socle.scheduler.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.SchedulerException;

import com.avancial.socle.scheduler.service.ISocleScheduler;
import com.avancial.socle.security.SecurityManagedBean;

/**
 * Servlet implementation class SchedulerReload
 */
@WebServlet("/SchedulerReload")
public class SchedulerReload extends HttpServlet {
   private static final long serialVersionUID = 1L;

   @Inject
   ISocleScheduler           socleScheduler;

   @Inject
   SecurityManagedBean       securityManager;

   /**
    * @see HttpServlet#HttpServlet()
    */
   public SchedulerReload() {
      super();
   }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      System.out.println("*****   JOB RELOAD  *****");
      try {
         this.socleScheduler.reloadlActiveJobs();
      } catch (SchedulerException e) {
         e.printStackTrace();
      }
      System.out.println("*****   JOBS RELOADED  *****");

   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
