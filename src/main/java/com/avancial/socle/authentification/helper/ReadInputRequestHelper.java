/**
 * 
 */
package com.avancial.socle.authentification.helper;

import javax.servlet.http.HttpServletRequest;

/**
 * Helper pour la lecture des données envoyés dans les reqûetes
 * @author hamza.laterem
 *
 */
public class ReadInputRequestHelper {
   /**
    * Méthode utilitaire qui retourne null si un champ est vide, et son contenu sinon.
    * 
    * @param request la requete @see HttpServletRequest
    * @param attName libelle de name d'input
    * @return null si aucune données n'existe dans requete, sinn la valeur en string
    */
   public static String getValueInput(final HttpServletRequest request, final String attName) {
      String value = request.getParameter(attName);
      
      if (value == null || value.trim().length() == 0) {
         return null;
      }
      
      return value; 
   }

}
