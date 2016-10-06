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
@Table(name = "tremas_motrice_ref_satcode")
@NamedQueries({ @NamedQuery(name = "MotriceRefSatcode.getAll", query = "SELECT t FROM MotriceRefSatcodeEntity t"),
      @NamedQuery(name = "MotriceRefSatcode.getUnique", query = "SELECT t FROM MotriceRefSatcodeEntity t where t.labelSatCode = :labelSatCode and t.compagnie = :compagnie"), })
public class MotriceRefSatcodeEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long            idMotriceRefSatCode;
   @Column(length = 3, nullable = false)
   private String          labelSatCode;

   @ManyToOne
   @JoinColumn(name = "idCompagnie")
   @ForeignKey(name = "FK_motrice_ref_satcode_idCompagnie")
   private CompagnieEntity compagnie;

   public Long getIdMotriceRefSatCode() {
      return this.idMotriceRefSatCode;
   }

   public void setIdMotriceRefSatCode(Long idMotriceRefSatCode) {
      this.idMotriceRefSatCode = idMotriceRefSatCode;
   }

   public String getLabelSatCode() {
      return this.labelSatCode;
   }

   public void setLabelSatCode(String labelSatCode) {
      this.labelSatCode = labelSatCode;
   }

   public CompagnieEntity getCompagnie() {
      return this.compagnie;
   }

   public void setCompagnie(CompagnieEntity compagnie) {
      this.compagnie = compagnie;
   }

}
