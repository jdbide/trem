/**
 * 
 */
package com.avancial.socle.ihm.menu.constants;

/**
 * @author HachemBenayed
 *
 */
public enum SOCLE_User {

   MESSAGE_ADD_SUCCESS("p_message_user_add_succes"),
   MESSAGE_REMOVE_SUCCESS("p_message_user_remove_succes"),
   MESSAGE_UPDATE_SUCCESS("p_message_user_update_succes");

   String constant;

   /**
    * Constructeur
    */
   private SOCLE_User(String valeur) {
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
