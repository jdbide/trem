package com.avancial.socle.logging;

import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.data.controller.dao.JobPlanifDao;
import com.avancial.socle.data.model.databean.LogJobDataBean;
import com.avancial.socle.exceptions.impl.ASocleException;

/**
 * Implémentation du pattern Strategy pour logger un job en base de données.<br/>
 * Ce log écrit un enregistrement unique dans la table logJob par instance de Logger
 * 
 * 
 * @author heloise.guillemaud
 *
 */
public class StrategySortieLogJobBdd implements IStrategySortieLog {

   protected LogJobDataBean logJobDataBean;

   /**
    * 
    */
   public StrategySortieLogJobBdd() {
      this.logJobDataBean = new LogJobDataBean();

   }

   @Override
   public void log(ALogBean logBean) throws Exception, ASocleException {

      if (!(logBean instanceof LogJobBean))
         return;

      AbstractDao daoLog = LogDaoFactory.getLogDao(logBean);
      LogJobBean bean1 = (LogJobBean) logBean;
      // AbstractDataBean dataBeanLog = this.getLogDataBean(logBean);
      this.logJobDataBean.setDateDebutLogJob(bean1.getDateDebutJob());
      this.logJobDataBean.setDateFinLogJob(bean1.getDateDebutJob());
      this.logJobDataBean.setEtatOkLogJob(bean1.getEtatOkJob());
      JobPlanifDao jobPlanifDao = new JobPlanifDao();
      this.logJobDataBean.setJobPlanif(jobPlanifDao.getJobPlanifById(bean1.getIdJobPlanif()));
      this.logJobDataBean.setLibelleJobLogJob(bean1.getLibelleJob());
      this.logJobDataBean.setLibelleUserLogJob(bean1.getLoginUser());

      // if (!this.updateBean) {
      daoLog.save(this.logJobDataBean);
      // } else {
      // daoLog.update(dataBeanLog);
      // }

   }

}
