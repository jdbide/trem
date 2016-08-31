/**
 * 
 */
package com.avancial.sequenceur;

/**
 * @author bruno.legloahec
 *
 */
public class Etape3 extends AEtape implements IEtape {

   /**
    * Constructeur
    * 
    * @param nom
    */
   public Etape3(String nom) {
      super(nom);
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.sequenceur.IEtape#executer(com.avancial.sequenceur.ASequenceurContext)
    */
   @Override
   public void executer(ASequenceurContext contexte) {
      System.out.println("Congratulation, this task has been executed automatically");

   }

}
