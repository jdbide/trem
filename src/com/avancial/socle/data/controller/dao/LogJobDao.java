package com.avancial.socle.data.controller.dao;

import java.util.List;

import com.avancial.socle.data.model.databean.LogJobDataBean;

/**
 * Classe DAO pour l'objet LogJob
 * 
 * @author heloise.guillemaud
 *
 */
public class LogJobDao extends AbstractDao {

	@Override
	public List<LogJobDataBean> getAll() {
		return this.getEntityManager().createQuery("FROM LogJobDataBean").getResultList();
	}

	public LogJobDataBean getLogJobById(int idLogJob) {

		String hql = "SELECT logJob FROM LogJobDataBean logJob WHERE logJob.idLogJob = :idLogJob";
		org.hibernate.Query q = this.getSession().createQuery(hql).setParameter("idLogJob", idLogJob);

		LogJobDataBean bean = (LogJobDataBean) q.uniqueResult();
		return bean;
	}

	public LogJobDataBean getLogJobByIdJobPlanif(Long idJobPlanif) {

		String hql = "SELECT logJob FROM LogJobDataBean logJob LEFT JOIN logJob.jobPlanif jobPlanif WHERE jobPlanif.idJobPlanif = :idJobPlanif";
		org.hibernate.Query q = this.getSession().createQuery(hql).setParameter("idJobPlanif", idJobPlanif);

		LogJobDataBean bean = (LogJobDataBean) q.uniqueResult();
		return bean;
	}

}
