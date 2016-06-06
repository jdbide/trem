package com.avancial.socle.authentification.resources.constants;

public enum AUTH_navigation {
   NAVIGATION_LOGIN("/pages/public/login.jsp"),
   NAVIGATION_ACCUEIL("/appClient/index.html"),
   URL_LOGIN("/login");

   private String constant = "";

   private AUTH_navigation(String pconstant) {
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
