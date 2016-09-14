package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_motrice_ref_mealtype")
@NamedQuery(name = "MotriceRefMealType.getAll", query = "SELECT t FROM MotriceRefMealTypeEntity t")
public class MotriceRefMealTypeEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long   idMotriceRefMealType;
   @Column(length = 1, nullable = false)
   private String labelMealType;

   /**
    * @return the idMotriceRefCodeDiagramme
    */
   public Long getIdMotriceRefCodeDiagramme() {
      return idMotriceRefMealType;
   }

   /**
    * @param idMotriceRefCodeDiagramme
    *           the idMotriceRefCodeDiagramme to set
    */
   public void setIdMotriceRefCodeDiagramme(Long idMotriceRefCodeDiagramme) {
      this.idMotriceRefMealType = idMotriceRefCodeDiagramme;
   }

   /**
    * @return the labelCodeDiagramme
    */
   public String getLabelCodeDiagramme() {
      return labelMealType;
   }

   /**
    * @param labelCodeDiagramme
    *           the labelCodeDiagramme to set
    */
   public void setLabelCodeDiagramme(String labelCodeDiagramme) {
      this.labelMealType = labelCodeDiagramme;
   }

}
