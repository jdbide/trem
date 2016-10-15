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
@Table(name = "tremas_motrice_regime_service")
@NamedQueries({ @NamedQuery(name = "MotriceRegimeService.getAll", query = "SELECT t FROM MotriceRegimeServiceEntity t"),
      @NamedQuery(name = "MotriceRegimeService.deleteAll", query = "DELETE FROM MotriceRegimeServiceEntity"),
      @NamedQuery(name = "MotriceRegimeService.deleteByRegimes", query = "DELETE FROM MotriceRegimeServiceEntity t WHERE t.motriceRegime IN (:regimes)"),
      @NamedQuery(name = "MotriceRegimeServiceEntity.getLastId", query = "SELECT MAX( t.idMotriceRegimeService ) FROM MotriceRegimeServiceEntity t") })
public class MotriceRegimeServiceEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long                         idMotriceRegimeService;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idMotriceRefService")
   @ForeignKey(name = "FK_motrice_regime_service_idMotriceRefService")
   private MotriceRefServiceEntity      motriceRefServiceEntity;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idMotriceRefServiceClass")
   @ForeignKey(name = "FK_motrice_regime_service_idMotriceRefServiceClass")
   private MotriceRefServiceClassEntity motriceRefServiceClassEntity;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idOrigineMotriceRefGare")
   @ForeignKey(name = "FK_motrice_regime_service_idOrigineMotriceRefGare")
   private MotriceRefGareEntity         origineMotriceRefGareEntity;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idDestinationMotriceRefGare")
   @ForeignKey(name = "FK_motrice_regime_service_idDestinationMotriceRefGare")
   private MotriceRefGareEntity         destinationMotriceRefGareEntity;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idMotriceRegime")
   @ForeignKey(name = "FK_motrice_regime_service_idMotriceRegime")
   private MotriceRegimeEntity          motriceRegime;

   public Long getIdMotriceRegimeService() {
      return this.idMotriceRegimeService;
   }

   public void setIdMotriceRegimeService(Long idMotriceRegimeService) {
      this.idMotriceRegimeService = idMotriceRegimeService;
   }

   public MotriceRegimeEntity getMotriceRegime() {
      return this.motriceRegime;
   }

   public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
      this.motriceRegime = motriceRegime;
   }

   public MotriceRefServiceEntity getMotriceRefServiceEntity() {
      return this.motriceRefServiceEntity;
   }

   public void setMotriceRefServiceEntity(MotriceRefServiceEntity motriceRefServiceEntity) {
      this.motriceRefServiceEntity = motriceRefServiceEntity;
   }

   public MotriceRefServiceClassEntity getMotriceRefServiceClassEntity() {
      return this.motriceRefServiceClassEntity;
   }

   public void setMotriceRefServiceClassEntity(MotriceRefServiceClassEntity motriceRefServiceClassEntity) {
      this.motriceRefServiceClassEntity = motriceRefServiceClassEntity;
   }

   public MotriceRefGareEntity getOrigineMotriceRefGareEntity() {
      return this.origineMotriceRefGareEntity;
   }

   public void setOrigineMotriceRefGareEntity(MotriceRefGareEntity origineMotriceRefGareEntity) {
      this.origineMotriceRefGareEntity = origineMotriceRefGareEntity;
   }

   public MotriceRefGareEntity getDestinationMotriceRefGareEntity() {
      return this.destinationMotriceRefGareEntity;
   }

   public void setDestinationMotriceRefGareEntity(MotriceRefGareEntity destinationMotriceRefGareEntity) {
      this.destinationMotriceRefGareEntity = destinationMotriceRefGareEntity;
   }

}
