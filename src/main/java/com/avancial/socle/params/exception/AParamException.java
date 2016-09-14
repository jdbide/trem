/**
 * 
 */
package com.avancial.socle.params.exception;

/**
 * @author bruno.legloahec
 *
 */
public abstract class AParamException extends Exception {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   @SuppressWarnings("javadoc")
   public static final String MSG_COLLECTION_ITEM_NOT_FOUND = "Le param�tre '%s' de la collection '%s' n'a pas �t� trouv�";
   @SuppressWarnings("javadoc")
   public static final String MSG_COLLECTION_NOT_LOADED = "La collection '%s' n'a pas �t� charg�e";

   /**
    * Constructeur
    */
   public AParamException() {
      // TODO Auto-generated constructor stub
   }

   /**
    * Constructeur
    * 
    * @param message
    */
   public AParamException(String message) {
      super(message);
      // TODO Auto-generated constructor stub
   }

   /**
    * Constructeur
    * 
    * @param cause
    */
   public AParamException(Throwable cause) {
      super(cause);
      // TODO Auto-generated constructor stub
   }

   /**
    * Constructeur
    * 
    * @param message
    * @param cause
    */
   public AParamException(String message, Throwable cause) {
      super(message, cause);
      // TODO Auto-generated constructor stub
   }

   /**
    * Constructeur
    * 
    * @param message
    * @param cause
    * @param enableSuppression
    * @param writableStackTrace
    */
   public AParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
      // TODO Auto-generated constructor stub
   }

}
