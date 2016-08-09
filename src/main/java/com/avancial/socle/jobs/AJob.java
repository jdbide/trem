/**
 * 
 */
package com.avancial.socle.jobs;

import javax.inject.Inject;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.model.managedbean.IhmManagedBean;

/**
 * 
 * 
 * @author bruno.legloahec
 *
 */
public abstract class AJob implements Job, IJob {
   // protected ILogger logger;

   protected long   idJobPlanif;
   protected String libelleJobPlanif;
   protected String userInfos = "";

   @Inject
   IhmManagedBean   ihmManagedBean;

   @Override
   public void execute(JobExecutionContext context) throws JobExecutionException {
      try {
         this.executeJob();

      } catch (Exception | ASocleException e) {

         e.printStackTrace();
      }
   }
}