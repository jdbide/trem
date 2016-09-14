package com.avancial.app.utilitaire;

import java.text.ParseException;
/**
 * set la date permetant le transcodage
 * @author gabriel.gagnier
 *
 */
public class DateSetterRegimeMotrice implements ITraitementDonnees {

    private static String app = null;
    final String appValid = "KHT";

    @Override
    public String execute(String donnee) throws ParseException {
        if (app.equals(this.appValid))
            TranscodageRegimeMotrice.setDateDebutService(donnee);
        return donnee;
    }

    /**
     * @param app
     *            the app to set
     */
    public static void setApp(String app) {
        DateSetterRegimeMotrice.app = app;
    }

	@Override
	public String execute2(Object object) throws ParseException {
		// TODO Auto-generated method stub
		return this.execute(object.toString());
	}

}
