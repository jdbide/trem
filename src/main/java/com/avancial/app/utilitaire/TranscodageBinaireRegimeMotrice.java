package com.avancial.app.utilitaire;

import java.text.ParseException;

import com.avancial.socle.utils.Convertisseur;

public class TranscodageBinaireRegimeMotrice implements ITraitementDonnees {

	@Override
	public String execute(Object object) throws ParseException {
		return Convertisseur.byteToBinary(object);
	}

}
