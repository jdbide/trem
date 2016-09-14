package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_motrice_ref_fareprofilecode")
@NamedQuery(name = "MotriceRefFareProfileCode.getAll", query = "SELECT t FROM MotriceRefFareProfileCodeEntity t")
public class MotriceRefFareProfileCodeEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long   idMotriceRefFareProfileCode;
   @Column(length = 3, nullable = false)
   private String labelFareProfileCode;

   /**
    * @return the idMotriceRefCodeDiagramme
    */
   public Long getIdMotriceRefCodeDiagramme() {
      return idMotriceRefFareProfileCode;
   }

   /**
    * @param idMotriceRefCodeDiagramme
    *           the idMotriceRefCodeDiagramme to set
    */
   public void setIdMotriceRefCodeDiagramme(Long idMotriceRefCodeDiagramme) {
      this.idMotriceRefFareProfileCode = idMotriceRefCodeDiagramme;
   }

   /**
    * @return the labelCodeDiagramme
    */
   public String getLabelCodeDiagramme() {
      return labelFareProfileCode;
   }

   /**
    * @param labelCodeDiagramme
    *           the labelCodeDiagramme to set
    */
   public void setLabelCodeDiagramme(String labelCodeDiagramme) {
      this.labelFareProfileCode = labelCodeDiagramme;
   }

}
