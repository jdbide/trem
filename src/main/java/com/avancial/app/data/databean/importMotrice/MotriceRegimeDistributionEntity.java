package com.avancial.app.data.databean.importMotrice;

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
@Table(name = "tremas_motrice_regime_distribution")
@NamedQuery(name = "MotriceRegimeDistribution.getAll", query = "SELECT t FROM MotriceRegimeDistributionEntity t")
public class MotriceRegimeDistributionEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long                idMotriceRegimeDistribution;
   private String              distribIndexMotriceRegimeDistribution;

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "idMotriceRegime")
   private MotriceRegimeEntity motriceRegime;

   public Long getIdMotriceRegimeDistribution() {
      return idMotriceRegimeDistribution;
   }

   public void setIdMotriceRegimeDistribution(Long idMotriceRegimeDistribution) {
      this.idMotriceRegimeDistribution = idMotriceRegimeDistribution;
   }

   public String getDistribIndexMotriceRegimeDistribution() {
      return distribIndexMotriceRegimeDistribution;
   }

   public void setDistribIndexMotriceRegimeDistribution(String distribIndexMotriceRegimeDistribution) {
      this.distribIndexMotriceRegimeDistribution = distribIndexMotriceRegimeDistribution;
   }

   public MotriceRegimeEntity getMotriceRegime() {
      return motriceRegime;
   }

   public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
      this.motriceRegime = motriceRegime;
   }

}
