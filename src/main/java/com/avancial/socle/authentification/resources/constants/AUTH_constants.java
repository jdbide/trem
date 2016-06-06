package com.avancial.socle.authentification.resources.constants;
/**
 * 
 * @author hamza.laterem
 * @version 1.0
 * 
 * Constantes pour le module d'authentification<\br>
 *
 */
public enum AUTH_constants {
   ATT_PATH("pathContext"),
   /* libelle de l'attribut qui a les infos de l'application */
   ATT_INFO_PAGE("infoPage"),   
   ATT_USER("utilisateur"),   
   ATT_FORM("form"),
   ATT_SESSION_USER("sessionUtilisateur"),
   VUE("/WEB-INF/connexion.jsp"),
   
   ERR_AUTH("msgErrAuth");
   
   
   private String constant = "";

   private AUTH_constants(String pconstant) {
      this.constant = pconstant;
   }

   @Override
   public String toString() {
      return this.constant;
   }

}
