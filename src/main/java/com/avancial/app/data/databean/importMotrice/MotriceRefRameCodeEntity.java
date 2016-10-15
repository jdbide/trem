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
@Table(name = "tremas_motrice_ref_ramecode")
@NamedQueries({ @NamedQuery(name = "MotriceRefRameCode.getAll", query = "SELECT t FROM MotriceRefRameCodeEntity t"),
      @NamedQuery(name = "MotriceRefRameCode.getUnique", query = "SELECT t FROM MotriceRefRameCodeEntity t where t.labelRameCode = :labelRameCode and t.compagnie = :compagnie"),
      @NamedQuery(name = "MotriceRefRameCode.getByCompagnie", query = "SELECT t FROM MotriceRefRameCodeEntity t where t.compagnie = :compagnie"), })
public class MotriceRefRameCodeEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long            idMotriceRefRameCode;
   @Column(length = 6, nullable = false)
   private String          labelRameCode;

   @ManyToOne
   @JoinColumn(name = "idCompagnie")
   @ForeignKey(name = "FK_motrice_ref_ramecode_idCompagnie")
   private CompagnieEntity compagnie;

   public Long getIdMotriceRefRameCode() {
      return this.idMotriceRefRameCode;
   }

   public void setIdMotriceRefRameCode(Long idMotriceRefRameCode) {
      this.idMotriceRefRameCode = idMotriceRefRameCode;
   }

   public String getLabelRameCode() {
      return this.labelRameCode;
   }

   public void setLabelRameCode(String labelRameCode) {
      this.labelRameCode = labelRameCode;
   }

   public CompagnieEntity getCompagnie() {
      return this.compagnie;
   }

   public void setCompagnie(CompagnieEntity compagnie) {
      this.compagnie = compagnie;
   }

}
