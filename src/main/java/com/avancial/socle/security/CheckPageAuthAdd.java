/**
 * 
 */
package com.avancial.socle.security;

/**
 * Vérifie que l'utilisateur a les droits pour afficher la page d'ajout
 * 
 * @author bruno.legloahec
 *
 */
public class CheckPageAuthAdd extends ACheckPageAuth {

   /**
    * Constructeur
    * 
    * @param prefix
    */
   public CheckPageAuthAdd() {
      super("add_");
   }

   @Override
   public boolean checkPageAuth(CheckPageAuthSecurityContext context) {
      String page = this.getMainPage(context);
      if (context.getSecurity() == null)
         return false;
      else if (context.getSecurity().isAddable(page))
         return true;
      else if (this.next != null)
         return this.next.checkPageAuth(context);
      return false;
   }

}
