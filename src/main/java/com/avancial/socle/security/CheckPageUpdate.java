/**
 * 
 */
package com.avancial.socle.security;

/**
 * @author bruno.legloahec
 *
 */
public class CheckPageUpdate extends ACheckPageAuth {

   /**
    * Constructeur
    * 
    * @param prefix
    */
   public CheckPageUpdate() {
      super("update_");
   }

   @Override
   public boolean checkPageAuth(CheckPageAuthSecurityContext context) {
      String page = this.getMainPage(context);
      if (context.getSecurity() == null)
         return false;
      else if (context.getSecurity().isEditable(page))
         return true;
      else if (this.next != null)
         return this.next.checkPageAuth(context);
      return false;
   }

}
