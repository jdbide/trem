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
@Table(name = "tremas_motrice_ref_fareprofilecode")
@NamedQueries({ @NamedQuery(name = "MotriceRefFareProfileCode.getAll", query = "SELECT t FROM MotriceRefFareProfileCodeEntity t"),
      @NamedQuery(name = "MotriceRefFareProfileCode.getUnique", query = "SELECT t FROM MotriceRefFareProfileCodeEntity t where t.labelFareProfileCode = :labelFareProfileCode and t.compagnie = :compagnie"),
      @NamedQuery(name = "MotriceRefFareProfileCode.getByCompagnie", query = "SELECT t FROM MotriceRefFareProfileCodeEntity t where t.compagnie = :compagnie"), })
public class MotriceRefFareProfileCodeEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long            idMotriceRefFareProfileCode;
   @Column(length = 3, nullable = false)
   private String          labelFareProfileCode;

   @ManyToOne
   @JoinColumn(name = "idCompagnie")
   @ForeignKey(name = "FK_motrice_ref_fareprofilecode_idCompagnie")
   private CompagnieEntity compagnie;

   public Long getIdMotriceRefFareProfileCode() {
      return this.idMotriceRefFareProfileCode;
   }

   public void setIdMotriceRefFareProfileCode(Long idMotriceRefFareProfileCode) {
      this.idMotriceRefFareProfileCode = idMotriceRefFareProfileCode;
   }

   public String getLabelFareProfileCode() {
      return this.labelFareProfileCode;
   }

   public void setLabelFareProfileCode(String labelFareProfileCode) {
      this.labelFareProfileCode = labelFareProfileCode;
   }

   public CompagnieEntity getCompagnie() {
      return this.compagnie;
   }

   public void setCompagnie(CompagnieEntity compagnie) {
      this.compagnie = compagnie;
   }

}
