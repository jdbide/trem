package com.avancial.socle.resources.constants;

/**
 * Liste des implémentations des stratégies de logging
 * @author heloise.guillemaud
 *
 */
public enum ELogSortie {
	/**
	 * Enregistrement dans une table de la base de données
	 */
	BDD("Base De Données"),
	/**
	 * Affichage dans la console Java
	 */
	CONSOLE("Console Java à l'écran");
	
	private String description;
	
	private ELogSortie(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
}
