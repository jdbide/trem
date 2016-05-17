/**
 * 
 */
package com.avancial.socle.init;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.avancial.socle.business.JobPlanifBean;
import com.avancial.socle.data.controller.dao.JobPlanifDao;
import com.avancial.socle.data.model.databean.JobPlanifDataBean;
import com.avancial.socle.resources.constants.SOCLE_jobs;
import com.avancial.socle.resources.constants.SOCLE_navigation;

/**
 * @author bruno.legloahec
 *
 */
@WebServlet(loadOnStartup = 1, urlPatterns = "/init")
public class SocleInit extends HttpServlet {
   public Scheduler          sched;

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

      JobPlanifDao dao = new JobPlanifDao();
      List<JobPlanifDataBean> jobs = new ArrayList<>();
      jobs = dao.getAllActif();

      SchedulerFactory sf = new StdSchedulerFactory();
      this.sched = sf.getScheduler();

      for (JobPlanifDataBean jobPlanifDataBean : jobs) {
         Job newjob = null;
         JobPlanifBean bean = new JobPlanifBean(jobPlanifDataBean);
         try {
            System.out.println("Planification de : " + jobPlanifDataBean.getLibelleJobPlanif());
            newjob = (Job) Class.forName(jobPlanifDataBean.getJob().getClasseJob()).newInstance();
            Scheduler sched = sf.getScheduler();
            JobDetail job = JobBuilder.newJob(newjob.getClass()).withIdentity(bean.getLibelleJobPlanif(), "group1").build();
            job.getJobDataMap().put(SOCLE_jobs.JOB_CONTEXT_ID_JOB_PLANNIF.name(), bean.getIdJobPlanif());
            job.getJobDataMap().put(SOCLE_jobs.JOB_CONTEXT_LIBELLE_JOB_PLANNIF.name(), bean.getLibelleJobPlanif());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(bean.getLibelleJobPlanif(), "group1").withSchedule(CronScheduleBuilder.cronSchedule(bean.getCron())).build();
            sched.scheduleJob(job, trigger);

         } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
         }

      }

      // define the job and tie it to our HelloJob class

      // Trigger the job to run on the next round minute

      this.sched.start();
   }
}
