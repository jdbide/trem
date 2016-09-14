package com.avancial.socle.resources.constants;

/**
 * Constantes permettant d'acc�der aux param�tres du socle
 * 
 * @author bruno
 *
 */
public enum SOCLE_params {
   /**
   * 
   */
   PARAMS_SOCLE("socle"),
   PARAMS_SOCLE_VERSION("version"),
   PARAMS_SOCLE_DATE_LIVRAISON("date_livraison"),
   PARAM_DIRECTORIES("directories"),
   PARAM_DIRECTORIES_TMP("SOCLE_tmp"),
   PARAMS_APP("app"),
   PARAMS_APP_VERSION("version"),
   PARAMS_APP_NOM_PROJET("nom_projet"),
   PARAMS_APP_DATE_LIVRAISON("date_livraison");
   private String constant = "";

   private SOCLE_params(String pconstant) {
      this.constant = pconstant;
   }

   public String getValue() {
      return this.constant;
   }
}
