package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_motrice_regime_satcode")
@NamedQuery(name = "MotriceRegimeSatcode.getAll", query = "SELECT t FROM MotriceRegimeSatcodeEntity t")
public class MotriceRegimeSatcodeEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long                idMotriceRegimeSatcode;
   @Column(length = 3, nullable = false)
   private String              satCodeMotriceRegimeSatcode;

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "idMotriceRegime")
   private MotriceRegimeEntity motriceRegime;

   public Long getIdMotriceRegimeSatcode() {
      return idMotriceRegimeSatcode;
   }

   public void setIdMotriceRegimeSatcode(Long idMotriceRegimeSatcode) {
      this.idMotriceRegimeSatcode = idMotriceRegimeSatcode;
   }

   public String getSatCodeMotriceRegimeSatcode() {
      return satCodeMotriceRegimeSatcode;
   }

   public void setSatCodeMotriceRegimeSatcode(String satCodeMotriceRegimeSatcode) {
      this.satCodeMotriceRegimeSatcode = satCodeMotriceRegimeSatcode;
   }

   public MotriceRegimeEntity getMotriceRegime() {
      return motriceRegime;
   }

   public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
      this.motriceRegime = motriceRegime;
   }

}
