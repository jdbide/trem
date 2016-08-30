/**
 * 
 */
package com.avancial.socle.traitement;

import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.avancial.socle.data.model.databean.LogTraitementDataBean;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * @author bruno.legloahec
 *
 */
public abstract class ATraitementLog extends ATraitement {
   protected static Logger logger;
   @Inject
   protected LogTraitementDataBean logBean;

   protected String                libelleTraitement;
   protected String                userTraitement;

   @Inject
   @Socle_PUSocle
   protected EntityManager         em;

   @Override
   public void execute() throws Exception  {
      logger.info("ATraitementLog -> Start execute");
      this.startLogging();
      try {
         this.executeTraitement();
         this.logBean.setMessageTraitement("Le traitement s'est terminé sans erreur.");
         this.logger.info("Le traitement (ATraitementLog) s'est terminé sans erreur.");
      } catch (Exception e) {
         this.logger.error("Exception class : ATraitementLog -> Le traitement s'est terminé avec des erreurs.", e);
         this.logBean.setExceptionTraitement(e.getMessage());
         this.logBean.setMessageTraitement("Le traitement s'est terminé avec des erreurs.");
         throw e;
      } finally {
         this.stopLogging();
         logger.info("ATraitementLog -> End execute");
      }
   }

   protected void showProgress(String message) throws Exception {
      this.logBean.setMessageTraitement(message);
      this.saveLog();

   }

   /**
    * Initialisation du logging
    */
   private void startLogging() throws Exception {
      this.logBean.setDateDebutLogTraitement(new Date());
      this.logBean.setLibelleLogTraitement(this.libelleTraitement);
      this.logBean.setLibelleUserLogTraitement(this.userTraitement);
      this.logBean.setMessageTraitement("Le traitement a démarré");
      this.saveLog();

   }

   /**
    * 
    */
   protected void saveLog() throws Exception  {
      if (!this.em.getTransaction().isActive())
         this.em.getTransaction().begin();

      this.em.persist(this.logBean);
      this.em.flush();
      this.em.getTransaction().commit();
      
      logger.info("--> Save log");
   }
   
   protected void updateLog() throws Exception  {
      if (!this.em.getTransaction().isActive())
         this.em.getTransaction().begin();


      this.em.merge(this.logBean);
      this.em.flush();
      this.em.getTransaction().commit();
      
      logger.info("Update log");
   }

   /**
    * 
    */
   private void stopLogging() throws Exception {
      this.logBean.setDateFinLogTraitement(new Date());
      this.updateLog();
   }

}
