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
@Table(name = "tremas_motrice_ref_gare")
@NamedQueries({ @NamedQuery(name = "MotriceRefGare.getAll", query = "SELECT t FROM MotriceRefGareEntity t"),
      @NamedQuery(name = "MotriceRefGare.getUnique", query = "SELECT t FROM MotriceRefGareEntity t where t.codeGareMotriceRefGare = :codeGare and t.compagnie = :compagnie"), })
public class MotriceRefGareEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long            idMotriceRefGare;

   @Column(length = 50, nullable = false)
   private String          codeGareMotriceRefGare;

   @Column(length = 50, nullable = true)
   private String          labelMotriceRefGare;

   @ManyToOne
   @JoinColumn(name = "idCompagnie")
   private CompagnieEntity compagnie;

   public Long getIdMotriceRefGare() {
      return this.idMotriceRefGare;
   }

   public void setIdMotriceRefGare(Long idMotriceRefGare) {
      this.idMotriceRefGare = idMotriceRefGare;
   }

   public String getCodeGareMotriceRefGare() {
      return this.codeGareMotriceRefGare;
   }

   public void setCodeGareMotriceRefGare(String codeGareMotriceRefGare) {
      this.codeGareMotriceRefGare = codeGareMotriceRefGare;
   }

   public String getLabelMotriceRefGare() {
      return this.labelMotriceRefGare;
   }

   public void setLabelMotriceRefGare(String labelMotriceRefGare) {
      this.labelMotriceRefGare = labelMotriceRefGare;
   }

   public CompagnieEntity getCompagnie() {
      return this.compagnie;
   }

   public void setCompagnie(CompagnieEntity compagnie) {
      this.compagnie = compagnie;
   }

}
