package com.avancial.app.data.databean.importMotrice;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_motrice_regime_mealtype")
@NamedQuery(name = "MotriceRegimeMealTypeEntity.getAll", query = "SELECT t FROM MotriceRegimeMealTypeEntity t")
public class MotriceRegimeMealTypeEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long                idMotriceRegimeMealTypeEntity;
   private String              mealTypeMotriceRegimeMealType;
   private Date                beginServiceHourRegimeMealType;
   private Date                endServiceHourRegimeMealType;

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "idMotriceRegime")
   private MotriceRegimeEntity motriceRegime;

   public Long getIdMotriceRegimeMealTypeEntity() {
      return idMotriceRegimeMealTypeEntity;
   }

   public void setIdMotriceRegimeMealTypeEntity(Long idMotriceRegimeMealTypeEntity) {
      this.idMotriceRegimeMealTypeEntity = idMotriceRegimeMealTypeEntity;
   }

   public String getMealTypeMotriceRegimeMealType() {
      return mealTypeMotriceRegimeMealType;
   }

   public void setMealTypeMotriceRegimeMealType(String mealTypeMotriceRegimeMealType) {
      this.mealTypeMotriceRegimeMealType = mealTypeMotriceRegimeMealType;
   }

   public Date getBeginServiceHourRegimeMealType() {
      return beginServiceHourRegimeMealType;
   }

   public void setBeginServiceHourRegimeMealType(Date beginServiceHourRegimeMealType) {
      this.beginServiceHourRegimeMealType = beginServiceHourRegimeMealType;
   }

   public Date getEndServiceHourRegimeMealType() {
      return endServiceHourRegimeMealType;
   }

   public void setEndServiceHourRegimeMealType(Date endServiceHourRegimeMealType) {
      this.endServiceHourRegimeMealType = endServiceHourRegimeMealType;
   }

   public MotriceRegimeEntity getMotriceRegime() {
      return motriceRegime;
   }

   public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
      this.motriceRegime = motriceRegime;
   }

}
