package com.avancial.app.utilitaire;

import java.text.ParseException;
/**
 * traitement permetant de set le champs selectionnant la date pour le transcodage
 * @author gabriel.gagnier
 *
 */
public class AppSetterRegimeMotrice implements ITraitementDonnees {

    @Override
    public String execute(String donnee) throws ParseException {
        DateSetterRegimeMotrice.setApp(donnee);
        return donnee;
    }

	@Override
	public String execute2(Object object) throws ParseException {
		// TODO Auto-generated method stub
		return this.execute(object.toString());
	}

}
