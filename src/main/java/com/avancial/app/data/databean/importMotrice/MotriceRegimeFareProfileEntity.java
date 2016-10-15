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
@Table(name = "tremas_motrice_regime_fareprofile")
@NamedQueries({ @NamedQuery(name = "MotriceRegimeFareProfile.getAll", query = "SELECT t FROM MotriceRegimeFareProfileEntity t"),
      @NamedQuery(name = "MotriceRegimeFareProfile.deleteAll", query = "DELETE FROM MotriceRegimeFareProfileEntity"),
      @NamedQuery(name = "MotriceRegimeFareProfile.deleteByRegimes", query = "DELETE FROM MotriceRegimeFareProfileEntity t WHERE t.motriceRegime IN (:regimes)"),
      @NamedQuery(name = "MotriceRegimeFareProfileEntity.getLastId", query = "SELECT MAX( t.idMotriceRegimeFareProfile ) FROM MotriceRegimeFareProfileEntity t") })
public class MotriceRegimeFareProfileEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long                            idMotriceRegimeFareProfile;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idMotriceRefFareProfile")
   @ForeignKey(name = "FK_motrice_regime_fareprofile_idMotriceRefFareProfile")
   private MotriceRefFareProfileCodeEntity motriceRefFareProfileEntity;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idMotriceRegime")
   @ForeignKey(name = "FK_motrice_regime_fareprofile_idMotriceRegime")
   private MotriceRegimeEntity             motriceRegime;

   public Long getIdMotriceRegimeFareProfile() {
      return this.idMotriceRegimeFareProfile;
   }

   public void setIdMotriceRegimeFareProfile(Long idMotriceRegimeFareProfile) {
      this.idMotriceRegimeFareProfile = idMotriceRegimeFareProfile;
   }

   public MotriceRegimeEntity getMotriceRegime() {
      return this.motriceRegime;
   }

   public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
      this.motriceRegime = motriceRegime;
   }

   public MotriceRefFareProfileCodeEntity getMotriceRefFareProfileEntity() {
      return this.motriceRefFareProfileEntity;
   }

   public void setMotriceRefFareProfileEntity(MotriceRefFareProfileCodeEntity motriceRefFareProfileEntity) {
      this.motriceRefFareProfileEntity = motriceRefFareProfileEntity;
   }

}
