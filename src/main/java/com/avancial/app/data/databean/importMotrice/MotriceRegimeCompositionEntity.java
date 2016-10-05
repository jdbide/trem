package com.avancial.app.data.databean.importMotrice;

import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "tremas_motrice_regime_composition")
@NamedQueries({ @NamedQuery(name = "MotriceRegimeComposition.getAll", query = "SELECT t FROM MotriceRegimeCompositionEntity t"),
      @NamedQuery(name = "MotriceRegimeComposition.getByRegimes", query = "SELECT d FROM MotriceRegimeCompositionEntity d WHERE d.motriceRegime IN (:regimes)"),
      @NamedQuery(name = "MotriceRegimeComposition.deleteAll", query = "DELETE FROM MotriceRegimeCompositionEntity"),
      @NamedQuery(name = "MotriceRegimeComposition.deleteByRegimes", query = "DELETE FROM MotriceRegimeCompositionEntity t WHERE t.motriceRegime IN (:regimes)"),
      @NamedQuery(name = "MotriceRegimeCompositionEntity.getLastId", query = "SELECT MAX( t.idMotriceRegimeComposition ) FROM MotriceRegimeCompositionEntity t") })
public class MotriceRegimeCompositionEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long                                      idMotriceRegimeComposition;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idMotriceRefCompositionClass")
   @ForeignKey(name = "FK_motrice_regime_composition_idMotriceRefCompositionClass")
   private MotriceRefCompositionClassEntity          motriceRefCompositionClassEntity;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idMotriceRefCodeDiagramme")
   @ForeignKey(name = "FK_motrice_regime_composition_idMotriceRefCodeDiagramme")
   private MotriceRefCodeDiagrammeEntity             motriceRefCodeDiagrammeEntity;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idMotriceRefRameCode")
   @ForeignKey(name = "FK_motrice_regime_composition_idMotriceRefRameCode")
   private MotriceRefRameCodeEntity                  motriceRefRameCodeEntity;

   @Column(length = 3, nullable = false)
   private String                                    rmCodeMotriceRegimeComposition;

   @OneToMany(fetch = FetchType.EAGER, mappedBy = "motriceRegimeComposition")
   private List<MotriceRegimeCompositionCoachEntity> carsNumbers;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idMotriceRegime")
   @ForeignKey(name = "FK_motrice_regime_composition_idMotriceRegime")
   private MotriceRegimeEntity                       motriceRegime;

   public MotriceRegimeCompositionEntity() {
      super();
   }

   public MotriceRegimeCompositionEntity(Long idMotriceRegimeComposition, MotriceRefCompositionClassEntity motriceRefCompositionClassEntity,
         MotriceRefCodeDiagrammeEntity motriceRefCodeDiagrammeEntity, MotriceRefRameCodeEntity motriceRefRameCodeEntity,
         String rmCodeMotriceRegimeComposition, List<MotriceRegimeCompositionCoachEntity> carsNumbers, MotriceRegimeEntity motriceRegime) {
      super();
      this.idMotriceRegimeComposition = idMotriceRegimeComposition;
      this.motriceRefCompositionClassEntity = motriceRefCompositionClassEntity;
      this.motriceRefCodeDiagrammeEntity = motriceRefCodeDiagrammeEntity;
      this.motriceRefRameCodeEntity = motriceRefRameCodeEntity;
      this.rmCodeMotriceRegimeComposition = rmCodeMotriceRegimeComposition;
      this.carsNumbers = carsNumbers;
      this.motriceRegime = motriceRegime;
   }

   /**
    * @return the idMotriceRegimeComposition
    */
   public Long getIdMotriceRegimeComposition() {
      return this.idMotriceRegimeComposition;
   }

   /**
    * @param idMotriceRegimeComposition
    *           the idMotriceRegimeComposition to set
    */
   public void setIdMotriceRegimeComposition(Long idMotriceRegimeComposition) {
      this.idMotriceRegimeComposition = idMotriceRegimeComposition;
   }

   /**
    * @return the rmCodeMotriceRegimeComposition
    */
   public String getRmCodeMotriceRegimeComposition() {
      return this.rmCodeMotriceRegimeComposition;
   }

   /**
    * @param rmCodeMotriceRegimeComposition
    *           the rmCodeMotriceRegimeComposition to set
    */
   public void setRmCodeMotriceRegimeComposition(String rmCodeMotriceRegimeComposition) {
      this.rmCodeMotriceRegimeComposition = rmCodeMotriceRegimeComposition;
   }

   /**
    * @return the carsNumbers
    */
   public List<MotriceRegimeCompositionCoachEntity> getCarsNumbers() {
      return this.carsNumbers;
   }

   /**
    * @param carsNumbers
    *           the carsNumbers to set
    */
   public void setCarsNumbers(List<MotriceRegimeCompositionCoachEntity> carsNumbers) {
      this.carsNumbers = carsNumbers;
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

   public MotriceRefCompositionClassEntity getMotriceRefCompositionClassEntity() {
      return this.motriceRefCompositionClassEntity;
   }

   public void setMotriceRefCompositionClassEntity(MotriceRefCompositionClassEntity motriceRefCompositionClassEntity) {
      this.motriceRefCompositionClassEntity = motriceRefCompositionClassEntity;
   }

   public MotriceRefCodeDiagrammeEntity getMotriceRefCodeDiagrammeEntity() {
      return this.motriceRefCodeDiagrammeEntity;
   }

   public void setMotriceRefCodeDiagrammeEntity(MotriceRefCodeDiagrammeEntity motriceRefCodeDiagrammeEntity) {
      this.motriceRefCodeDiagrammeEntity = motriceRefCodeDiagrammeEntity;
   }

   public MotriceRefRameCodeEntity getMotriceRefRameCodeEntity() {
      return this.motriceRefRameCodeEntity;
   }

   public void setMotriceRefRameCodeEntity(MotriceRefRameCodeEntity motriceRefRameCodeEntity) {
      this.motriceRefRameCodeEntity = motriceRefRameCodeEntity;
   }

}
