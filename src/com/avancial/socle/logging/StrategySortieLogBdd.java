package com.avancial.socle.logging;

import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.data.controller.dao.JobPlanifDao;
import com.avancial.socle.data.controller.dao.LogJobDao;
import com.avancial.socle.data.controller.dao.UserDao;
import com.avancial.socle.data.model.databean.AbstractDataBean;
import com.avancial.socle.data.model.databean.LogJobDataBean;
import com.avancial.socle.data.model.databean.LogJobDetailDataBean;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.resources.constants.ELogSeverite;

/**
 * Impl�mentation du pattern Strategy
 * pour logger en base de donn�es
 * @author heloise.guillemaud
 *
 */
public class StrategySortieLogBdd implements IStrategySortieLog {
	
	/**
	 * Indique si le DataBean que l'on doit "logger" en base
	 * doit �tre mis � jour ({@code true}) ou ajout� ({@code false})
	 */
	private boolean updateBean = false;

	@Override
	public void log(ALogBean logBean) throws Exception, ASocleException {
		AbstractDao daoLog = LogDaoFactory.getLogDao(logBean);
		
		AbstractDataBean dataBeanLog = this.getLogDataBean(logBean);

		if (!this.updateBean) {
			daoLog.save(dataBeanLog);
		} else {
			daoLog.update(dataBeanLog);
		}
		
	}
	
	/**
	 * Retourne le DataBean associ� au LogBean,
	 * pour permettre un enregistrement en base
	 * @param logBean
	 * @return DataBean (impl�mente Serializable)
	 * @throws Exception
	 */
	private AbstractDataBean getLogDataBean(ALogBean logBean) throws Exception {
		this.updateBean = false;
		
		/* JobLog */
		if (logBean.getClass().equals(LogJobBean.class)) {
			LogJobBean logJobBean = (LogJobBean) logBean;
			
			/* 
			 * On v�rifie si le log est d�j� en base,
			 * dans le cas o� l'on est � la fin de l'ex�cution
			 * du Job et qu'il faut simplement faire un update
			 */
			LogJobDao logJobDao = new LogJobDao();
			LogJobDataBean res = logJobDao.getLogJobByIdJobPlanif(logJobBean.getIdJobPlanif());
			if (res != null) {
				/* On met � jour les champs de date de fin et d'�tat */
				res.setDateFinLogJob(logJobBean.getDateFinJob());
				res.setEtatOkLogJob(logJobBean.getEtatOkJob());
				this.updateBean = true;
				return res;
			}
			
			/* On fait un nouveau log pour le d�but du Job */
			res = new LogJobDataBean();
			res.setDateDebutLogJob(logJobBean.getDateDebutJob());
			res.setDateFinLogJob(logJobBean.getDateDebutJob());
			res.setEtatOkLogJob(logJobBean.getEtatOkJob());
			JobPlanifDao jobPlanifDao = new JobPlanifDao();
			res.setJobPlanif(jobPlanifDao.getJobPlanifById(logJobBean.getIdJobPlanif()));
			res.setLibelleJob(logJobBean.getLibelleJob());
			UserDao userDao = new UserDao();
			res.setUser(userDao.getUserByLogin(logJobBean.getLoginUser()));
			this.updateBean = false;
			return res;
		}
		
		/* LogJobDetail */
		if (logBean.getClass().equals(LogJobDetailBean.class)) {
			LogJobDetailBean logJobDetailBean = (LogJobDetailBean) logBean;
			LogJobDetailDataBean res = new LogJobDetailDataBean();
			res.setDateLogJobDetail(logJobDetailBean.getDate());
			res.setSeveriteLogJobDetail(ELogSeverite.getCodeSeverite(logJobDetailBean.getSeverite()));
			res.setMessageLogJobDetail(logJobDetailBean.getMessage());
			res.setMessageExceptionLogJobDetail(logJobDetailBean.getMessageException());
			LogJobDao logJobDao = new LogJobDao();
			res.setLogJob(logJobDao.getLogJobByIdJobPlanif(logJobDetailBean.getIdJobPlanif()));
			this.updateBean = false;
			return res;
		}
		
		this.updateBean = false;
		return null;
	}

}
