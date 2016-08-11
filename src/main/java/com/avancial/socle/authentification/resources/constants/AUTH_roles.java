package com.avancial.socle.authentification.resources.constants;

/**
 * 
 * @author hamza.laterem
 * @version 1.0
 * 
 *          Constantes pour le module d'authentification<\br>
 *
 */
public enum AUTH_roles {
   /**
    * Role Admin pour la moe
    */
   ADMIN_MOE("SOCLE_adminMOE"),
   /**
    * Aucun rôle
    */
   NO_ROLE(""),;

   private String constant = "";

   private AUTH_roles(String pconstant) {
      this.constant = pconstant;
   }

   @Override
   public String toString() {
      return this.constant;
   }

}
