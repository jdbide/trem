package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_motrice_ref_satcode")
@NamedQuery(name = "MotriceRefSatcode.getAll", query = "SELECT t FROM MotriceRefSatcodeEntity t")
public class MotriceRefSatcodeEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long   idMotriceRefSatCode;
   @Column(length = 3, nullable = false)
   private String labelSatCode;

   /**
    * @return the idMotriceRefCodeDiagramme
    */
   public Long getIdMotriceRefCodeDiagramme() {
      return idMotriceRefSatCode;
   }

   /**
    * @param idMotriceRefCodeDiagramme
    *           the idMotriceRefCodeDiagramme to set
    */
   public void setIdMotriceRefCodeDiagramme(Long idMotriceRefCodeDiagramme) {
      this.idMotriceRefSatCode = idMotriceRefCodeDiagramme;
   }

   /**
    * @return the labelCodeDiagramme
    */
   public String getLabelCodeDiagramme() {
      return labelSatCode;
   }

   /**
    * @param labelCodeDiagramme
    *           the labelCodeDiagramme to set
    */
   public void setLabelCodeDiagramme(String labelCodeDiagramme) {
      this.labelSatCode = labelCodeDiagramme;
   }

}
