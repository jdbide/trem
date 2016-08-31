/**
 * 
 */
package com.avancial.sequenceur;

import java.util.ArrayList;

/**
 * @author bruno
 *
 */
public class ASequenceur extends ArrayList<IEtape> implements ISequenceur {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private IEtape            etapeEnCours;
   private int               compteur         = 0;

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.sequenceur.ISequenceur#avance(com.avancial.sequenceur. ASequenceurContext)
    */
   @Override
   public boolean avance(ASequenceurContext context) {
      if (this.compteur == this.size())
         return false;

      this.etapeEnCours = this.get(compteur++);

      this.etapeEnCours.executer(context);
      if (this.etapeEnCours.isLancerEtapeSuivante())
         this.avance(context);
      return true;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.sequenceur.ISequenceur#getNomEtape()
    */
   @Override
   public String getNomEtapeEnCours() {
      return this.etapeEnCours.getNom();
   }

   /**
    * get value for etapeEnCours
    * 
    * @return the etapeEnCours
    */
   public IEtape getEtapeEnCours() {
      return etapeEnCours;
   }

   /**
    * sets value for etapeEnCours
    * 
    * @param etapeEnCours
    *           the etapeEnCours to set
    */
   public void setEtapeEnCours(IEtape etapeEnCours) {
      this.etapeEnCours = etapeEnCours;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.sequenceur.ISequenceur#AjouteEtape(com.avancial.sequenceur.IEtape)
    */
   @Override
   public void AjouteEtape(IEtape etape) {
      this.add(etape);

   }

}
