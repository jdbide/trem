package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_motrice_ref_codediagramme")
@NamedQuery(name = "MotriceRefCodeDiagramme.getAll", query = "SELECT t FROM MotriceRefCodeDiagrammeEntity t")
public class MotriceRefCodeDiagrammeEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long   idMotriceRefCodeDiagramme;
   @Column(length = 3, nullable = false)
   private String labelCodeDiagramme;

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

}
