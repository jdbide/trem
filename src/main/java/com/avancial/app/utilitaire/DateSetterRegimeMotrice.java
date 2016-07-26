package com.avancial.app.utilitaire;

import java.text.ParseException;
/**
 * set la date permetant le transcodage
 * @author gabriel.gagnier
 *
 */
public class DateSetterRegimeMotrice implements ITraitementDonnees {

    private static String app = null;

    @Override
    public String execute(String donnee) throws ParseException {
        if (app.equals("KHT"))
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

}
