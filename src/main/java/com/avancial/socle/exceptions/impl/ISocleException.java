/**
 * 
 */
package com.avancial.socle.exceptions.impl;

import java.io.Serializable;
import java.util.Locale;

/**
 * Décrit le contrat minimum pour créer les exceptions du socle.
 * <ul>
 * Ce contrat permet de gérer 2 niveaux de communication :<br/>
 * <li>Pour le développeur avec l'exception originale</li>
 * <li>Pour le client avec un message fonctionnel clair et localisé</li>
 * </ul>
 * 
 * @author bruno.legloahec
 *
 */
public interface ISocleException extends Serializable {

   public Exception getOriginalException();

   /**
    * Récupère un message traduit dans la langue locale de l'utilisateur
    * 
    * @return
    */
   public String getClientMessage();

   /**
    * Version non JSF
    * 
    * @param locale
    * @return
    */
   public String getClientMessage(Locale locale);

}
