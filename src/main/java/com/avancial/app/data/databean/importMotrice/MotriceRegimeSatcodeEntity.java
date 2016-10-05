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
@Table(name = "tremas_motrice_regime_satcode")
@NamedQueries({ @NamedQuery(name = "MotriceRegimeSatcode.getAll", query = "SELECT t FROM MotriceRegimeSatcodeEntity t"),
      @NamedQuery(name = "MotriceRegimeSatcode.deleteAll", query = "DELETE FROM MotriceRegimeSatcodeEntity"),
      @NamedQuery(name = "MotriceRegimeSatcode.deleteByRegimes", query = "DELETE FROM MotriceRegimeSatcodeEntity t WHERE t.motriceRegime IN (:regimes)"),
      @NamedQuery(name = "MotriceRegimeSatcodeEntity.getLastId", query = "SELECT MAX( t.idMotriceRegimeSatcode ) FROM MotriceRegimeSatcodeEntity t") })
public class MotriceRegimeSatcodeEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long                    idMotriceRegimeSatcode;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idMotriceRefSatcode")
   @ForeignKey(name = "FK_motrice_regime_satcode_idMotriceRefSatcode")
   private MotriceRefSatcodeEntity motriceRefSatcodeEntity;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idMotriceRegime")
   @ForeignKey(name = "FK_motrice_regime_satcode_idMotriceRegime")
   private MotriceRegimeEntity     motriceRegime;

   public Long getIdMotriceRegimeSatcode() {
      return this.idMotriceRegimeSatcode;
   }

   public void setIdMotriceRegimeSatcode(Long idMotriceRegimeSatcode) {
      this.idMotriceRegimeSatcode = idMotriceRegimeSatcode;
   }

   public MotriceRegimeEntity getMotriceRegime() {
      return this.motriceRegime;
   }

   public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
      this.motriceRegime = motriceRegime;
   }

   public MotriceRefSatcodeEntity getMotriceRefSatcodeEntity() {
      return this.motriceRefSatcodeEntity;
   }

   public void setMotriceRefSatcodeEntity(MotriceRefSatcodeEntity motriceRefSatcodeEntity) {
      this.motriceRefSatcodeEntity = motriceRefSatcodeEntity;
   }

}
