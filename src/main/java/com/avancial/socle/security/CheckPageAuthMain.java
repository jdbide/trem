/**
 * 
 */
package com.avancial.socle.security;

/**
 * Vérifie que le rôle est autorisé à afficher la page principale (cad pas la update ni la add)
 * 
 * @author bruno.legloahec
 *
 */
public class CheckPageAuthMain extends ACheckPageAuth {

   /**
    * Constructeur
    * 
    * @param prefix
    */
   public CheckPageAuthMain() {
      super("");
      // TODO Auto-generated constructor stub
   }

   /**
    * Revoie vrai si l'utilisateur connecté a le droit d'afficher la page
    */
   @Override
   public boolean checkPageAuth(CheckPageAuthSecurityContext context) {
      if (context.getSecurity().isRendered(context.getPage()))
         return true;
      else if (null != this.next)
         return this.next.checkPageAuth(context);
      return false;
   }

}
