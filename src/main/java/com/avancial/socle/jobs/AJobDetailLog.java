/**
 * 
 */
package com.avancial.socle.jobs;

import java.util.Date;

import com.avancial.socle.data.model.databean.LogJobDetailDataBean;
import com.avancial.socle.exceptions.impl.ASocleException;
import com.avancial.socle.resources.constants.SOCLE_logSeverite;

/**
 * @author bruno.legloahec
 *
 */
public abstract class AJobDetailLog extends AJobLog {

   /**
    * 
    */
   private static final long      serialVersionUID = -4633580995101723958L;
   protected LogJobDetailDataBean logJobDetailBean;

   /**
    * Constructeur
    */
   public AJobDetailLog() {
      this.logJobDetailBean = new LogJobDetailDataBean();
      this.logJobDetailBean.setSeveriteLogJobDetail(SOCLE_logSeverite.getCodeSeverite(SOCLE_logSeverite.INFO));

   }

   @Override
   public void executeJob() throws Exception, ASocleException {
      this.logJobDetailBean.setDateLogJobDetail(new Date());

   }

   public void log(String message) {
      this.logJobDetailBean = new LogJobDetailDataBean();
      this.logJobDetailBean.setDateLogJobDetail(new Date());
      this.logJobDetailBean.setLogJob(this.joblogBean);
      this.logJobDetailBean.setMessageLogJobDetail(message);
      this.logJobDetailBean.setSeveriteLogJobDetail(1);
      this.em.getTransaction().begin();
      this.em.merge(this.logJobDetailBean);
      this.em.flush();
      this.em.getTransaction().commit();
   }

}
