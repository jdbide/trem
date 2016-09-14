package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.ArrayList;
import java.util.List;
import com.avancial.socle.utils.ListUtils;

public class Voiture {

   private String             numeroVoiture;

   private List<Compartiment> compartiments;

   public Voiture() {
      this.numeroVoiture = "";
      this.compartiments = new ArrayList<>();
   }

   public Voiture clone() {
      Voiture res = new Voiture();
      List<Compartiment> resCompartiments = new ArrayList<>();
      res.setNumeroVoiture(this.numeroVoiture);
      if (this.compartiments != null) {
         for (Compartiment compartiment : this.compartiments) {
            resCompartiments.add(compartiment.clone());
         }
         res.setCompartiments(resCompartiments);
      } else {
         res.setCompartiments(null);
      }
      return res;
   }

   public Voiture(String numeroVoiture, List<Compartiment> compartiments) {
      super();
      this.numeroVoiture = numeroVoiture;
      this.compartiments = compartiments;
   }

   @Override
   public boolean equals(Object obj) {
      Voiture voiture = (Voiture) obj;
      
      if(this.getCompartiments() != null && voiture.getCompartiments() != null) {
    	  return this.getNumeroVoiture().equals(voiture.getNumeroVoiture()) && ListUtils.compareLists(this.getCompartiments(), voiture.getCompartiments());
      }
      else if(this.getCompartiments() == null && voiture.getCompartiments() == null) {
    	  return true;
      }
      else
    	  return false;
      
   }

   public String getNumeroVoiture() {
      return this.numeroVoiture;
   }

   public void setNumeroVoiture(String numeroVoiture) {
      this.numeroVoiture = numeroVoiture;
   }

   public List<Compartiment> getCompartiments() {
      return this.compartiments;
   }

   public void setCompartiments(List<Compartiment> compartiments) {
      this.compartiments = compartiments;
   }
}
