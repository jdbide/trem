/**
 * 
 */
package com.avancial.socle.traitement;

import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.avancial.app.resources.constants.APP_Const;
import com.avancial.socle.data.model.databean.LogTraitementDataBean;
import com.avancial.socle.persistence.EntityManagerFactoryProvider;

/**
 * @author bruno.legloahec
 *
 */
public abstract class ATraitementLog extends ATraitement {
   /**
    * 
    */
   private static final long       serialVersionUID = 1L;

   @Inject
   protected LogTraitementDataBean logBean;

   protected String                libelleTraitement;
   protected String                userTraitement;

   protected EntityManager         emLog;

   public ATraitementLog() {
      super();
   }

   @Override
   public void execute() throws Exception {
      this.startLogging();
      try {
         this.executeTraitement();
         this.logBean.setMessageTraitement("Le traitement s'est terminé sans erreur.");
      } catch (InterruptedException ex) {
         this.logBean.setMessageTraitement("Le traitement s'est terminé avec des erreurs.");
         Thread.currentThread().interrupt(); // Très important de réinterrompre
         throw ex;
      } catch (Exception e) {
         this.logBean.setExceptionTraitement(e.getMessage());
         this.logBean.setMessageTraitement("Le traitement s'est terminé avec des erreurs.");
      } finally {
         this.stopLogging();
         this.closeEmLog();
      }
   }

   private void closeEmLog() {
      // TODO Auto-generated method stub

   }

   protected void showProgress(String message) throws Exception {
      this.logBean.setMessageTraitement(message);
      this.saveLog();

   }

   /**
    * Initialisation du logging
    * 
    * @throws Exception
    */
   private void startLogging() throws Exception {
      this.logBean.setDateDebutLogTraitement(new Date());
      this.logBean.setLibelleLogTraitement(this.libelleTraitement);
      this.logBean.setLibelleUserLogTraitement(this.userTraitement);
      this.logBean.setMessageTraitement("Le traitement a démarré");
      this.saveLog();

   }

   protected void saveLog() throws Exception {
      this.emLog = EntityManagerFactoryProvider.getInstance().getEntityManagerFactory(APP_Const.PERSISTENCE_UNIT_NAME.toString()).createEntityManager();
      this.emLog.getTransaction().begin();
      try {
         this.emLog.persist(this.logBean);
         this.emLog.getTransaction().commit();
      } catch (Exception ex) {
         ex.printStackTrace();
         this.emLog.getTransaction().rollback();
      } finally {
         this.emLog.close();
      }

   }

   protected void updateLog() throws Exception {
      this.emLog = EntityManagerFactoryProvider.getInstance().getEntityManagerFactory(APP_Const.PERSISTENCE_UNIT_NAME.toString()).createEntityManager();
      this.emLog.getTransaction().begin();
      try {
         this.emLog.merge(this.logBean);
         this.emLog.getTransaction().commit();
      } catch (Exception ex) {
         ex.printStackTrace();
         this.emLog.getTransaction().rollback();
      } finally {
         this.emLog.clear();
      }
   }

   /**
    * 
    */
   private void stopLogging() throws Exception {
      this.logBean.setDateFinLogTraitement(new Date());
      this.updateLog();
   }

}