package com.avancial.app.data.objetsMetier.PlanTransport.comparaison;

import java.util.Date;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;

public class ComparaisonControlePlanTransport<T extends IPlanTransport> extends AComparaisonPlanTransport<IPlanTransport> {

   private Date dateCircul;

   /**
    * @param dateCircul
    */
   public ComparaisonControlePlanTransport() {
      super();
      this.dateCircul = null;
   }

   @SuppressWarnings("unchecked")
   @Override
   public boolean equals(Object obj) {
      ComparaisonControlePlanTransport<T> comparaison = (ComparaisonControlePlanTransport<T>) obj;
      return this.numeroTrain.equals(comparaison.getNumeroTrain()) && this.numeroTranche.equals(comparaison.getNumeroTranche())
            && this.fieldEquals((T) this.ancienField, (T) comparaison.getAncienField())
            && this.fieldEquals((T) this.nouveauField, (T) comparaison.getNouveauField());
   }

   /**
    * @return the dateCircul
    */
   public Date getDateCircul() {
      return this.dateCircul;
   }

   /**
    * @param dateCircul
    *           the dateCircul to set
    */
   public void setDateCircul(Date dateCircul) {
      this.dateCircul = dateCircul;
   }

}
