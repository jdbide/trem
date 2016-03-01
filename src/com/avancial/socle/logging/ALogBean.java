package com.avancial.socle.logging;

import java.util.Date;

import com.avancial.socle.resources.constants.ELogSeverite;

/**
 * Classe m�re pour les objets qui repr�sentent
 * ce qui va �tre logg�.
 * De base, elle contient une s�v�rit�, un message et une date.
 * @author heloise.guillemaud
 *
 */
public abstract class ALogBean {

	/**
	 * S�v�rit� du log, sur 4 niveaux
	 *  - Informatif
	 *  - Attention
	 *  - Erreur
	 *  - Mode debug
	 */
	private ELogSeverite severite;
	
	/**
	 * Message li� au log
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
