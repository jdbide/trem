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
@Table(name = "tremas_motrice_ref_compositionclass")
@NamedQueries({ @NamedQuery(name = "MotriceRefCompositionClass.getAll", query = "SELECT t FROM MotriceRefCompositionClassEntity t"),
      @NamedQuery(name = "MotriceRefCompositionClass.getUnique", query = "SELECT t FROM MotriceRefCompositionClassEntity t where t.labelCompositionClass = :labelCompositionClass and t.compagnie = :compagnie"),
      @NamedQuery(name = "MotriceRefCompositionClass.getByCompagnie", query = "SELECT t FROM MotriceRefCompositionClassEntity t where t.compagnie = :compagnie"), })
public class MotriceRefCompositionClassEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long            idMotriceRefCompositionClass;
   @Column(length = 1, nullable = false)
   private String          labelCompositionClass;

   @ManyToOne
   @JoinColumn(name = "idCompagnie")
   @ForeignKey(name = "FK_motrice_ref_compositionclass_idCompagnie")
   private CompagnieEntity compagnie;

   public Long getIdMotriceRefCompositionClass() {
      return this.idMotriceRefCompositionClass;
   }

   public void setIdMotriceRefCompositionClass(Long idMotriceRefCompositionClass) {
      this.idMotriceRefCompositionClass = idMotriceRefCompositionClass;
   }

   public String getLabelCompositionClass() {
      return this.labelCompositionClass;
   }

   public void setLabelCompositionClass(String labelCompositionClass) {
      this.labelCompositionClass = labelCompositionClass;
   }

   public CompagnieEntity getCompagnie() {
      return this.compagnie;
   }

   public void setCompagnie(CompagnieEntity compagnie) {
      this.compagnie = compagnie;
   }

}
