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
@Table(name = "tremas_motrice_regime_stop")
@NamedQueries({ @NamedQuery(name = "MotriceRegimeStop.getAll", query = "SELECT t FROM MotriceRegimeStopEntity t"),
      @NamedQuery(name = "MotriceRegimeStop.deleteAll", query = "DELETE FROM MotriceRegimeStopEntity"),
      @NamedQuery(name = "MotriceRegimeStop.deleteByRegimes", query = "DELETE FROM MotriceRegimeStopEntity t WHERE t.motriceRegime IN (:regimes)"),
      @NamedQuery(name = "MotriceRegimeStopEntity.getLastId", query = "SELECT MAX( t.idMotriceRegimeStop ) FROM MotriceRegimeStopEntity t") })
public class MotriceRegimeStopEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long                 idMotriceRegimeStop;

   @Column(length = 4, nullable = false)
   private String               arrivalHourMotriceRegimeStop;
   @Column(length = 4, nullable = false)
   private String               departureHourMotriceRegimeStop;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idMotriceRefGare")
   @ForeignKey(name = "FK_motrice_regime_stop_idMotriceRefGare")
   private MotriceRefGareEntity motriceRefGareEntity;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idMotriceRegime")
   @ForeignKey(name = "FK_motrice_regime_stop_idMotriceRegime")
   private MotriceRegimeEntity  motriceRegime;

   public Long getIdMotriceRegimeStop() {
      return this.idMotriceRegimeStop;
   }

   public void setIdMotriceRegimeStop(Long idMotriceRegimeStop) {
      this.idMotriceRegimeStop = idMotriceRegimeStop;
   }

   public String getArrivalHourMotriceRegimeStop() {
      return this.arrivalHourMotriceRegimeStop;
   }

   public void setArrivalHourMotriceRegimeStop(String arrivalHour) {
      this.arrivalHourMotriceRegimeStop = arrivalHour;
   }

   public String getDepartureHourMotriceRegimeStop() {
      return this.departureHourMotriceRegimeStop;
   }

   public void setDepartureHourMotriceRegimeStop(String departureHour) {
      this.departureHourMotriceRegimeStop = departureHour;
   }

   public MotriceRegimeEntity getMotriceRegimeMotriceRegimeStop() {
      return this.motriceRegime;
   }

   public void setMotriceRegimeMotriceRegimeStop(MotriceRegimeEntity motriceRegime) {
      this.motriceRegime = motriceRegime;
   }

   public MotriceRefGareEntity getMotriceRefGareEntity() {
      return this.motriceRefGareEntity;
   }

   public void setMotriceRefGareEntity(MotriceRefGareEntity motriceRefGareEntity) {
      this.motriceRefGareEntity = motriceRefGareEntity;
   }

   public MotriceRegimeEntity getMotriceRegime() {
      return this.motriceRegime;
   }

   public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
      this.motriceRegime = motriceRegime;
   }

}
