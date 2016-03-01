package com.avancial.socle.logging;

import java.util.Date;

/**
 * Log se référant à l'exécution d'un Job
 * @author heloise.guillemaud
 *
 */
public class LogJobBean extends ALogBean {
	
	public static final boolean ETAT_SUCCES = true;
	public static final boolean ETAT_ECHEC = false;
	
	private Long idJobPlanif;
	private String libelleJob;
	private Date dateDebutJob;
	private Date dateFinJob;
	private String loginUser;
	private boolean etatOkJob;
	
	public LogJobBean(Long idJobPlanif, String libelleJob, String loginUser) {
		this.etatOkJob = ETAT_ECHEC;
		this.idJobPlanif = idJobPlanif;
		this.libelleJob = libelleJob;
		this.loginUser = loginUser;
	}

	public Long getIdJobPlanif() {
		return this.idJobPlanif;
	}

	public void setIdJobPlanif(Long idJobPlanif) {
		this.idJobPlanif = idJobPlanif;
	}

	public String getLibelleJob() {
		return this.libelleJob;
	}

	public void setLibelleJob(String libelleJob) {
		this.libelleJob = libelleJob;
	}

	public Date getDateDebutJob() {
		return this.dateDebutJob;
	}

	public void setDateDebutJob(Date dateDebutJob) {
		this.dateDebutJob = dateDebutJob;
	}

	public Date getDateFinJob() {
		return this.dateFinJob;
	}

	public void setDateFinJob(Date dateFinJob) {
		this.dateFinJob = dateFinJob;
	}

	public boolean getEtatOkJob() {
		return this.etatOkJob;
	}
	
	public void setEtatOkJob(boolean etatOkJob) {
		this.etatOkJob = etatOkJob;
	}
	
	public String getEtatOkJobToString() {
		if (this.etatOkJob) {
			return "OK";
		}
		
		if (!this.etatOkJob) {
			return "KO";
		}
		
		return "";
	}

	public String getLoginUser() {
		return this.loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

}
