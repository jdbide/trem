/**
 * 
 */
package com.avancial.socle.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Cette utilitaire sert à manipuler les données de la session
 * @author hamza.laterem
 *
 */
public class SessionUtils {

   /**
    * Sauvegarde d'une objet dans la session
    * 
    * @param session session en cours 
    * @param attributeName Le nom de l'attribut
    * @param attributeValue La valeur de l'attribut
    */
   public static void saveInSession (HttpServletRequest request, String attributeName, Object attributeValue) {
      /* Récupération de la session depuis la requête */
      HttpSession session = request.getSession();
      
      session.setAttribute(attributeName, attributeValue);
   }
   
   /**
    * Récupération d'un object de la session
    * @param request
    * @param attributeName
    * @return
    */
   public static Object getInSession (HttpServletRequest request, String attributeName) {
      /* Récupération de la session depuis la requête */
      HttpSession session = request.getSession();
      
      return session.getAttribute(attributeName);
   }

}
