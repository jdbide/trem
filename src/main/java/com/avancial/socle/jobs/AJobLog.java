/**
 * 
 */
package com.avancial.socle.jobs;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.logging.ALogBean;
import com.avancial.socle.logging.ILogger;
import com.avancial.socle.logging.LogJobBean;
import com.avancial.socle.logging.Logger;
import com.avancial.socle.resources.constants.SOCLE_jobs;
import com.avancial.socle.resources.constants.SOCLE_logSeverite;
import com.avancial.socle.resources.constants.SOCLE_logSortie;

/**
 * @author bruno.legloahec
 *
 */
public abstract class AJobLog extends AJob {
   protected ILogger           logger;
   protected ALogBean          joblogBean;

   private final static String SCHEDULER_USER = "Scheduler";

   /**
    * Constructeur
    */
   public AJobLog() {
      this.logger = new Logger();
      this.initLogStrategy();
      this.joblogBean = new LogJobBean();
   }

   /**
    * Ne doit pas être redéfini par les enfants
    */
   @Override
   public final void execute(JobExecutionContext context) throws JobExecutionException {
      try {
         this.startLogging(context);

         this.executeJob();

         ((LogJobBean) this.joblogBean).setEtatOkJob(true);
         ((LogJobBean) this.joblogBean).setSeverite(SOCLE_logSeverite.INFO);
         this.stopLogging();

      } catch (Exception | ASocleException e) {
         try {
            ((LogJobBean) this.joblogBean).setEtatOkJob(false);
            ((LogJobBean) this.joblogBean).setSeverite(SOCLE_logSeverite.ERROR);
            this.stopLogging();
         } catch (Exception | ASocleException e1) {
            e1.printStackTrace();
         }
      }
   }

   /**
    * @throws ASocleException
    * @throws Exception
    * 
    */
   private void stopLogging() throws Exception, ASocleException {
      ((LogJobBean) this.joblogBean).setDateFinJob(new Date());
      this.logger.log(this.joblogBean);
   }

   /**
    * @param context
    * @throws ASocleException
    * @throws Exception
    */
   private void startLogging(JobExecutionContext context) throws Exception, ASocleException {
      this.idJobPlanif = context.getJobDetail().getJobDataMap().getLong(SOCLE_jobs.JOB_CONTEXT_ID_JOB_PLANNIF.name());
      this.libelleJobPlanif = context.getJobDetail().getJobDataMap().getString(SOCLE_jobs.JOB_CONTEXT_LIBELLE_JOB_PLANNIF.name());
      if (this.ihmManagedBean != null) {
         if (this.ihmManagedBean.getCurrentUser() != null)
            this.userInfos = String.format("%s - %s", this.ihmManagedBean.getCurrentUser().getLoginUser(), this.ihmManagedBean.getCurrentUser().getNomUser());
      } else
         this.userInfos = AJobLog.SCHEDULER_USER;

      ((LogJobBean) this.joblogBean).setDateDebutJob(new Date());
      ((LogJobBean) this.joblogBean).setIdJobPlanif(this.idJobPlanif);
      ((LogJobBean) this.joblogBean).setLibelleJob(this.libelleJobPlanif);
      ((LogJobBean) this.joblogBean).setLoginUser(this.userInfos);
      this.logger.log(this.joblogBean);

   }

   /**
    * Définit la stratégie du logger par défaut (Console). A surcharger pour la modifier
    * 
    */
   public void initLogStrategy() {
      this.logger.ajouterSortie(SOCLE_logSortie.CONSOLE);
   }

}
