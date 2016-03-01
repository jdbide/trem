package com.avancial.socle.logging;

/**
 * Log donnant un d�tail d'ex�cution d'un Job.<br/>
 * <b>ATTENTION</b> en cas de Log en Base De Donn�es,
 * le {@link LogJobBean} li� au Job en cours
 * doit avoir d�j� �t� enregistr� (d�j� logg�).
 * @author heloise.guillemaud
 *
 */
public class LogJobDetailBean extends ALogBean {
	
	private Long idJobPlanif;
	private String messageException;
	
	public LogJobDetailBean(Long idJobPlanif) {
		this.setIdJobPlanif(idJobPlanif);
	}

	public String getMessageException() {
		return this.messageException;
	}

	public void setMessageException(String messageException) {
		this.messageException = messageException;
	}

	public Long getIdJobPlanif() {
		return this.idJobPlanif;
	}

	public void setIdJobPlanif(Long idJobPlanif) {
		this.idJobPlanif = idJobPlanif;
	}
}
