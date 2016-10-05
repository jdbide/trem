package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import com.avancial.app.data.databean.CompagnieEntity;

@Entity
@Table(name = "tremas_motrice_ref_distribution")
@NamedQueries({ @NamedQuery(name = "MotriceRefDistribution.getAll", query = "SELECT t FROM MotriceRefDistributionEntity t"),
   @NamedQuery(name = "MotriceRefDistribution.getUnique", query = "SELECT t FROM MotriceRefDistributionEntity t where t.labelDistribution = :labelDistribution and t.compagnie = :compagnie"), })
public class MotriceRefDistributionEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long            idMotriceRefDistribution;
   @Column(length = 1, nullable = false)
   private String          labelDistribution;

   @ManyToOne
   @JoinColumn(name = "idCompagnie")
   @ForeignKey(name = "FK_motrice_ref_distribution_idCompagnie")
   private CompagnieEntity compagnie;

   public Long getIdMotriceRefDistribution() {
      return this.idMotriceRefDistribution;
   }

   public void setIdMotriceRefDistribution(Long idMotriceRefDistribution) {
      this.idMotriceRefDistribution = idMotriceRefDistribution;
   }

   public String getLabelDistribution() {
      return this.labelDistribution;
   }

   public void setLabelDistribution(String labelDistribution) {
      this.labelDistribution = labelDistribution;
   }

   public CompagnieEntity getCompagnie() {
      return this.compagnie;
   }

   public void setCompagnie(CompagnieEntity compagnie) {
      this.compagnie = compagnie;
   }

}
