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
      this.logDetailBean = new LogTraitementDetailDataBean();
      this.logDetailBean.setDateLogTraitementDetail(new Date());
      this.logDetailBean.setLogTraitementDataBean(this.logBean);
      this.logDetailBean.setMessageTraitementDetail(message);
      this.entityManagerSocle.getTransaction().begin();
      this.entityManagerSocle.merge(this.logDetailBean);
      this.entityManagerSocle.flush();
      this.entityManagerSocle.getTransaction().commit();
   }

}
