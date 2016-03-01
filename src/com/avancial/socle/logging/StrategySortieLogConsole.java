package com.avancial.socle.logging;

import com.avancial.socle.data.controller.dao.LogJobDao;
import com.avancial.socle.data.model.databean.LogJobDataBean;

/**
 * Impl�mentation du pattern Strategy
 * pour logger en console
 * @author heloise.guillemaud
 *
 */
public class StrategySortieLogConsole implements IStrategySortieLog {

	@Override
	public void log(ALogBean logBean) {
		System.out.println(this.printLogBean(logBean));
	}
	
	/**
	 * G�n�re la cha�ne de caract�res d�crivant un LogBean
	 * @param logBean
	 * @return String � afficher sur la console Java
	 */
	private String printLogBean(ALogBean logBean) {
		String res = "";
		String tab = "    ";
		String sautLigne = "\n";
		
		/* LogJob */
		if (logBean.getClass().equals(LogJobBean.class)) {
			LogJobBean logJobBean = (LogJobBean) logBean;
			res += "Log du Job '" + logJobBean.getLibelleJob() + "' ";
			res += "par l'utilisateur de login '" + logJobBean.getLoginUser() + "' ";
			res += tab + "Date de d�but d'ex�cution: " + logJobBean.getDateDebutJob() + sautLigne;
			res += tab + "Date de fin d'ex�cution  : " + logJobBean.getDateFinJob() + sautLigne;
			res += tab + "Etat du job : " + (logJobBean.getEtatOkJobToString()) + sautLigne;
			return res;
		}
		
		/* LogJobDetail */
		if (logBean.getClass().equals(LogJobDetailBean.class)) {
			LogJobDetailBean logJobDetailBean = (LogJobDetailBean) logBean;
			LogJobDao logJobDao = new LogJobDao();
			LogJobDataBean logJobDataBean = logJobDao.getLogJobByIdJobPlanif(logJobDetailBean.getIdJobPlanif());
			res += "[" + logJobDetailBean.getSeverite() + "] ";
			res += "Log D�tail du Job '" + logJobDataBean.getLibelleJob() + "'" + sautLigne;
			res += tab + "Date : " + logJobDetailBean.getDate() + sautLigne;
			res += tab + "Message: '" + logJobDetailBean.getMessage() + "'" + sautLigne;
			if (logJobDetailBean.getMessageException() != null) {
				res += tab + "Exception: '" + logJobDetailBean.getMessageException() + "'" + sautLigne;
			}
		}
		
		return res;
	}

}
