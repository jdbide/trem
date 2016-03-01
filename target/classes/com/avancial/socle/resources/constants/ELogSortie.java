package com.avancial.socle.resources.constants;

/**
 * Liste des impl�mentations des strat�gies de logging
 * @author heloise.guillemaud
 *
 */
public enum ELogSortie {
	/**
	 * Enregistrement dans une table de la base de donn�es
	 */
	BDD("Base De Donn�es"),
	/**
	 * Affichage dans la console Java
	 */
	CONSOLE("Console Java � l'�cran");
	
	private String description;
	
	private ELogSortie(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
}
