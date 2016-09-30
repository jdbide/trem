package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.ArrayList;
import java.util.List;

import com.avancial.socle.utils.ListUtils;

public class PlanTransport implements IPlanTransport {

   private EnumCompagnies compagnie;
   private List<Train>    trains;

   public PlanTransport clone() {
      PlanTransport res = new PlanTransport();
      List<Train> resTrains = new ArrayList<Train>();
      res.setCompagnie(this.compagnie);
      if (this.trains != null) {
         for (Train train : this.trains) {
            resTrains.add(train.clone());
         }
         res.setTrains(resTrains);
      } else {
         res.setTrains(null);
      }
      return res;
   }

   @Override
   public boolean equals(Object obj) {
      PlanTransport plan = (PlanTransport) obj;
      if (plan.getCompagnie().equals(this.compagnie) && ListUtils.compareLists(plan.getTrains(), this.trains)) {
         return true;
      }
      return false;
   }

   /**
    * @param compagnie
    * @param trains
    */
   public PlanTransport(EnumCompagnies compagnie, List<Train> trains) {
      super();
      this.compagnie = compagnie;
      this.trains = trains;
   }

   public PlanTransport() {
      this.compagnie = EnumCompagnies.ES;
      this.trains = new ArrayList<>();
   }

   /**
    * @return the compagnie
    */
   public EnumCompagnies getCompagnie() {
      return this.compagnie;
   }

   /**
    * @param compagnie
    *           the compagnie to set
    */
   public void setCompagnie(EnumCompagnies compagnie) {
      this.compagnie = compagnie;
   }

   /**
    * @return the trains
    */
   public List<Train> getTrains() {
      return this.trains;
   }

   /**
    * @param trains
    *           the trains to set
    */
   public void setTrains(List<Train> trains) {
      this.trains = trains;
   }

   /**
    * Getter train par numero de train
    * 
    * @param numeroTrain
    * @return
    */
   public Train getTrainByNumeroTrain(String numeroTrain) {
      for (Train train : this.trains) {
         if (train.getNumeroTrain().equals(numeroTrain))
            return train;
      }

      return null;
   }

}
