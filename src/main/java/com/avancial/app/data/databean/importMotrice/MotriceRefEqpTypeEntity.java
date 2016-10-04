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

import com.avancial.app.data.databean.CompagnieEntity;

@Entity
@Table(name = "tremas_motrice_ref_eqptype")
@NamedQueries({ @NamedQuery(name = "MotriceRefEqpType.getAll", query = "SELECT t FROM MotriceRefEqpTypeEntity t"),
      @NamedQuery(name = "MotriceRefEqpType.getUnique", query = "SELECT t FROM MotriceRefEqpTypeEntity t where t.labelEqpType = :labelEqpType and t.compagnie = :compagnie"), })
public class MotriceRefEqpTypeEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long            idMotriceRefEqpType;
   @Column(length = 3, nullable = false)
   private String          labelEqpType;

   @ManyToOne
   @JoinColumn(name = "idCompagnie")
   private CompagnieEntity compagnie;

   public Long getIdMotriceRefEqpType() {
      return this.idMotriceRefEqpType;
   }

   public void setIdMotriceRefEqpType(Long idMotriceRefEqpType) {
      this.idMotriceRefEqpType = idMotriceRefEqpType;
   }

   public String getLabelEqpType() {
      return this.labelEqpType;
   }

   public void setLabelEqpType(String labelEqpType) {
      this.labelEqpType = labelEqpType;
   }

   public CompagnieEntity getCompagnie() {
      return this.compagnie;
   }

   public void setCompagnie(CompagnieEntity compagnie) {
      this.compagnie = compagnie;
   }

}
