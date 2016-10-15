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
@Table(name = "tremas_motrice_regime_distribution")
@NamedQueries({ @NamedQuery(name = "MotriceRegimeDistribution.getAll", query = "SELECT t FROM MotriceRegimeDistributionEntity t"),
      @NamedQuery(name = "MotriceRegimeDistribution.deleteAll", query = "DELETE FROM MotriceRegimeDistributionEntity"),
      @NamedQuery(name = "MotriceRegimeDistribution.deleteByRegimes", query = "DELETE FROM MotriceRegimeDistributionEntity t WHERE t.motriceRegime IN (:regimes)"),
      @NamedQuery(name = "MotriceRegimeDistributionEntity.getLastId", query = "SELECT MAX( t.idMotriceRegimeDistribution ) FROM MotriceRegimeDistributionEntity t") })
public class MotriceRegimeDistributionEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long                         idMotriceRegimeDistribution;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idMotriceRefDistribution")
   @ForeignKey(name = "FK_motrice_regime_distribution_idMotriceRefDistribution")
   private MotriceRefDistributionEntity motriceRefDistributionEntity;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idMotriceRegime")
   @ForeignKey(name = "FK_motrice_regime_distribution_idMotriceRegime")
   private MotriceRegimeEntity          motriceRegime;

   public Long getIdMotriceRegimeDistribution() {
      return this.idMotriceRegimeDistribution;
   }

   public void setIdMotriceRegimeDistribution(Long idMotriceRegimeDistribution) {
      this.idMotriceRegimeDistribution = idMotriceRegimeDistribution;
   }

   public MotriceRegimeEntity getMotriceRegime() {
      return this.motriceRegime;
   }

   public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
      this.motriceRegime = motriceRegime;
   }

   public MotriceRefDistributionEntity getMotriceRefDistributionEntity() {
      return this.motriceRefDistributionEntity;
   }

   public void setMotriceRefDistributionEntity(MotriceRefDistributionEntity motriceRefDistributionEntity) {
      this.motriceRefDistributionEntity = motriceRefDistributionEntity;
   }

}
