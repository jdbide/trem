package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "tremas_motrice_regime_mealtype")
@NamedQueries({ @NamedQuery(name = "MotriceRegimeMealTypeEntity.getAll", query = "SELECT t FROM MotriceRegimeMealTypeEntity t"),
      @NamedQuery(name = "MotriceRegimeMealType.deleteAll", query = "DELETE FROM MotriceRegimeMealTypeEntity"),
      @NamedQuery(name = "MotriceRegimeMealType.deleteByRegimes", query = "DELETE FROM MotriceRegimeMealTypeEntity t WHERE t.motriceRegime IN (:regimes)"),
      @NamedQuery(name = "MotriceRegimeMealTypeEntity.getLastId", query = "SELECT MAX( t.idMotriceRegimeMealType ) FROM MotriceRegimeMealTypeEntity t") })
public class MotriceRegimeMealTypeEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long                     idMotriceRegimeMealType;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idMotriceRefMealType")
   @ForeignKey(name = "FK_motrice_regime_mealtype_idMotriceRefMealType")
   private MotriceRefMealTypeEntity motriceRefMealTypeEntity;

   @Column(length = 8, nullable = false)
   private String                   beginServiceHourRegimeMealType;
   @Column(length = 8, nullable = false)
   private String                   endServiceHourMotriceRegimeMealType;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idMotriceRegime")
   @ForeignKey(name = "FK_motrice_regime_mealtype_idMotriceRegime")
   private MotriceRegimeEntity      motriceRegime;

   /**
    * @return the beginServiceHourRegimeMealType
    */
   public String getBeginServiceHourRegimeMealType() {
      return this.beginServiceHourRegimeMealType;
   }

   /**
    * @param beginServiceHourRegimeMealType
    *           the beginServiceHourRegimeMealType to set
    */
   public void setBeginServiceHourRegimeMealType(String beginServiceHourRegimeMealType) {
      this.beginServiceHourRegimeMealType = beginServiceHourRegimeMealType;
   }

   /**
    * @return the endServiceHourMotriceRegimeMealType
    */
   public String getEndServiceHourMotriceRegimeMealType() {
      return this.endServiceHourMotriceRegimeMealType;
   }

   /**
    * @param endServiceHourMotriceRegimeMealType
    *           the endServiceHourMotriceRegimeMealType to set
    */
   public void setEndServiceHourMotriceRegimeMealType(String endServiceHourMotriceRegimeMealType) {
      this.endServiceHourMotriceRegimeMealType = endServiceHourMotriceRegimeMealType;
   }

   /**
    * @return the motriceRegime
    */
   public MotriceRegimeEntity getMotriceRegime() {
      return this.motriceRegime;
   }

   /**
    * @param motriceRegime
    *           the motriceRegime to set
    */
   public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
      this.motriceRegime = motriceRegime;
   }

   public Long getIdMotriceRegimeMealType() {
      return this.idMotriceRegimeMealType;
   }

   public void setIdMotriceRegimeMealType(Long idMotriceRegimeMealType) {
      this.idMotriceRegimeMealType = idMotriceRegimeMealType;
   }

   public MotriceRefMealTypeEntity getMotriceRefMealTypeEntity() {
      return this.motriceRefMealTypeEntity;
   }

   public void setMotriceRefMealTypeEntity(MotriceRefMealTypeEntity motriceRefMealTypeEntity) {
      this.motriceRefMealTypeEntity = motriceRefMealTypeEntity;
   }

}
