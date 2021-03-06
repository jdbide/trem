package com.avancial.app.data.databean.importMotrice;

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
@Table(name = "tremas_motrice_regime_eqptype")
@NamedQueries({ @NamedQuery(name = "MotriceRegimeEqpType.getAll", query = "SELECT t FROM MotriceRegimeEqpTypeEntity t"),
      @NamedQuery(name = "MotriceRegimeEqpType.deleteAll", query = "DELETE FROM MotriceRegimeEqpTypeEntity"),
      @NamedQuery(name = "MotriceRegimeEqpType.deleteByRegimes", query = "DELETE FROM MotriceRegimeEqpTypeEntity t WHERE t.motriceRegime IN (:regimes)"),
      @NamedQuery(name = "MotriceRegimeEqpTypeEntity.getLastId", query = "SELECT MAX( t.idMotriceRegimeEqpType ) FROM MotriceRegimeEqpTypeEntity t") })
public class MotriceRegimeEqpTypeEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long                    idMotriceRegimeEqpType;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idMotriceRefEqpType")
   @ForeignKey(name = "FK_motrice_regime_eqptype_idMotriceRefEqpType")
   private MotriceRefEqpTypeEntity motriceRefEqpTypeEntity;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idMotriceRegime")
   @ForeignKey(name = "FK_motrice_regime_eqptype_idMotriceRegime")
   private MotriceRegimeEntity     motriceRegime;

   public Long getIdMotriceRegimeEqpType() {
      return this.idMotriceRegimeEqpType;
   }

   public void setIdMotriceRegimeEqpType(Long idMotriceRegimeEqpType) {
      this.idMotriceRegimeEqpType = idMotriceRegimeEqpType;
   }

   public MotriceRegimeEntity getMotriceRegime() {
      return this.motriceRegime;
   }

   public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
      this.motriceRegime = motriceRegime;
   }

   public MotriceRefEqpTypeEntity getMotriceRefEqpTypeEntity() {
      return this.motriceRefEqpTypeEntity;
   }

   public void setMotriceRefEqpTypeEntity(MotriceRefEqpTypeEntity motriceRefEqpTypeEntity) {
      this.motriceRefEqpTypeEntity = motriceRefEqpTypeEntity;
   }

}
