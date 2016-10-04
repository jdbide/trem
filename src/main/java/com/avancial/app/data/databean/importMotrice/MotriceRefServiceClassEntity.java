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
@Table(name = "tremas_motrice_ref_serviceclass")
@NamedQueries({ @NamedQuery(name = "MotriceRefServiceClass.getAll", query = "SELECT t FROM MotriceRefServiceClassEntity t"),
      @NamedQuery(name = "MotriceRefServiceClass.getUnique", query = "SELECT t FROM MotriceRefServiceClassEntity t where t.labelServiceClass = :labelServiceClass and t.compagnie = :compagnie"), })
public class MotriceRefServiceClassEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long            idMotriceRefServiceClass;
   @Column(length = 1, nullable = false)
   private String          labelServiceClass;
   @Column(length = 6, nullable = true)
   private String          libelleServiceClass;

   @ManyToOne
   @JoinColumn(name = "idCompagnie")
   private CompagnieEntity compagnie;

   /**
    * @return the idMotriceRefServiceClass
    */
   public Long getIdMotriceRefServiceClass() {
      return this.idMotriceRefServiceClass;
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
      return this.labelServiceClass;
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
      return this.libelleServiceClass;
   }

   /**
    * @param libelleServiceClass
    *           the libelleServiceClass to set
    */
   public void setLibelleServiceClass(String libelleServiceClass) {
      this.libelleServiceClass = libelleServiceClass;
   }

   public CompagnieEntity getCompagnie() {
      return this.compagnie;
   }

   public void setCompagnie(CompagnieEntity compagnie) {
      this.compagnie = compagnie;
   }

}
