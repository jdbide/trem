/**
 * 
 */
package com.avancial.socle.traitement;

import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.avancial.socle.data.model.databean.LogTraitementDataBean;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * @author bruno.legloahec
 *
 */
public abstract class ATraitementLog extends ATraitement {

   @Inject
   protected LogTraitementDataBean logBean;

   protected String                libelleTraitement;
   protected String                userTraitement;

   @Inject
   @Socle_PUSocle
   protected EntityManager         em;

   @Override
   public void execute() {
      this.startLogging();
      try {
         this.executeTraitement();
         this.logBean.setMessageTraitement("Le traitement s'est terminé sans erreur.");
      } catch (Exception e) {
         this.logBean.setExceptionTraitement(e.getMessage());
         this.logBean.setMessageTraitement("Le traitement s'est terminé avec des erreurs.");
      } finally {
         this.stopLogging();
      }
   }

   protected void showProgress(String message) {
      this.logBean.setMessageTraitement(message);
      this.saveLog();

   }

   /**
    * Initialisation du logging
    */
   private void startLogging() {
      this.logBean.setDateDebutLogTraitement(new Date());
      this.logBean.setLibelleLogTraitement(this.libelleTraitement);
      this.logBean.setLibelleUserLogTraitement(this.userTraitement);
      this.logBean.setMessageTraitement("Le traitement a démarré");
      this.saveLog();

   }

   /**
    * 
    */
   protected void saveLog() {
      this.em.getTransaction().begin();
      this.em.persist(this.logBean);
      this.em.flush();
      this.em.getTransaction().commit();

   }

   /**
    * 
    */
   private void stopLogging() {
      this.logBean.setDateFinLogTraitement(new Date());
      this.saveLog();
   }

}
