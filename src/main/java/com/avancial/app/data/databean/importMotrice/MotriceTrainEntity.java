package com.avancial.app.data.databean.importMotrice;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_motrice_train")
@NamedQuery(name = "MotriceTrain.getAll", query = "SELECT t FROM MotriceTrainEntity t")
public class MotriceTrainEntity {
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long    idMotriceTrain;
   private String  trainNumberMotriceTrain;
   private Boolean validForRRMotriceTrain;
   
   @ManyToMany
   @JoinTable(
       name ="tremas_motrice_train2tranche",
       joinColumns=@JoinColumn(name="idMotriceTrain", referencedColumnName="idMotriceTrain"),
       inverseJoinColumns=@JoinColumn(name="idMotriceTranche", referencedColumnName="idMotriceTrain"))
   private List<MotriceTrancheEntity> MotriceTranches;

   /**
    * @return the idMotriceTrain
    */
   public Long getIdMotriceTrain() {
      return idMotriceTrain;
   }

   /**
    * @param idMotriceTrain
    *           the idMotriceTrain to set
    */
   public void setIdMotriceTrain(Long idMotriceTrain) {
      this.idMotriceTrain = idMotriceTrain;
   }

   /**
    * @return the numeroTrain
    */
   public String getNumeroTrain() {
      return trainNumberMotriceTrain;
   }

   /**
    * @param numeroTrain
    *           the numeroTrain to set
    */
   public void setNumeroTrain(String numeroTrain) {
      this.trainNumberMotriceTrain = numeroTrain;
   }

   /**
    * @return the validForRR
    */
   public Boolean getValidForRR() {
      return validForRRMotriceTrain;
   }

   /**
    * @param validForRR
    *           the validForRR to set
    */
   public void setValidForRR(Boolean validForRR) {
      this.validForRRMotriceTrain = validForRR;
   }
}
