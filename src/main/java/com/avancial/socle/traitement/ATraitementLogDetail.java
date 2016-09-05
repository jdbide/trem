/**
 * 
 */
package com.avancial.socle.traitement;

import java.util.Date;

import com.avancial.socle.data.model.databean.LogTraitementDetailDataBean;
import com.avancial.socle.persistence.EntityManagerFactoryProvider;

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
   
   public void log(String message) throws Exception {
      try {
         this.logDetailBean = new LogTraitementDetailDataBean();
         this.logDetailBean.setDateLogTraitementDetail(new Date());
         this.logDetailBean.setLogTraitementDataBean(this.logBean);
         this.logDetailBean.setMessageTraitementDetail(message);
         
         
         this.emLog = EntityManagerFactoryProvider.getInstance().getEntityManagerFactory(ATraitementLog.PERSISTENCE_UNIT_NAME).createEntityManager();
         this.emLog.getTransaction().begin();
         
         try {
            this.emLog.persist(this.logDetailBean);
            this.emLog.getTransaction().commit();
            
         } catch (Exception ex) {
            ex.printStackTrace();
            this.emLog.getTransaction().rollback();
         } finally {
            this.emLog.close();
         }

      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }
   }

}
