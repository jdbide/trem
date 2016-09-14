/**
 * 
 */
package com.avancial.socle.ihm.menu.constants;

/**
 * @author bruno.legloahec
 *
 */
public enum SOCLE_Page {

   MESSAGE_ADD_SUCCESS("p_message_page_add_succes"),
   MESSAGE_REMOVE_SUCCESS("p_message_page_remove_succes"),
   MESSAGE_UPDATE_SUCCESS("p_message_page_update_succes");

   String constant;

   /**
    * Constructeur
    */
   private SOCLE_Page(String valeur) {
      this.constant = valeur;
   }

   /*
    * (non-Javadoc)
    * 
    * @see java.lang.Enum#toString()
    */
   @Override
   public String toString() {
      return this.constant;
   }
}
