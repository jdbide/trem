/**
 * 
 */
package com.avancial.socle.ihm.menu.constants;

/**
 * @author bruno.legloahec
 *
 */
public enum SOCLE_Rubrique {

   MESSAGE_ADD_SUCCESS("p_message_rubrique_add_succes"),
   MESSAGE_REMOVE_SUCCESS("p_message_rubrique_remove_succes"),
   MESSAGE_UPDATE_SUCCESS("p_message_rubrique_update_succes");

   String constant;

   /**
    * Constructeur
    */
   private SOCLE_Rubrique(String valeur) {
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
