/**
 * 
 */
package com.avancial.socle.security;

/**
 * @author bruno.legloahec
 *
 */
public abstract class ACheckPageAuth implements ICheckPageAuth {
   protected ICheckPageAuth next;
   /**
    * Le préfixe sert à retrouver la page principale à partir des pages d'ajout et d'update
    */
   protected String         prefix;

   /**
    * Constructeur
    */
   public ACheckPageAuth(String prefix) {
      this.prefix = prefix;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.model.managedbean.security.ICheckPageAuth#setNext(com.avancial.socle.model.managedbean.security.ICheckPageAuth)
    */
   @Override
   public void setNext(ICheckPageAuth next) {
      this.next = next;
   }

   protected String getMainPage(CheckPageAuthSecurityContext context) {
      return context.getPage().replaceAll(this.prefix, "");
   }

}
