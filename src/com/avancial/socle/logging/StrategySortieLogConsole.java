package com.avancial.socle.logging;

import com.avancial.socle.data.controller.dao.LogJobDao;
import com.avancial.socle.data.model.databean.LogJobDataBean;

/**
 * Implémentation du pattern Strategy
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
	 * Génère la chaîne de caractères décrivant un LogBean
	 * @param logBean
	 * @return String à afficher sur la console Java
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
			res += tab + "Date de début d'exécution: " + logJobBean.getDateDebutJob() + sautLigne;
			res += tab + "Date de fin d'exécution  : " + logJobBean.getDateFinJob() + sautLigne;
			res += tab + "Etat du job : " + (logJobBean.getEtatOkJobToString()) + sautLigne;
			return res;
		}
		
		/* LogJobDetail */
		if (logBean.getClass().equals(LogJobDetailBean.class)) {
			LogJobDetailBean logJobDetailBean = (LogJobDetailBean) logBean;
			LogJobDao logJobDao = new LogJobDao();
			LogJobDataBean logJobDataBean = logJobDao.getLogJobByIdJobPlanif(logJobDetailBean.getIdJobPlanif());
			res += "[" + logJobDetailBean.getSeverite() + "] ";
			res += "Log Détail du Job '" + logJobDataBean.getLibelleJob() + "'" + sautLigne;
			res += tab + "Date : " + logJobDetailBean.getDate() + sautLigne;
			res += tab + "Message: '" + logJobDetailBean.getMessage() + "'" + sautLigne;
			if (logJobDetailBean.getMessageException() != null) {
				res += tab + "Exception: '" + logJobDetailBean.getMessageException() + "'" + sautLigne;
			}
		}
		
		return res;
	}

}
