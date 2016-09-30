package com.avancial.app.data.objetsMetier.PlanTransport.comparaison;

import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;

public abstract class AComparaisonPlanTransport<T extends IPlanTransport> implements IComparaisonPlanTransport {

   protected String                           numeroTrain;

   protected String                           numeroTranche;

   private Regime                             regimeTranche;

   protected T                                ancienField;

   protected T                                nouveauField;

   protected EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport;

   public abstract boolean equals(Object obj);

   /**
    * @param ancienField
    * @param nouveauField
    * @param typeComparaisonPlanTransport
    */
   public AComparaisonPlanTransport() {
      super();
      this.ancienField = null;
      this.nouveauField = null;
      this.typeComparaisonPlanTransport = EnumTypeComparaisonPlanTransport.UNCHANGED;
   }

   /**
    * Retourne le résultat de la comparaison de deux attributs de comparaison:
    * <ul>
    * <li>soit les deux sont nuls, et donc égaux</li>
    * <li>soit les deux sont non nuls, alors on vérifie qu'ils sont de la même classe et qu'ils sont égaux</li>
    * <li>soit seulement un des deux est nul, alors ils ne sont pas égaux</li>
    * </ul>
    * 
    * @param field1
    * @param field2
    * @return
    */
   protected boolean fieldEquals(T field1, T field2) {
      return (field1 == null && field2 == null)
            || (!(field1 == null || field2 == null) && field1.getClass().equals(field2.getClass()) && field1.equals(field2));
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

   /**
    * @return the numeroTranche
    */
   public String getNumeroTranche() {
      return this.numeroTranche;
   }

   /**
    * @param numeroTranche
    *           the numeroTranche to set
    */
   public void setNumeroTranche(String numeroTranche) {
      this.numeroTranche = numeroTranche;
   }

   public Regime getRegimeTranche() {
      return this.regimeTranche;
   }

   public void setRegimeTranche(Regime regimeTranche) {
      this.regimeTranche = regimeTranche;
   }

   /**
    * @return the ancienField
    */
   public T getAncienField() {
      return this.ancienField;
   }

   /**
    * @param ancienField
    *           the ancienField to set
    */
   public void setAncienField(T ancienField) {
      this.ancienField = ancienField;
   }

   /**
    * @return the nouveauField
    */
   public T getNouveauField() {
      return this.nouveauField;
   }

   /**
    * @param nouveauField
    *           the nouveauField to set
    */
   public void setNouveauField(T nouveauField) {
      this.nouveauField = nouveauField;
   }

   /**
    * @return the typeComparaisonPlanTransport
    */
   public EnumTypeComparaisonPlanTransport getTypeComparaisonPlanTransport() {
      return this.typeComparaisonPlanTransport;
   }

   /**
    * @param typeComparaisonPlanTransport
    *           the typeComparaisonPlanTransport to set
    */
   public void setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport) {
      this.typeComparaisonPlanTransport = typeComparaisonPlanTransport;
   }

}
