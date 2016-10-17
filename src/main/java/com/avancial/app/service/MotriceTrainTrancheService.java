/**
 * 
 */
package com.avancial.app.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.socle.service.AService;

/**
 * @author hamza.laterem
 *
 */
public class MotriceTrainTrancheService  extends AService implements Serializable {
   /**
   * 
   */
   private static final long serialVersionUID = 1L;

   /**
    * 
    */
   public MotriceTrainTrancheService() {
      // TODO Auto-generated constructor stub
   }

   /**
    * Charge all MotriceTrainTrancheEntity en fonction du jeuDonnees Actif de compagnie/environnement sélectionné.
    * @param idCompagnieEnvironnement
    * @return
    */
   public List<MotriceTrainTrancheEntity> getAllByIdCompagnieEnvironnementWithJeuDonneesActive (Integer idCompagnieEnvironnement) {
      Query requete = this.getEntityManager().createNamedQuery(MotriceTrainTrancheEntity.GET_ALL_BY_ID_COMPAGNIE_ENVIRONNEMENT);
      requete.setParameter("idCompagnieEnvironnement", idCompagnieEnvironnement);

      List<MotriceTrainTrancheEntity> res = null;

      try {
         res = ((ArrayList<MotriceTrainTrancheEntity>) requete.getResultList());
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }

      return res;
   }
   
   /**
    * Charge all MotriceTrainTrancheEntity en fonction du jeuDonnees Actif de compagnie/environnement sélectionné.
    * @param idCompagnieEnvironnement
    * @return
    */
   public List<String> getAllTrainByIdJeuDonnees (Integer idJeuDonnees) {
      Query requete = this.getEntityManager().createNamedQuery(MotriceTrainTrancheEntity.GET_ALL_TRAIN_BY_ID_JEU_DONNEES);
      requete.setParameter("idJeuDonnees", idJeuDonnees);

      List<String> res = null;

      try {
         res = ((ArrayList<String>) requete.getResultList());
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }

      return res;
   }
   
   /**
    * Charge all MotriceTrainTrancheEntity en fonction du jeuDonnees Actif de compagnie/environnement sélectionné.
    * @param idCompagnieEnvironnement
    * @return
    */
   public List<String> getAllTrancheByIdJeuDonnees (Integer idJeuDonnees) {
      Query requete = this.getEntityManager().createNamedQuery(MotriceTrainTrancheEntity.GET_ALL_TRANCHE_BY_ID_JEU_DONNEES);
      requete.setParameter("idJeuDonnees", idJeuDonnees);

      List<String> res = null;

      try {
         res = ((ArrayList<String>) requete.getResultList());
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }

      return res;
   }
}
