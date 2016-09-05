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

   EXCEPTION_DUPLICATE_ID("exception_duplicate_id"),
   EXCEPTION_COLUMN_NOT_NULL("exception_column_not_null"),
   EXCEPTION_FOREIGN_KEY_CONSTRAINT("exception_foreign_key_constraint"),
   EXCEPTION_UNKNOWN("exception_unknown"),
   CONNECTION_NOT_LOADED("message_collection_not_loaded"),
   PARAM_NOT_FOUND("message_param_not_found"),
   /* Libelle de l'attribut dans le cas d'un appel au webService sans que l'utilisateur sera connecté */
   ERR_AUTH("msgErrAuth"),
   INPUT_USER_NAME("j_username"),
   INPUT_PASSWORD("j_password"),
   ATT_SESSION_USER("sessionUser");

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
