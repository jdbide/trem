/**
 * 
 */
package com.avancial.sequenceur;

/**
 * @author bruno.legloahec
 *
 */
public class Etape1 extends AEtape implements IEtape {

   /**
    * Constructeur
    * 
    * @param nom
    */
   public Etape1(String nom) {
      super(nom);
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.sequenceur.IEtape#executer(com.avancial.sequenceur.ASequenceurContext)
    */
   @Override
   public void executer(ASequenceurContext contexte) {
      System.out.println("Etape 1 éxécutée");

   }

}
