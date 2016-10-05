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
@Table(name = "tremas_motrice_ref_mealtype")
@NamedQueries({ @NamedQuery(name = "MotriceRefMealType.getAll", query = "SELECT t FROM MotriceRefMealTypeEntity t"),
      @NamedQuery(name = "MotriceRefMealType.getUnique", query = "SELECT t FROM MotriceRefMealTypeEntity t where t.codeMealType = :codeMealType and t.compagnie = :compagnie"), })
public class MotriceRefMealTypeEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long            idMotriceRefMealType;

   @Column(length = 1, nullable = false)
   private String          codeMealType;

   @Column(length = 50, nullable = true)
   private String          labelMealType;

   @ManyToOne
   @JoinColumn(name = "idCompagnie")
   @ForeignKey(name = "FK_motrice_ref_mealtype_idCompagnie")
   private CompagnieEntity compagnie;

   public Long getIdMotriceRefMealType() {
      return this.idMotriceRefMealType;
   }

   public void setIdMotriceRefMealType(Long idMotriceRefMealType) {
      this.idMotriceRefMealType = idMotriceRefMealType;
   }

   public String getCodeMealType() {
      return this.codeMealType;
   }

   public void setCodeMealType(String codeMealType) {
      this.codeMealType = codeMealType;
   }

   public String getLabelMealType() {
      return this.labelMealType;
   }

   public void setLabelMealType(String labelMealType) {
      this.labelMealType = labelMealType;
   }

   public CompagnieEntity getCompagnie() {
      return this.compagnie;
   }

   public void setCompagnie(CompagnieEntity compagnie) {
      this.compagnie = compagnie;
   }

}
