/**
 * 
 */
package com.avancial.app.data.dto;

import com.avancial.app.data.databean.EStatus;

/**
 * @author hamza.laterem
 *
 */
public class JeuDonneesForControlDto {
   private int    idJeuDonnees;
   private String titleJeuDonnees;
   private EStatus statusJeuDonnees;

   /**
    * 
    */
   public JeuDonneesForControlDto() {
   }

   /**
    * @return the idJeuDonnees
    */
   public int getIdJeuDonnees() {
      return this.idJeuDonnees;
   }

   /**
    * @param idJeuDonnees
    *           the idJeuDonnees to set
    */
   public void setIdJeuDonnees(int idJeuDonnees) {
      this.idJeuDonnees = idJeuDonnees;
   }

   /**
    * @return the titleJeuDonnees
    */
   public String getTitleJeuDonnees() {
      return this.titleJeuDonnees;
   }

   /**
    * @param titleJeuDonnees
    *           the titleJeuDonnees to set
    */
   public void setTitleJeuDonnees(String titleJeuDonnees) {
      this.titleJeuDonnees = titleJeuDonnees;
   }

   /**
    * @return the statusJeuDonnees
    */
   public EStatus getStatusJeuDonnees() {
      return this.statusJeuDonnees;
   }

   /**
    * @param statusJeuDonnees
    *           the statusJeuDonnees to set
    */
   public void setStatusJeuDonnees(EStatus statusJeuDonnees) {
      this.statusJeuDonnees = statusJeuDonnees;
   }

}
