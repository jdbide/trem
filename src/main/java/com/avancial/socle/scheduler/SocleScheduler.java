/**
 * 
 */
package com.avancial.socle.scheduler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

   private List<JobPlanifDataBean> job              = new ArrayList<>();

   private static final String     GROUP_NAME       = "group1";

   @Inject
   JobPlanifDao                    dao;

   /**
    * @throws SchedulerException
    * 
    */

   public SocleScheduler() throws SchedulerException {
      SchedulerFactory sf = new StdSchedulerFactory();
      this.sched = sf.getScheduler();
   }

   @Override
   public void init() throws SchedulerException {
      this.scheduleActiveJobs();
      this.sched.start();
   }

   @Override
   public void reload() throws SchedulerException {
      this.unscheduleAllJobs();
      this.scheduleActiveJobs();
   }

   /**
    * Planification des jobs actifs
    * 
    * @throws SchedulerException
    */
   private void scheduleActiveJobs() throws SchedulerException {

      for (JobPlanifDataBean jobPlanifDataBean : job) {
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

   /**
    * Supprime tous les jobs de la planification
    * 
    * @throws SchedulerException
    */
   private void unscheduleAllJobs() throws SchedulerException {
      this.chargeActiveJobs();

      for (JobPlanifDataBean jobPlanifDataBean : this.job) {
         JobKey key = new JobKey(jobPlanifDataBean.getLibelleJobPlanif(), GROUP_NAME);
         if (this.sched.deleteJob(key))
            ;
         System.out.println(String.format("Le job %s a été correctement déplanifié", jobPlanifDataBean.getLibelleJobPlanif()));
      }
   }

   /**
    * Charge la liste des jobs actifs à partir de la base de données
    * 
    * @return
    */
   private void chargeActiveJobs() {
      this.job.clear();

      job = dao.getAllActif();
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