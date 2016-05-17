package com.avancial.socle.resources.constants;

/**
 * Niveaux de sévérité d'un log, décrivant son importance/ utilité
 * 
 * @author heloise.guillemaud
 *
 */
public enum SOCLE_logSeverite {
   /**
    * Informations implicitement demandées par l'utilisateur
    */
   INFO(1),
   /**
    * Avertissements sur d'éventuels problèmes
    */
   WARNING(2),
   /**
    * Erreur ayant causé l'annulation du process en cours
    */
   ERROR(3),
   /**
    * Informations d�taillées destinées au développeur
    */
   DEBUG(4);

   private int code;

   private SOCLE_logSeverite(int severite) {
      this.code = severite;
   }

   public static int getCodeSeverite(SOCLE_logSeverite severite) {
      return severite.code;
   }

}
