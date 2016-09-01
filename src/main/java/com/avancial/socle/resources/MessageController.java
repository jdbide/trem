/**
 * 
 */
package com.avancial.socle.resources;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import com.avancial.socle.resources.constants.SOCLE_constants;

/**
 * Gestion de la traduction de l'application
 * 
 * @author guillaume.bouziou
 * 
 */
public final class MessageController {

   /**
    * Constructor
    */
   private MessageController() {
   }

   /**
    * Retourne la traduction du parametre
    * 
    * @param parameter
    * @return
    */
   public static String getTraduction(String parameter) {
      String traduction = "";
      ResourceBundle messages = ResourceBundle.getBundle(SOCLE_constants.BUNDLE_PATH.toString(), FacesContext.getCurrentInstance().getViewRoot().getLocale());
      try {
         traduction = messages.getString(parameter);
      } catch (MissingResourceException e) {
         return MessageFormat.format(messages.getString(SOCLE_constants.MESSAGE_ERR_TRANSLATION_NOT_FOUND.toString()), parameter);
      }
      return traduction;
   }

   /**
    * Utilisé pour les contextes hors JSF
    * 
    * @param parameter
    * @param locale
    * @return
    */
   public static String getTraduction(String parameter, Locale locale) {
      String traduction = "";
      if (locale == null)
         locale = new Locale("fr");
      ResourceBundle messages = ResourceBundle.getBundle(SOCLE_constants.BUNDLE_PATH.toString(), locale);
      try {
         traduction = messages.getString(parameter);
      } catch (MissingResourceException e) {
         return MessageFormat.format(messages.getString(SOCLE_constants.MESSAGE_ERR_TRANSLATION_NOT_FOUND.toString()), parameter);
      }
      return traduction;
   }

   /**
    * Permet de gérer les traduction avec un paramètre
    * 
    * @param parameter
    * @return
    */
   public static String getTraductionWithParams(String parameter, String param) {
      String traduction = "";
      ResourceBundle messages = ResourceBundle.getBundle(SOCLE_constants.BUNDLE_PATH.toString(), FacesContext.getCurrentInstance().getViewRoot().getLocale());
      try {
         traduction = MessageFormat.format(messages.getString(parameter), param);
      } catch (MissingResourceException e) {
         return MessageFormat.format(messages.getString(SOCLE_constants.MESSAGE_ERR_TRANSLATION_NOT_FOUND.toString()), parameter);
      }
      return traduction;
   }

}
