package com.avancial.socle.data.model.databean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: JobDataBean
 *
 */
@Entity
@Table(name = "socle_job")
public class JobDataBean extends AbstractDataBean {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long              idJob;
   private String            libelleJob;
   private String            nomTechniqueJob;
   private String            classeJob;
   private boolean           actifJob;

   public Long getIdJob() {
      return this.idJob;
   }

   public void setIdJob(Long idJob) {
      this.idJob = idJob;
   }

   public String getLibelleJob() {
      return this.libelleJob;
   }

   public void setLibelleJob(String libelleJob) {
      this.libelleJob = libelleJob;
   }

   public String getNomTechniqueJob() {
      return this.nomTechniqueJob;
   }

   public void setNomTechniqueJob(String nomTechniqueJob) {
      this.nomTechniqueJob = nomTechniqueJob;
   }

   public String getClasseJob() {
      return this.classeJob;
   }

   public void setClasseJob(String classeJob) {
      this.classeJob = classeJob;
   }

   /**
    * @return the actifJob
    */
   public boolean isActifJob() {
      return this.actifJob;
   }

   /**
    * @param actifJob
    *           the actifJob to set
    */
   public void setActifJob(boolean actifJob) {
      this.actifJob = actifJob;
   }

}
