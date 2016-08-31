/**
 * 
 */
package com.avancial.sequenceur;

/**
 * Définit le contrat qui décrit un séquenceur (workflow)
 * 
 * @author bruno
 *
 */
public interface ISequenceur {

   public boolean avance(ASequenceurContext context);

   /**
    * @return
    */
   public String getNomEtapeEnCours();

   /**
    * @param etape
    */
   public void AjouteEtape(IEtape etape);

}
