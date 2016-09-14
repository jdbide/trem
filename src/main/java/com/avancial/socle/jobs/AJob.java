/**
 * 
 */
package com.avancial.socle.jobs;

import java.io.Serializable;

import javax.inject.Inject;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.avancial.socle.exceptions.impl.ASocleException;
import com.avancial.socle.model.managedbean.IhmManagedBean;

/**
 * 
 * 
 * @author bruno.legloahec
 *
 */
public abstract class AJob implements Job, IJob, Serializable {
   // protected ILogger logger;

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   protected long            idJobPlanif;
   protected String          libelleJobPlanif;
   protected String          userInfos        = "";

   @Inject
   IhmManagedBean            ihmManagedBean;

   @Override
   public void execute(JobExecutionContext context) throws JobExecutionException {
      try {

         this.executeJob();

      } catch (Exception | ASocleException e) {

         e.printStackTrace();
      }
   }
}