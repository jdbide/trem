package com.avancial.app.data.databean.importMotrice;

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

@Entity
@Table(name = "tremas_motrice_ref_od2gare")
@NamedQueries({ @NamedQuery(name = "MotriceRefOd2gareEntity.getAll", query = "SELECT t FROM MotriceRefOd2gareEntity t"),
      @NamedQuery(name = "MotriceRefOd2gare.getUnique", query = "SELECT t FROM MotriceRefOd2gareEntity t where t.motriceRefODEntity = :refOd and t.motriceRefGareEntity = :refGare"), })
public class MotriceRefOd2gareEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long                 idMotriceRefOd2gare;

   @ManyToOne
   @JoinColumn(name = "idMotriceRefOd")
   @ForeignKey(name = "FK_motrice_ref_od2gare_idMotriceRefOd")
   private MotriceRefODEntity   motriceRefODEntity;

   @ManyToOne
   @JoinColumn(name = "idMotriceRefGare")
   @ForeignKey(name = "FK_motrice_ref_od2gare_idMotriceRefGare")
   private MotriceRefGareEntity motriceRefGareEntity;

   public Long getIdMotriceRefOd2gare() {
      return this.idMotriceRefOd2gare;
   }

   public void setIdMotriceRefOd2gare(Long idMotriceRefOd2gare) {
      this.idMotriceRefOd2gare = idMotriceRefOd2gare;
   }

   public MotriceRefODEntity getMotriceRefODEntity() {
      return this.motriceRefODEntity;
   }

   public void setMotriceRefODEntity(MotriceRefODEntity motriceRefODEntity) {
      this.motriceRefODEntity = motriceRefODEntity;
   }

   public MotriceRefGareEntity getMotriceRefGareEntity() {
      return this.motriceRefGareEntity;
   }

   public void setMotriceRefGareEntity(MotriceRefGareEntity motriceRefGareEntity) {
      this.motriceRefGareEntity = motriceRefGareEntity;
   }

}
