package com.avancial.socle.resources.constants;

/**
 * Liste des implémentations des stratégies de logging
 * 
 * @author heloise.guillemaud
 *
 */
public enum SOCLE_logSortie {
   /**
    * Enregistrement dans une table de la base de données
    */
   JOB_BDD("Base De Données job"),
   JOB_DETAIL_BDD("Base De Données job details"),
   /**
    * Affichage dans la console Java
    */
   CONSOLE("Console Java à l'écran");

   private String description;

   private SOCLE_logSortie(String description) {
      this.description = description;
   }

   public String getDescription() {
      return this.description;
   }

}
