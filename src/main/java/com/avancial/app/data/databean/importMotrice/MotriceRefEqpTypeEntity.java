package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_motrice_ref_eqptype")
@NamedQuery(name = "MotriceRefEqpType.getAll", query = "SELECT t FROM MotriceRefEqpTypeEntity t")
public class MotriceRefEqpTypeEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long   idMotriceRefEqpType;
   @Column(length = 3, nullable = false)
   private String labelEqpType;

   /**
    * @return the idMotriceRefCodeDiagramme
    */
   public Long getIdMotriceRefCodeDiagramme() {
      return idMotriceRefEqpType;
   }

   /**
    * @param idMotriceRefCodeDiagramme
    *           the idMotriceRefCodeDiagramme to set
    */
   public void setIdMotriceRefCodeDiagramme(Long idMotriceRefCodeDiagramme) {
      this.idMotriceRefEqpType = idMotriceRefCodeDiagramme;
   }

   /**
    * @return the labelCodeDiagramme
    */
   public String getLabelCodeDiagramme() {
      return labelEqpType;
   }

   /**
    * @param labelCodeDiagramme
    *           the labelCodeDiagramme to set
    */
   public void setLabelCodeDiagramme(String labelCodeDiagramme) {
      this.labelEqpType = labelCodeDiagramme;
   }

}
