/**
 * 
 */
package com.avancial.socle.traitement;

import java.util.Date;

import com.avancial.socle.data.model.databean.LogTraitementDetailDataBean;
import com.avancial.socle.persistence.EntityManagerFactoryProvider;
import com.avancial.socle.resources.constants.SOCLE_constants;

/**
 * @author bruno.legloahec
 *
 */
public abstract class ATraitementLogDetail extends ATraitementLog {

   /**
    * 
    */
   private static final long   serialVersionUID = 1L;

   LogTraitementDetailDataBean logDetailBean;

   public void log(String message) {
      this.logDetailBean = new LogTraitementDetailDataBean();
      this.logDetailBean.setDateLogTraitementDetail(new Date());
      this.logDetailBean.setLogTraitementDataBean(this.logBean);
      this.logDetailBean.setMessageTraitementDetail(message);

      this.log();
   }
   
   public void error(String message, Exception e) {
      this.logDetailBean = new LogTraitementDetailDataBean();
      this.logDetailBean.setDateLogTraitementDetail(new Date());
      this.logDetailBean.setLogTraitementDataBean(this.logBean);
      this.logDetailBean.setMessageTraitementDetail(message);
      this.logDetailBean.setExceptionTraitementDetail(e.getMessage());
      
      this.log();
   }
   
   private void log() {
      this.emLog = EntityManagerFactoryProvider.getInstance().getEntityManagerFactory(SOCLE_constants.PERSISTENCE_UNIT_NAME.toString()).createEntityManager();
      this.emLog.getTransaction().begin();

      try {
         this.emLog.persist(this.logDetailBean);
         this.emLog.getTransaction().commit();

      } catch (Exception ex) {
         ex.printStackTrace();
         this.emLog.getTransaction().rollback();
      } finally {
         this.emLog.clear();
         this.emLog.close();
      }
   }

}
