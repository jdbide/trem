package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.ArrayList;
import java.util.List;
import com.avancial.socle.utils.ListUtils;

public class Compartiment {

   private String      numeroCompartiment;

   private List<Siege> sieges;

   public Compartiment() {
      this.numeroCompartiment = "";
      this.sieges = new ArrayList<>();
   }

   public Compartiment clone() {
      Compartiment res = new Compartiment();
      List<Siege> resSieges = new ArrayList<>();
      res.setNumeroCompartiment(this.numeroCompartiment);
      if (this.sieges != null) {
         for (Siege siege : this.sieges) {
            resSieges.add(siege.clone());
         }
         res.setSieges(resSieges);
      } else {
         res.setSieges(null);
      }

      return res;
   }

   public Compartiment(String numeroCompartiment, List<Siege> sieges) {
      super();
      this.numeroCompartiment = numeroCompartiment;
      this.sieges = sieges;
   }

   @Override
   public boolean equals(Object obj) {
      Compartiment compartiment = (Compartiment) obj;
      
      if(compartiment.getSieges() != null && this.sieges != null) {
    	  return compartiment.getNumeroCompartiment().equals(this.numeroCompartiment) && ListUtils.compareLists(compartiment.getSieges(), this.sieges);
      }
      else if (compartiment.getSieges() == null && this.sieges == null) {
         return true;
      }
      else
    	  return false;
   }

   public String getNumeroCompartiment() {
      return this.numeroCompartiment;
   }

   public void setNumeroCompartiment(String numeroCompartiment) {
      this.numeroCompartiment = numeroCompartiment;
   }

   public List<Siege> getSieges() {
      return this.sieges;
   }

   public void setSieges(List<Siege> sieges) {
      this.sieges = sieges;
   }

}
