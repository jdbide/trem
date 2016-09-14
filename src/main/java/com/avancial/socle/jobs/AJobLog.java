/**
 * 
 */
package com.avancial.socle.jobs;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.avancial.socle.data.model.databean.JobPlanifDataBean;
import com.avancial.socle.data.model.databean.LogJobDataBean;
import com.avancial.socle.exceptions.impl.ASocleException;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
import com.avancial.socle.resources.constants.SOCLE_jobs;

/**
 * @author bruno.legloahec
 *
 */
public abstract class AJobLog extends AJob {
   // protected ILogger logger;
   // protected LogJobBean joblogBean;

   /**
    * 
    */
   private static final long   serialVersionUID = -4611973342322362966L;
   
   @Inject
   @Socle_PUSocle
   EntityManager               em;
   
   @Inject
   LogJobDataBean              joblogBean;

   private final static String SCHEDULER_USER   = "Scheduler";

	/**
	 * méthode de pré-destruction.
	 */
	@PreDestroy
	public void preDestroy() {
		if(this.em.isOpen()) {
			this.em.close();
		}
	}
   
   /**
    * Constructeur
    */
   public AJobLog() {
      // this.logger = new Logger();
      // this.initLogStrategy();
      // this.joblogBean = new LogJobBean();
   }

   /**
    * Ne doit pas être redéfini par les enfants
    */
   @Override
   public final void execute(JobExecutionContext context) throws JobExecutionException {
      try {
         this.startLogging(context);

         this.executeJob();

         this.joblogBean.setEtatOkLogJob(true);
         // this.joblogBean.setSeverite(SOCLE_logSeverite.INFO);
         this.stopLogging();

      } catch (Exception | ASocleException e) {
         try {
            this.joblogBean.setEtatOkLogJob(false);
            // this.joblogBean.setSeverite(SOCLE_logSeverite.ERROR);
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
      this.joblogBean.setDateFinLogJob(new Date());
      this.saveLog();
   }

   /**
    * @param context
    * @throws ASocleException
    * @throws Exception
    */
   private void startLogging(JobExecutionContext context) throws Exception, ASocleException {
      this.idJobPlanif = context.getJobDetail().getJobDataMap().getLong(SOCLE_jobs.JOB_CONTEXT_ID_JOB_PLANNIF.name());
      this.libelleJobPlanif = context.getJobDetail().getJobDataMap().getString(SOCLE_jobs.JOB_CONTEXT_LIBELLE_JOB_PLANNIF.name());
      // if (this.ihmManagedBean != null) {
      // if (this.ihmManagedBean.getCurrentUser() != null)
      // this.userInfos = String.format("%s - %s", this.ihmManagedBean.getCurrentUser().getLoginUser(), this.ihmManagedBean.getCurrentUser().getNomUser());
      // } else
      this.userInfos = AJobLog.SCHEDULER_USER;

      this.joblogBean.setEtatOkLogJob(false);
      this.joblogBean.setDateDebutLogJob(new Date());
      this.joblogBean.setJobPlanif(em.find(JobPlanifDataBean.class, this.idJobPlanif));
      this.joblogBean.setLibelleJobLogJob(this.libelleJobPlanif);
      this.joblogBean.setLibelleUserLogJob(this.userInfos);
      this.saveLog();

   }

   /**
    * 
    */
   protected void saveLog() {
      try {
         this.em.getTransaction().begin();
         this.em.persist(this.joblogBean);
         this.em.flush();
         this.em.getTransaction().commit();
      } catch (Exception e) {
         this.em.getTransaction().rollback();
      }

   }

   /**
    * Définit la stratégie du logger par défaut (Console). A surcharger pour la modifier
    * 
    */
   public void initLogStrategy() {
      // this.logger.ajouterSortie(SOCLE_logSortie.CONSOLE);
   }

}
