package com.avancial.app.utilitaire;

import java.text.ParseException;
import java.sql.Date;

/**
 * set la date permetant le transcodage
 * 
 * @author gabriel.gagnier
 *
 */
public class DateSetterRegimeMotrice implements ITraitementDonnees {

   private static String app      = null;
   final String          appValid = "KHT";

   @Override
   public String execute(Object donnee) throws ParseException {
      if (app.equals(this.appValid) && donnee.getClass().equals(Date.class)) {
         TranscodageRegimeMotrice.setDateDebutService(donnee.toString());
      }
      return donnee.toString();
   }

   /**
    * @param app
    *           the app to set
    */
   public static void setApp(String app) {
      DateSetterRegimeMotrice.app = app;
   }

}
