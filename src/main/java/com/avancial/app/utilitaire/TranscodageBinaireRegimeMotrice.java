package com.avancial.app.utilitaire;

import java.text.ParseException;

import com.avancial.socle.utils.Convertisseur;

public class TranscodageBinaireRegimeMotrice implements ITraitementDonnees {

	@Override
	public String execute(String donnee) throws ParseException {
		return Convertisseur.asciiToBin(donnee);
	}

	@Override
	public String execute2(Object object) throws ParseException {
		return Convertisseur.test(object);
	}

}
