/**
 * 
 */
package com.avancial.socle.logging;

import javax.inject.Inject;

import com.avancial.socle.data.controller.dao.UserDao;
import com.avancial.socle.data.model.databean.AbstractDataBean;
import com.avancial.socle.data.model.databean.LogJobDataBean;
import com.avancial.socle.scheduler.service.JobPlanifService;

/**
 * @author bruno
 *
 */
public class LogJobBean2LogJobDataBean implements ILogBean2LogDataBeanConverter {

	@Inject
   private JobPlanifService jobService;
	
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
      this.dataBean.setJobPlanif(this.jobService.getJobPlanifById(bean1.getIdJobPlanif()));
      this.dataBean.setLibelleJobLogJob(bean1.getLibelleJob());
      UserDao userDao = new UserDao();
      // this.dataBean.setUser(userDao.getUserByLogin(bean1.getLoginUser()));
      return this.dataBean;
   }

}
