package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_motrice_ref_serviceclass")
@NamedQuery(name = "MotriceRefServiceClass.getAll", query = "SELECT t FROM MotriceRefServiceClassEntity t")
public class MotriceRefServiceClassEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long   idMotriceRefServiceClass;
   @Column(length = 1, nullable = false)
   private String labelServiceClass;
   @Column(length = 6, nullable = false)
   private String libelleServiceClass;

   /**
    * @return the idMotriceRefServiceClass
    */
   public Long getIdMotriceRefServiceClass() {
      return idMotriceRefServiceClass;
   }

   /**
    * @param idMotriceRefServiceClass
    *           the idMotriceRefServiceClass to set
    */
   public void setIdMotriceRefServiceClass(Long idMotriceRefServiceClass) {
      this.idMotriceRefServiceClass = idMotriceRefServiceClass;
   }

   /**
    * @return the labelServiceClass
    */
   public String getLabelServiceClass() {
      return labelServiceClass;
   }

   /**
    * @param labelServiceClass
    *           the labelServiceClass to set
    */
   public void setLabelServiceClass(String labelServiceClass) {
      this.labelServiceClass = labelServiceClass;
   }

   /**
    * @return the libelleServiceClass
    */
   public String getLibelleServiceClass() {
      return libelleServiceClass;
   }

   /**
    * @param libelleServiceClass
    *           the libelleServiceClass to set
    */
   public void setLibelleServiceClass(String libelleServiceClass) {
      this.libelleServiceClass = libelleServiceClass;
   }

}