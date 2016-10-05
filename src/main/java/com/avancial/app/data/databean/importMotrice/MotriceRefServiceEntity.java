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
@Table(name = "tremas_motrice_ref_service")
@NamedQueries({ @NamedQuery(name = "MotriceRefService.getAll", query = "SELECT t FROM MotriceRefServiceEntity t"),
      @NamedQuery(name = "MotriceRefService.getUnique", query = "SELECT t FROM MotriceRefServiceEntity t where t.labelService = :labelService and t.compagnie = :compagnie"), })
public class MotriceRefServiceEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long            idMotriceRefService;
   @Column(length = 2, nullable = false)
   private String          labelService;

   @ManyToOne
   @JoinColumn(name = "idCompagnie")
   @ForeignKey(name = "FK_motrice_ref_service_idCompagnie")
   private CompagnieEntity compagnie;

   public Long getIdMotriceRefService() {
      return this.idMotriceRefService;
   }

   public void setIdMotriceRefService(Long idMotriceRefService) {
      this.idMotriceRefService = idMotriceRefService;
   }

   public String getLabelService() {
      return this.labelService;
   }

   public void setLabelService(String labelService) {
      this.labelService = labelService;
   }

   public CompagnieEntity getCompagnie() {
      return this.compagnie;
   }

   public void setCompagnie(CompagnieEntity compagnie) {
      this.compagnie = compagnie;
   }

}
