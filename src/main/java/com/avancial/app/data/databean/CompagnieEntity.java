package com.avancial.app.data.databean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_compagnie")
@NamedQueries({ @NamedQuery(name = "Compagnie.findAll", query = "SELECT t FROM CompagnieEntity t"),
      @NamedQuery(name = "Compagnie.findByNomTechnique", query = "SELECT t FROM CompagnieEntity t where t.nomTechniqueCompagnie = :nomTechniqueCompagnie"),
      @NamedQuery(name = "Compagnie.findById", query = "SELECT t FROM CompagnieEntity t where t.idCompagnie = :idCompagnie"), })
public class CompagnieEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int    idCompagnie;

   private String libelleCompagnie;

   private String nomTechniqueCompagnie;

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
