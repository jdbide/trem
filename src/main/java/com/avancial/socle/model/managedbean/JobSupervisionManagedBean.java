/**
 * @author ismael.yahiani 
 * managed bean de l'IHM job supervision 
 */
package com.avancial.socle.model.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;



import com.avancial.socle.data.controller.dao.JobPlanifDao;
import com.avancial.socle.data.model.databean.JobPlanifDataBean;
import com.avancial.socle.jobs.JobSupervision;
import com.avancial.socle.utils.StringToDate;

@Named("jobSupervision")
@ViewScoped
public class JobSupervisionManagedBean {

   private List<JobPlanifDataBean> listPlanif;
   private List<JobSupervision> listSuperviser;
    private boolean showImage;

   public JobSupervisionManagedBean() {
      this.listPlanif = new JobPlanifDao().getAll();
      this.listSuperviser = new ArrayList<>();
      
   }

   @PostConstruct
   public void init() {
      this.getSchedJobs();
      this.showImage = false ;
   }
   
   private void getSchedJobs() {

      try {
         Scheduler sched = new StdSchedulerFactory().getScheduler();
         for (String group : sched.getJobGroupNames()) {
            this.listSuperviser.clear();

            for (JobKey jobKey : sched.getJobKeys(GroupMatcher.jobGroupEquals(group))) {
               JobSupervision job = new JobSupervision();
               job.setJobNom(jobKey.getName());

               for (Trigger triggerKey : sched.getTriggersOfJob(jobKey)) {

                  job.setNextExec(StringToDate.toStringByFormat(triggerKey.getNextFireTime(), "dateSlashAvecHeure"));
                  job.setJobPlanifType(getTypePlanif(jobKey.getName()));
                  for (JobExecutionContext context : sched.getCurrentlyExecutingJobs()) {
                     if (context.getJobDetail().getKey() == jobKey)
                        {
                           job.setEtat("Actif"); 
                           this.setShowImage(true);
                        }
                     else
                        job.setEtat("Innactif");
                  }
               } 
               
               this.listSuperviser.add(job);
            }
         }
      } catch (Exception e) {
         System.out.println(e.getMessage());
      }
   }

   private String getTypePlanif(String jobName) {

      for (JobPlanifDataBean jobPlanifDataBean : this.listPlanif) {
         if (jobName.equalsIgnoreCase(jobPlanifDataBean.getLibelleJobPlanif()))
            return jobPlanifDataBean.getJobPlanifTypeDataBean().getLibelleJobPlanifType();
      }

      return null;
   }

   public String refresh() {
      try {
         this.init();
      } catch (Exception e) {

         e.printStackTrace();
      }
      return null;
   }

   public List<JobPlanifDataBean> getListPlanif() {
      return this.listPlanif;
   }

   public void setListPlanif(List<JobPlanifDataBean> listPlanif) {
      this.listPlanif = listPlanif;
   }

   public List<JobSupervision> getListSuperviser() {
      return this.listSuperviser;
   }

   public void setListSuperviser(List<JobSupervision> listSuperviser) {
      this.listSuperviser = listSuperviser;
   }

   public boolean isShowImage() {
      return this.showImage;
   }

   public void setShowImage(boolean showImage) {
      this.showImage = showImage;
   }

  
}
