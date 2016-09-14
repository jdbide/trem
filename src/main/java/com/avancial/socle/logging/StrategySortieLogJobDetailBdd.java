package com.avancial.socle.logging;

import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.data.model.databean.LogJobDetailDataBean;
import com.avancial.socle.exceptions.impl.ASocleException;
import com.avancial.socle.resources.constants.SOCLE_logSeverite;

/**
 * Implémentation du pattern Strategy pour logger un job en détail en base de données.<br/>
 * Ce log étend la stratégie {@link StrategySortieLogJobBdd} donc inutile de rajouter cette dernière dans le logger
 * 
 * 
 * @author heloise.guillemaud
 *
 */
public class StrategySortieLogJobDetailBdd extends StrategySortieLogJobBdd {

   /**
    * Indique si le DataBean que l'on doit "logger" en base doit être mis à jour ({@code true}) ou ajouté ({@code false})
    */
   // private boolean updateBean = false;
   private LogJobDetailDataBean logJobDetailDataBean;

   /**
    * 
    */
   public StrategySortieLogJobDetailBdd() {
      super();
   }

   @Override
   public void log(ALogBean logBean) throws Exception, ASocleException {
      super.log(logBean);

      if (!(logBean instanceof LogJobDetailBean))
         return;

      this.logJobDetailDataBean = new LogJobDetailDataBean();

      AbstractDao daoLog = LogDaoFactory.getLogDao(logBean);
      LogJobDetailBean bean1 = (LogJobDetailBean) logBean;
      // AbstractDataBean dataBeanLog = this.getLogDataBean(logBean);
      this.logJobDetailDataBean.setDateLogJobDetail(bean1.getDate());
      this.logJobDetailDataBean.setSeveriteLogJobDetail(SOCLE_logSeverite.getCodeSeverite(bean1.getSeverite()));
      this.logJobDetailDataBean.setMessageLogJobDetail(bean1.getMessage());
      this.logJobDetailDataBean.setMessageExceptionLogJobDetail(bean1.getMessageException());
      // this.logJobDetailDataBean.setLogJob(AbstractEntityManager.getInstance().getEntityManager().find(LogJobDataBean.class, this.logJobDataBean.getIdLogJob()));
      this.logJobDetailDataBean.setLogJob(this.logJobDataBean);

      // On fait un update à cause de la jointure
      daoLog.update(this.logJobDetailDataBean);

   }

}
