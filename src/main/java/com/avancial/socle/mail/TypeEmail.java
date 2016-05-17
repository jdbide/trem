package com.avancial.socle.mail;

/**
 * Roles pour le chargement des données utilisateur.
 * @author sebastien.benede
 *
 */
public enum TypeEmail 
{
	HTML("text/html"),
	TEXTE("text/plain");

	private String nomTechnique = "";

	TypeEmail(String nomTechnique)
	{
		this.nomTechnique = nomTechnique;
	}

	@Override
	public String toString()
	{
		return this.nomTechnique;
	}
}
