package com.avancial.app.utilitaire;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DecodageRegime {
   
   private final String prefixAnnee = "201"; 
   private static final SimpleDateFormat FORMAT_DATE_YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
   
   public Date dateDebut(String regime) {
      String sepa = "/";
      int debut = regime.indexOf(sepa) - 5, fin = regime.indexOf(sepa);
      System.out.println(regime + " , " + debut + " , " + fin);
      String dateDebut = regime.substring(debut, fin);
      return decodeDate(dateDebut);
   }

   public Date dateFin(String regime) {
      String sepa = "/";
      int debut = regime.indexOf(sepa) + 1, fin = regime.indexOf(sepa) + 6;
      String dateFin = regime.substring(debut, fin);
      return decodeDate(dateFin);
   }
   
   private Date decodeDate(String date) {
      String formatDate = prefixAnnee + date.substring(4) + "-" + date.substring(2, 4) + "-" + date.substring(0, 2);
      try {
         return FORMAT_DATE_YYYYMMDD.parse(formatDate);
      } catch (ParseException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return null;
   }
   
   public List<Date> listeDates(String regime) {
      return null;
   }
}
