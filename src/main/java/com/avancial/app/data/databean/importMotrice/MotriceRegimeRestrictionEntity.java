package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "tremas_motrice_regime_restriction")
@NamedQueries({ @NamedQuery(name = "MotriceRegimeRestriction.getAll", query = "SELECT t FROM MotriceRegimeRestrictionEntity t"),
      @NamedQuery(name = "MotriceRegimeRestriction.deleteAll", query = "DELETE FROM MotriceRegimeRestrictionEntity"),
      @NamedQuery(name = "MotriceRegimeRestriction.deleteByRegimes", query = "DELETE FROM MotriceRegimeRestrictionEntity t WHERE t.motriceRegime IN (:regimes)"),
      @NamedQuery(name = "MotriceRegimeRestrictionEntity.getLastId", query = "SELECT MAX( t.idMotriceRegimeRestriction ) FROM MotriceRegimeRestrictionEntity t") })
public class MotriceRegimeRestrictionEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long                 idMotriceRegimeRestriction;

   @Column(nullable = false)
   private String               typeMotriceRegimeRestriction;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idOrigineMotriceRefGare")
   @ForeignKey(name = "FK_motrice_regime_restriction_idOrigineMotriceRefGare")
   private MotriceRefGareEntity origineMotriceRefGareEntity;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idDestinationMotriceRefGare")
   @ForeignKey(name = "FK_motrice_regime_restriction_idDestinationMotriceRefGare")
   private MotriceRefGareEntity destinationMotriceRefGareEntity;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idMotriceRegime")
   @ForeignKey(name = "FK_motrice_regime_restriction_idMotriceRegime")
   private MotriceRegimeEntity  motriceRegime;

   /**
    * @return the idMotriceRegimeRestriction
    */
   public Long getIdMotriceRegimeRestriction() {
      return this.idMotriceRegimeRestriction;
   }

   /**
    * @param idMotriceRegimeRestriction
    *           the idMotriceRegimeRestriction to set
    */
   public void setIdMotriceRegimeRestriction(Long idMotriceRegimeRestriction) {
      this.idMotriceRegimeRestriction = idMotriceRegimeRestriction;
   }

   /**
    * @return the typeMotriceRegimeRestriction
    */
   public String getTypeMotriceRegimeRestriction() {
      return this.typeMotriceRegimeRestriction;
   }

   /**
    * @param typeMotriceRegimeRestriction
    *           the typeMotriceRegimeRestriction to set
    */
   public void setTypeMotriceRegimeRestriction(String typeMotriceRegimeRestriction) {
      this.typeMotriceRegimeRestriction = typeMotriceRegimeRestriction;
   }

   /**
    * @return the motriceRegime
    */
   public MotriceRegimeEntity getMotriceRegime() {
      return this.motriceRegime;
   }

   /**
    * @param motriceRegime
    *           the motriceRegime to set
    */
   public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
      this.motriceRegime = motriceRegime;
   }

   public MotriceRefGareEntity getOrigineMotriceRefGareEntity() {
      return this.origineMotriceRefGareEntity;
   }

   public void setOrigineMotriceRefGareEntity(MotriceRefGareEntity origineMotriceRefGareEntity) {
      this.origineMotriceRefGareEntity = origineMotriceRefGareEntity;
   }

   public MotriceRefGareEntity getDestinationMotriceRefGareEntity() {
      return this.destinationMotriceRefGareEntity;
   }

   public void setDestinationMotriceRefGareEntity(MotriceRefGareEntity destinationMotriceRefGareEntity) {
      this.destinationMotriceRefGareEntity = destinationMotriceRefGareEntity;
   }

}
