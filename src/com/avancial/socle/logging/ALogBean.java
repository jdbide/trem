package com.avancial.socle.logging;

import java.util.Date;

import com.avancial.socle.resources.constants.ELogSeverite;

/**
 * Classe mère pour les objets qui représentent
 * ce qui va être loggé.
 * De base, elle contient une sévérité, un message et une date.
 * @author heloise.guillemaud
 *
 */
public abstract class ALogBean {

	/**
	 * Sévérité du log, sur 4 niveaux
	 *  - Informatif
	 *  - Attention
	 *  - Erreur
	 *  - Mode debug
	 */
	private ELogSeverite severite;
	
	/**
	 * Message lié au log
	 */
	private String message;
	
	/**
	 * Date correspondant au moment
	 * de l'enregistrement
	 */
	private Date date;

	public ELogSeverite getSeverite() {
		return this.severite;
	}

	public void setSeverite(ELogSeverite severite) {
		this.severite = severite;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
