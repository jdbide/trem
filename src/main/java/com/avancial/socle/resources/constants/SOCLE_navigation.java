package com.avancial.socle.resources.constants;

/**
 * Constantes de navigation
 * 
 * @author bruno
 *
 */
public enum SOCLE_navigation {
   /**
   * 
   */
   NAVIGATION_ACCUEIL("/pages/public/accueil?faces-redirect=true"),
   NAVIGATION_ACCUEIL2("/appClient/index.html"),
   NAVIGATION_LOGIN2("/pages/public/login2.xhtml"),
   NAVIGATION_LOGIN3("/pages/public/login3.xhtml"),
   NAVIGATION_LOGIN("/pages/public/login.jsp"),
   NAVIGATION_MDPOUBLIE("/pages/public/mdpoublie?faces-redirect=true"),
   NAVIGATION_ROLE("/pages/private/role?faces-redirect=true"),
   NAVIGATION_USER("/pages/private/user?faces-redirect=true"),
   NAVIGATION_JOB("/pages/private/job?faces-redirect=true"),
   NAVIGATION_ROBOT("/pages/private/robot?faces-redirect=true"),
   NAVIGATION_JOB_PLANIF("/pages/private/jobPlanif?faces-redirect=true"),
   NAVIGATION_JOB_SUPERVISION("/pages/private/jobSupervision?faces-redirect=true"),
   NAVIGATION_JOURNAL_LOG("/pages/private/journalLogs?faces-redirect=true"),
   NAVIGATION_INFO_MOT_DE_PASSE("/pages/public/infoMotDePasse?faces-redirect=true"),
   NAVIGATION_ITEM2_ROLE("/pages/private/item2Role?faces-redirect=true");

   private String constant = "";

   private SOCLE_navigation(String pconstant) {
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
