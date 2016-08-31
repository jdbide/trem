/**
 * 
 */
package com.avancial.sequenceur;

/**
 * @author bruno.legloahec
 *
 */
public class Etape2 extends AEtape implements IEtape {

   /**
    * Constructeur
    * 
    * @param nom
    */
   public Etape2(String nom) {
      super(nom);
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.sequenceur.IEtape#executer(com.avancial.sequenceur.ASequenceurContext)
    */
   @Override
   public void executer(ASequenceurContext contexte) {
      System.out.println("Hi!, step has been executed");

   }

}
