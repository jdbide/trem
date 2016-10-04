package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.avancial.app.data.databean.CompagnieEntity;

@Entity
@Table(name = "tremas_motrice_ref_codediagramme")
@NamedQueries({ @NamedQuery(name = "MotriceRefCodeDiagramme.getAll", query = "SELECT t FROM MotriceRefCodeDiagrammeEntity t"),
      @NamedQuery(name = "MotriceRefCodeDiagramme.getUnique", query = "SELECT t FROM MotriceRefCodeDiagrammeEntity t where t.labelCodeDiagramme = :labelCodeDiagramme and t.compagnie = :compagnie"), })
public class MotriceRefCodeDiagrammeEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long            idMotriceRefCodeDiagramme;
   @Column(length = 3, nullable = false)
   private String          labelCodeDiagramme;

   @ManyToOne
   @JoinColumn(name = "idCompagnie")
   private CompagnieEntity compagnie;

   /**
    * @return the idMotriceRefCodeDiagramme
    */
   public Long getIdMotriceRefCodeDiagramme() {
      return this.idMotriceRefCodeDiagramme;
   }

   /**
    * @param idMotriceRefCodeDiagramme
    *           the idMotriceRefCodeDiagramme to set
    */
   public void setIdMotriceRefCodeDiagramme(Long idMotriceRefCodeDiagramme) {
      this.idMotriceRefCodeDiagramme = idMotriceRefCodeDiagramme;
   }

   /**
    * @return the labelCodeDiagramme
    */
   public String getLabelCodeDiagramme() {
      return this.labelCodeDiagramme;
   }

   /**
    * @param labelCodeDiagramme
    *           the labelCodeDiagramme to set
    */
   public void setLabelCodeDiagramme(String labelCodeDiagramme) {
      this.labelCodeDiagramme = labelCodeDiagramme;
   }

   public CompagnieEntity getCompagnie() {
      return this.compagnie;
   }

   public void setCompagnie(CompagnieEntity compagnie) {
      this.compagnie = compagnie;
   }

}
