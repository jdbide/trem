/**
 * 
 */
package com.avancial.socle.security;

/**
 * @author bruno.legloahec
 *
 */
public class CheckPageAuthSecurityContext {
   private SecurityManagedBean security;
   private String              page;

   /**
    * Constructeur
    */
   public CheckPageAuthSecurityContext(SecurityManagedBean security, String page) {

      this.setSecurity(security);
      this.setPage(page);
   }

   /**
    * @return the security
    */
   public SecurityManagedBean getSecurity() {
      return this.security;
   }

   /**
    * @param security
    *           the security to set
    */
   public void setSecurity(SecurityManagedBean security) {
      this.security = security;
   }

   /**
    * @return the page
    */
   public String getPage() {
      return this.page;
   }

   /**
    * @param page
    *           the page to set
    */
   public void setPage(String page) {
      this.page = page;
   }

}
