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
    public String execute(Object donnee) throws ParseException {
        if(app.equals(this.appValid) && donnee instanceof String) {
        	TranscodageRegimeMotrice.setDateDebutService((String) donnee);
    		return (String) donnee;
    	}
    	
    	return null;
    }

    /**
     * @param app
     *            the app to set
     */
    public static void setApp(String app) {
        DateSetterRegimeMotrice.app = app;
    }

}
