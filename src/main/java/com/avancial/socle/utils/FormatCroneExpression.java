package com.avancial.socle.utils;

import java.util.List;

/**
 * 
 * @author ismael.yahiani
 *  classe Utilitaire, permet de formatter From/To expressions Crone    
 */
public class FormatCroneExpression {

   public static String setJoursSemaine(List<String> listJours) {
      int i = 0 ; 
      StringBuilder sb = new StringBuilder() ; 
      for (String jour : listJours) {
         sb.append(jour) ; 
         if (i < listJours.size()-1 ) sb.append("-") ;
         i++ ;
      } 
     
      return sb.toString() ;
      
   }
}
