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
@Table(name = "tremas_motrice_regime_od")
@NamedQueries({ @NamedQuery(name = "MotriceRegimeOD.getAll", query = "SELECT t FROM MotriceRegimeODEntity t"),
      @NamedQuery(name = "MotriceRegimeOD.deleteAll", query = "DELETE FROM MotriceRegimeODEntity"),
      @NamedQuery(name = "MotriceRegimeOD.deleteByRegimes", query = "DELETE FROM MotriceRegimeODEntity t WHERE t.motriceRegime IN (:regimes)"),
      @NamedQuery(name = "MotriceRegimeODEntity.getLastId", query = "SELECT MAX( t.idMotriceRegimeOD ) FROM MotriceRegimeODEntity t"),
      @NamedQuery(name = "MotriceRegimeODEntity.getRefOdByIdTrainTranche", query = "SELECT od.motriceRefODEntity FROM MotriceRegimeODEntity od join od.motriceRegime as regime join regime.motriceTrainTranche as tt where tt.idMotriceTrainTranche = :idMotriceTrainTranche") })
public class MotriceRegimeODEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long                idMotriceRegimeOD;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idMotriceRefOd")
   @ForeignKey(name = "FK_motrice_regime_od_idMotriceRefOd")
   private MotriceRefODEntity  motriceRefODEntity;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idMotriceRegime")
   @ForeignKey(name = "FK_motrice_regime_od_idMotriceRegime")
   private MotriceRegimeEntity motriceRegime;

   /**
    * @return the idMotriceRegimeOD
    */
   public Long getIdMotriceRegimeOD() {
      return this.idMotriceRegimeOD;
   }

   /**
    * @param idMotriceRegimeOD
    *           the idMotriceRegimeOD to set
    */
   public void setIdMotriceRegimeOD(Long idMotriceRegimeOD) {
      this.idMotriceRegimeOD = idMotriceRegimeOD;
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

   public MotriceRefODEntity getMotriceRefODEntity() {
      return this.motriceRefODEntity;
   }

   public void setMotriceRefODEntity(MotriceRefODEntity motriceRefODEntity) {
      this.motriceRefODEntity = motriceRefODEntity;
   }

}
