package com.avancial.socle.authentification.resources.constants;

/**
 * 
 * @author bruno.legloahec
 * @version 1.0
 * 
 *          Constantes pour l'internationalisation de la page login<\br>
 *
 */
public enum AUTH_internationalisation {
   /**
    * Texte d'aide affiché dans le login
    */
   LOGIN_PLACE_HOLDER("p_login_user"),
   /* libelle de l'attribut qui a les infos de l'application */
   /**
    * Texte d'aide affiché dans le mot de passe
    */
   PASSWORD_PLACE_HOLDER("p_login_password"),
   /**
    * Message d'erreur lorsque l'utilisateur a saisi des crédentiels erronnés
    */
   LOGIN_KO("p_login_msg_id_mdp_ko"),
   /**
    * Titre de la fenêtre de connexion
    */
   LOGIN_TITLE("p_login_titre");

   private String constant = "";

   private AUTH_internationalisation(String pconstant) {
      this.constant = pconstant;
   }

   @Override
   public String toString() {
      return this.constant;
   }

}
