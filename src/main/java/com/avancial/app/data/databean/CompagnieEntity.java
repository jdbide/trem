package com.avancial.app.data.databean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_compagnie")
public class CompagnieEntity implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int               idCompagnie;

   private String            libelleCompagnie;

   private String            nomTechniqueCompagnie;

   public int getIdCompagnie() {
      return this.idCompagnie;
   }

   public void setIdCompagnie(int idCompagnie) {
      this.idCompagnie = idCompagnie;
   }

   public String getLibelleCompagnie() {
      return this.libelleCompagnie;
   }

   public void setLibelleCompagnie(String libelleCompagnie) {
      this.libelleCompagnie = libelleCompagnie;
   }

   public String getNomTechniqueCompagnie() {
      return this.nomTechniqueCompagnie;
   }

   public void setNomTechniqueCompagnie(String nomTechniqueCompagnie) {
      this.nomTechniqueCompagnie = nomTechniqueCompagnie;
   }

}
