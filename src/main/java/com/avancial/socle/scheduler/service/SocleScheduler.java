/**
 * 
 */
package com.avancial.socle.scheduler.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.spi.JobFactory;

import com.avancial.socle.business.JobPlanifBean;
import com.avancial.socle.data.controller.dao.JobPlanifDao;
import com.avancial.socle.data.model.databean.JobPlanifDataBean;
import com.avancial.socle.resources.constants.SOCLE_jobs;

/**
 * @author bruno.legloahec
 *
 */
@ApplicationScoped
public class SocleScheduler implements ISocleScheduler, Serializable {
   /**
   * 
   */
   private static final long       serialVersionUID = 1L;
   private Scheduler               sched;

   private List<JobPlanifDataBean> jobs             = new ArrayList<>();

   private static final String     GROUP_NAME       = "socle";

   @Inject
   JobPlanifDao                    dao;

   @Inject
   private JobFactory              cdiJobFactory;

   /**
    * @throws SchedulerException
    * 
    */

   public SocleScheduler() throws SchedulerException {

   }

   @PostConstruct
   public void init() throws SchedulerException {
      SchedulerFactory sf = new StdSchedulerFactory();
      this.sched = sf.getScheduler();
      this.sched.setJobFactory(this.cdiJobFactory);
      this.sched.start();
   }

   public void reload() throws SchedulerException {
      this.unscheduleAllJobs();
      this.scheduleAllActiveJobs();
   }

   /**
    * Supprime tous les jobs de la planification
    * 
    * @throws SchedulerException
    */
   @Override
   public void unscheduleAllJobs() throws SchedulerException {
      this.chargeActiveJobs();

      for (JobKey jobKey : sched.getJobKeys(GroupMatcher.jobGroupEquals(SocleScheduler.GROUP_NAME))) {
         if (this.sched.deleteJob(jobKey))
            System.out.println(String.format("Le job %s a été correctement déplanifié", jobKey));
      }

      // for(
      //
      // JobPlanifDataBean jobPlanifDataBean:this.jobs)
      // {
      // JobKey key = new JobKey(jobPlanifDataBean.getLibelleJobPlanif(), GROUP_NAME);
      //
   }

   /**
    * Charge la liste des jobs actifs à partir de la base de données
    * 
    * @return
    */
   private void chargeActiveJobs() {
      this.jobs.clear();
      jobs = dao.getAllActif();
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.scheduler.service.ISocleScheduler#scheduleAllActiveJobs()
    */
   @Override
   public void scheduleAllActiveJobs() throws SchedulerException {
      this.chargeActiveJobs();
      for (JobPlanifDataBean jobPlanifDataBean : jobs) {
         Job newjob = null;
         JobPlanifBean bean = new JobPlanifBean(jobPlanifDataBean);
         try {
            System.out.println("Planification de : " + jobPlanifDataBean.getLibelleJobPlanif());
            newjob = (Job) Class.forName(jobPlanifDataBean.getJob().getClasseJob()).newInstance();
            JobDetail job = JobBuilder.newJob(newjob.getClass()).withIdentity(bean.getLibelleJobPlanif(), GROUP_NAME).build();
            job.getJobDataMap().put(SOCLE_jobs.JOB_CONTEXT_ID_JOB_PLANNIF.name(), bean.getIdJobPlanif());
            job.getJobDataMap().put(SOCLE_jobs.JOB_CONTEXT_LIBELLE_JOB_PLANNIF.name(), bean.getLibelleJobPlanif());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(bean.getLibelleJobPlanif(), GROUP_NAME).withSchedule(CronScheduleBuilder.cronSchedule(bean.getCron())).build();
            sched.scheduleJob(job, trigger);

         } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
         }

      }

   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.scheduler.service.ISocleScheduler#reloadlActiveJobs()
    */
   @Override
   public void reloadlActiveJobs() throws SchedulerException {
      this.unscheduleAllJobs();
      this.scheduleAllActiveJobs();

   }

}

/*
 * @Override public void init() throws SchedulerException { JobPlanifDao dao = new JobPlanifDao(); List<JobPlanifDataBean> jobs = new ArrayList<>(); jobs = dao.getAllActif();
 * 
 * SchedulerFactory sf = new StdSchedulerFactory(); this.sched = sf.getScheduler();
 * 
 * for (JobPlanifDataBean jobPlanifDataBean : jobs) { Job newjob = null; JobPlanifBean bean = new JobPlanifBean(jobPlanifDataBean); try { System.out.println("Planification de : " + jobPlanifDataBean.getLibelleJobPlanif()); newjob = (Job)
 * Class.forName(jobPlanifDataBean.getJob().getClasseJob()).newInstance(); Scheduler sched = sf.getScheduler(); JobDetail job = JobBuilder.newJob(newjob.getClass()).withIdentity(bean.getLibelleJobPlanif(), "group1").build(); job.getJobDataMap().put(SOCLE_jobs.JOB_CONTEXT_ID_JOB_PLANNIF.name(),
 * bean.getIdJobPlanif()); job.getJobDataMap().put(SOCLE_jobs.JOB_CONTEXT_LIBELLE_JOB_PLANNIF.name(), bean.getLibelleJobPlanif()); Trigger trigger = TriggerBuilder.newTrigger().withIdentity(bean.getLibelleJobPlanif(), "group1").withSchedule(CronScheduleBuilder.cronSchedule(bean.getCron())). build();
 * sched.scheduleJob(job, trigger);
 * 
 * } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) { e.printStackTrace(); }
 * 
 * }
 * 
 * // define the job and tie it to our HelloJob class
 * 
 * // Trigger the job to run on the next round minute
 * 
 * this.sched.start();
 * 
 * }
 * 
 * // define the job and tie it to our HelloJob class
 * 
 * // Trigger the job to run on the next round minute
 * 
 * 
 * 
 * 
 * /**
 * 
 */