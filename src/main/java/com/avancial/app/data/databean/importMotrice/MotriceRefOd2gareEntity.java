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
      @NamedQuery(name = "MotriceRefOd2gare.getUnique", query = "SELECT t FROM MotriceRefOd2gareEntity t where t.motriceRefODEntity = :refOd and t.motriceRefGareEntity = :refGare"),
      @NamedQuery(name = "MotriceRefOd2gare.getByOd", query = "SELECT t FROM MotriceRefOd2gareEntity t join t.motriceRefODEntity od where od.idMotriceRefOd = :idMotriceRefOd"),
})
public class MotriceRefOd2gareEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Integer                 idMotriceRefOd2gare;

   @ManyToOne
   @JoinColumn(name = "idMotriceRefOd")
   @ForeignKey(name = "FK_motrice_ref_od2gare_idMotriceRefOd")
   private MotriceRefODEntity   motriceRefODEntity;

   @ManyToOne
   @JoinColumn(name = "idMotriceRefGare")
   @ForeignKey(name = "FK_motrice_ref_od2gare_idMotriceRefGare")
   private MotriceRefGareEntity motriceRefGareEntity;

   public Integer getIdMotriceRefOd2gare() {
      return this.idMotriceRefOd2gare;
   }

   public void setIdMotriceRefOd2gare(Integer idMotriceRefOd2gare) {
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
