/**
 * 
 */
package com.avancial.socle.traitement;

import java.util.Date;

import com.avancial.socle.data.model.databean.LogTraitementDetailDataBean;

/**
 * @author bruno.legloahec
 *
 */
public abstract class ATraitementLogDetail extends ATraitementLog {

   LogTraitementDetailDataBean logDetailBean;

   public void log(String message) {
      try {
         this.logDetailBean = new LogTraitementDetailDataBean();
         this.logDetailBean.setDateLogTraitementDetail(new Date());
         this.logDetailBean.setLogTraitementDataBean(this.logBean);
         this.logDetailBean.setMessageTraitementDetail(message);
         this.em.getTransaction().begin();
         this.em.persist(this.logDetailBean);
         this.em.flush();
         this.em.getTransaction().commit();
      } catch (Exception e) {
         e.printStackTrace();
      }
      
   }

}
