package com.avancial.socle.authentification.resources.constants;

public enum AUTH_error {
   UNKNOWN_ERR_AUTH("Erreur authentification non reconnu, veuillez réessayer"),
   SESSION_EXPIR("Session expirée");
   
   
   private String constant = "";

   private AUTH_error(String pconstant) {
      this.constant = pconstant;
   }

   @Override
   public String toString() {
      return this.constant;
   }
}
