package com.avancial.app.service.filtrePlanTransport;

public class CritereOu<T> implements ICritere<T> {

   private ICritere<T> critere1;
   private ICritere<T> critere2;

   /**
    * Opération logique "OU" entre deux critères
    * 
    * @param critere1
    * @param critere2
    */
   public CritereOu(ICritere<T> critere1, ICritere<T> critere2) {
      super();
      this.critere1 = critere1;
      this.critere2 = critere2;
   }

   @Override
   public boolean satisfaitCritere(T object) {
      return this.critere1.satisfaitCritere(object) || this.critere2.satisfaitCritere(object);
   }

}
