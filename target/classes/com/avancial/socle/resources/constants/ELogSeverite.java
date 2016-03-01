package com.avancial.socle.resources.constants;

/**
 * Niveaux de s�v�rit� d'un log,
 * d�crivant son importance/ utilit�
 * @author heloise.guillemaud
 *
 */
public enum ELogSeverite {
	/**
	 * Informations implicitement demand�es par l'utilisateur
	 */
	INFO(1),
	/**
	 * Avertissements sur d'�ventuels probl�mes
	 */
	WARNING(2),
	/**
	 * Erreur ayant caus� l'annulation du process en cours
	 */
	ERROR(3),
	/**
	 * Informations d�taill�es destin�es au d�veloppeur
	 */
	DEBUG(4);
	
	private int code;
	
	private ELogSeverite(int severite) {
		this.code = severite;
	}
	
	public static int getCodeSeverite(ELogSeverite severite) {
		return severite.code;
	}
	
}
