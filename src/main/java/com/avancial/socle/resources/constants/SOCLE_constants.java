package com.avancial.socle.resources.constants;

/**
 * @author bruno
 *
 */
public enum SOCLE_constants {
   /**
   * 
   */
   BUNDLE_PATH("com.avancial.app.resources.internationalization.Message"),
   SOCLE_PROPERTIES_PATH("/classes/com/avancial/socle/resources/socle.properties"),
   APP_PROPERTIES_PATH("/classes/com/avancial/app/resources/app.properties"),
   MESSAGE_ERR_TRANSLATION_NOT_FOUND("erreur_traduction_non_trouve"),
   MESSAGE_CONNECTION_ACTIVE("message_connection_active"),
   MESSAGE_CONNECTION_INACTIVE("message_connection_inactive"),

   /**
    * ID du statut des messages gÃ©nÃ©raux Ã  l'IHM
    */
   PAGE_ID_MESSAGES("idPageMessages"),
   /**
    * ID du statut des messages pour l'ajout des donnï¿½es
    */
   DIALOG_ADD_MESSAGES("message_add"),
   /**
    * ID du statut des messages pour l'update des donnï¿½es
    */
   DIALOG_UPD_MESSAGES("message_upd"),
   /**
    * ID du statut des messages pour la suppression des donnÃ©es
    */
   DIALOG_DEL_MESSAGES("message_del"),

   EXCEPTION_DUPICATE_ID("exception_duplicate_id"),
   EXCEPTION_UNKNOWN("exception_unknown");

   private String constant = "";

   private SOCLE_constants(String pconstant) {
      this.constant = pconstant;
   }

   @Override
   public String toString() {
      return this.constant;
   }

   /**
    * 
    * @return Le chemin et le nom de la page xhtml
    */
   public String getPagePath() {
      return this.constant.replaceAll("\\?faces-redirect=true", ".xhtml");
   }

}
