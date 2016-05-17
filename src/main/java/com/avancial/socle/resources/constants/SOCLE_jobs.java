package com.avancial.socle.resources.constants;

/**
 * Constantes pour les jobs
 * 
 * @author bruno.legloahec
 *
 */
public enum SOCLE_jobs {
   /**
    * Enregistrement dans une table de la base de données
    */
   JOB_CONTEXT_ID_JOB_PLANNIF("ID_JOB_PLANIF"),
   JOB_CONTEXT_LIBELLE_JOB_PLANNIF("LIBELLE_JOB_PLANIF");

   private String description;

   private SOCLE_jobs(String description) {
      this.description = description;
   }

}
