package com.avancial.socle.resources.constants;
/**
 * 
 * @author hamza.laterem
 * @version 1.0
 * 
 * Constantes pour les codes status des requête (Rest API).<\br>
 *
 */
public enum SOCLE_CodeStatus {
   /**
    * Entier représente la réponse : Authentification avec succes
    */
   HTTP_OK(200),
   
   /**
    * Entier représente la réponse : Utilisateur non connecté
    */
   NOT_CONNECTED(402),
   
   /**
    * Entier représente la réponse : Utilisateur non connecté
    */
   ACCESS_DENIED(403),
   
   /**
    * Entier représente la réponse : Erreur
    */
   ERROR(400);
   
   private int status;
   
   private SOCLE_CodeStatus(int status) {
      this.status = status;
   }
   
   public int getStatus() {
      return this.status;
   }
   
   @Override
   public String toString() {
      return String.valueOf(this.status);
   }
}
