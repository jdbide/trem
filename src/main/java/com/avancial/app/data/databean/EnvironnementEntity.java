package com.avancial.app.data.databean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_environnement")
public class EnvironnementEntity implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int               idEnvironnement;

   private String            libelleEnvironnement;

   private String            nomTechniqueEnvironnement;

   public int getIdEnvironnement() {
      return this.idEnvironnement;
   }

   public void setIdEnvironnement(int idEnvironnement) {
      this.idEnvironnement = idEnvironnement;
   }

   public String getLibelleEnvironnement() {
      return this.libelleEnvironnement;
   }

   public void setLibelleEnvironnement(String libelleEnvironnement) {
      this.libelleEnvironnement = libelleEnvironnement;
   }

   public String getNomTechniqueEnvironnement() {
      return this.nomTechniqueEnvironnement;
   }

   public void setNomTechniqueEnvironnement(String nomTechniqueEnvironnement) {
      this.nomTechniqueEnvironnement = nomTechniqueEnvironnement;
   }

}
