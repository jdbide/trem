/**
 * 
 */
package com.avancial.sequenceur;

/**
 * @author bruno
 *
 */
public abstract class AEtape implements IEtape {
   private boolean lancerEtapeSuivante = false;
   private String  nom;

   /**
    * Constructeur
    */
   public AEtape(String nom) {
      this.setNom(nom);
   }

   /**
    * get value for lancerEtapeSuivante
    * 
    * @return the lancerEtapeSuivante
    */
   @Override
   public boolean isLancerEtapeSuivante() {
      return lancerEtapeSuivante;
   }

   /**
    * sets value for lancerEtapeSuivante
    * 
    * @param lancerEtapeSuivante
    *           the lancerEtapeSuivante to set
    */
   @Override
   public void setLancerEtapeSuivante(boolean lancerEtapeSuivante) {
      this.lancerEtapeSuivante = lancerEtapeSuivante;
   }

   /**
    * @return the nom
    */
   public String getNom() {
      return nom;
   }

   /**
    * @param nom the nom to set
    */
   public void setNom(String nom) {
      this.nom = nom;
   }

}
