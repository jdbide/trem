/**
 * 
 */
package com.avancial.sequenceur;

/**
 * @author bruno
 *
 */
public interface IEtape {

   /**
    * @param contexte
    */
   public void executer(ASequenceurContext contexte);

   /**
    * @return
    */
   public boolean isLancerEtapeSuivante();

   /**
    * @param lancerEtapeSuivante
    */
   public void setLancerEtapeSuivante(boolean lancerEtapeSuivante);

   /**
    * @return
    */
   public String getNom();
}
