package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.ArrayList;
import java.util.List;

public class Train implements IPlanTransport {

   private List<Tranche> tranches;
   private String        numeroTrain;
   private boolean       validePourRR;

   /**
    * @param tranches
    * @param numeroTrain
    * @param validForRR
    */
   public Train(List<Tranche> tranches, String numeroTrain, boolean validForRR) {
      super();
      this.tranches = tranches;
      this.numeroTrain = numeroTrain;
      this.validePourRR = validForRR;
   }

   public Train() {
      this.tranches = new ArrayList<>();
      this.numeroTrain = "";
      this.validePourRR = false;
   }

   /**
    * Getter trache par numero de tranche
    * @param numeroTranche
    * @return
    */
   public Tranche getTrancheByNumeroTranche(String numeroTranche) {
      for (Tranche tranche : tranches) {
         if (tranche.getNumeroTranche().equals(numeroTranche))
            return tranche;
      }

      return null;
   }

   public Train clone() {
      Train res = new Train();
      List<Tranche> resTranches = new ArrayList<>();
      res.setNumeroTrain(this.numeroTrain);
      res.setValidForRR(this.validePourRR);
      if (this.tranches != null) {
         for (Tranche tranche : this.tranches) {
            resTranches.add(tranche.clone());
         }
         res.setTranches(resTranches);
      } else {
         res.setTranches(null);
      }
      return res;
   }

   @Override
   public boolean equals(Object obj) {
      Train train = (Train) obj;
      return this.numeroTrain.equals(train.getNumeroTrain());
   }

   /**
    * @return the tranches
    */
   public List<Tranche> getTranches() {
      return this.tranches;
   }

   /**
    * @param tranches
    *           the tranches to set
    */
   public void setTranches(List<Tranche> tranches) {
      this.tranches = tranches;
   }

   /**
    * @return the validForRR
    */
   public boolean isValidForRR() {
      return this.validePourRR;
   }

   /**
    * @param validForRR
    *           the validForRR to set
    */
   public void setValidForRR(boolean validForRR) {
      this.validePourRR = validForRR;
   }

   /**
    * @return the numeroTrain
    */
   public String getNumeroTrain() {
      return this.numeroTrain;
   }

   /**
    * @param numeroTrain
    *           the numeroTrain to set
    */
   public void setNumeroTrain(String numeroTrain) {
      this.numeroTrain = numeroTrain;
   }

}
