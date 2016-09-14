/**
 * 
 */
package com.avancial.socle.logging;

import com.avancial.socle.data.controller.dao.JobPlanifDao;
import com.avancial.socle.data.controller.dao.UserDao;
import com.avancial.socle.data.model.databean.AbstractDataBean;
import com.avancial.socle.data.model.databean.LogJobDataBean;

/**
 * @author bruno
 *
 */
public class LogJobBean2LogJobDataBean implements ILogBean2LogDataBeanConverter {

   private LogJobDataBean dataBean;

   /**
    * 
    */
   public LogJobBean2LogJobDataBean() {
      this.dataBean = new LogJobDataBean();
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.logging.ILogBean2LogDataBeanConverter#convert(com. avancial.socle.logging.ALogBean, data.model.databean.AbstractDataBean)
    */
   @Override
   public AbstractDataBean convert(ALogBean bean) {

      LogJobBean bean1 = (LogJobBean) bean;

      this.dataBean.setDateDebutLogJob(bean1.getDateDebutJob());
      this.dataBean.setDateFinLogJob(bean1.getDateDebutJob());
      this.dataBean.setEtatOkLogJob(bean1.getEtatOkJob());
      JobPlanifDao jobPlanifDao = new JobPlanifDao();
      this.dataBean.setJobPlanif(jobPlanifDao.getJobPlanifById(bean1.getIdJobPlanif()));
      this.dataBean.setLibelleJobLogJob(bean1.getLibelleJob());
      UserDao userDao = new UserDao();
      // this.dataBean.setUser(userDao.getUserByLogin(bean1.getLoginUser()));
      return this.dataBean;
   }

}
