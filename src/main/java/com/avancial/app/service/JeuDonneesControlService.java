/**
 * 
 */
package com.avancial.app.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import com.avancial.app.data.databean.JeuDonneesControlEntity;
import com.avancial.socle.service.AService;

/**
 * @author hamza.laterem
 *
 */
public class JeuDonneesControlService extends AService implements Serializable {
   /**
   * 
   */
   private static final long serialVersionUID = 1L;

   /**
    * 
    */
   public JeuDonneesControlService() {
      // TODO Auto-generated constructor stub
   }

   public JeuDonneesControlEntity initJeuDonneeControl() {
      // création de l'entite
      JeuDonneesControlEntity jeuDonneesControlEntity = new JeuDonneesControlEntity();

      jeuDonneesControlEntity.setTitleJeuDonneesControl("");
      jeuDonneesControlEntity.setDateCreateJeuDonneesControl(new Date());
      jeuDonneesControlEntity.setDateLastUpdateJeuDonneesControl(new Date());
      jeuDonneesControlEntity.setPathFileImportJeuDonneesControlTimeTable("");
      jeuDonneesControlEntity.setPathFileImportJeuDonneesControlYield("");
      jeuDonneesControlEntity.setPathFileReportJeuDonneesControl("");
      jeuDonneesControlEntity.setCompagnieEnvironnement(null);

      return jeuDonneesControlEntity;
   }

   /**
    * Sauvegarde le jeu de données control dans la table.
    * 
    * @param jeuDonneeDataBean
    * @return
    */
   public JeuDonneesControlEntity save(JeuDonneesControlEntity jeuDonneesControlEntity) {
      try {

         if (this.getEntityManager().isOpen()) {
            this.getEntityManager().getTransaction().begin();
            this.getEntityManager().persist(jeuDonneesControlEntity);
            this.getEntityManager().flush();
            this.getEntityManager().getTransaction().commit();
         }
      } catch (Exception ex) {
         ex.printStackTrace();
         this.getEntityManager().getTransaction().rollback();
         throw ex;
      }

      return jeuDonneesControlEntity;
   }

   /**
    * Update l'entité jeu de données control dans la table associée.
    * 
    * @param jeuDonneeDataBean
    */
   public void update(JeuDonneesControlEntity jeuDonneesControlEntity) {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().merge(jeuDonneesControlEntity);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         e.printStackTrace();
         this.getEntityManager().getTransaction().rollback();
         throw e;
      }
   }

   /**
    * Suppression d'un jeudonneescontrol par id
    * 
    * @param idJeuDonnee
    * @return
    */
   public int deleteById(int idJeuDonneesControl) {
      int deleteEntity = 0;
      try {
         deleteEntity = this.getEntityManager().createNamedQuery(JeuDonneesControlEntity.QUERY_DELETE_BY_ID).setParameter("idJeuDonneesControl", idJeuDonneesControl).executeUpdate();
      } catch (Exception e) {
         throw e;
      } finally {
         return deleteEntity;
      }
   }

   /**
    * Charge un jeu de données control en fonction du compagnie/environnement.
    * 
    * @param idCompagnieEnvironnement
    * @return List<JeuDonneesControlEntity>
    */
   public List<JeuDonneesControlEntity> getAllJeuDonneesControlParIdCompagnieEnvironnement(int idCompagnieEnvironnement) {
      Query requete = this.getEntityManager().createNamedQuery(JeuDonneesControlEntity.QUERY_GET_BY_COMPAGNIE_ENVIRONNEMENT);
      requete.setParameter("idCompagnieEnvironnement", idCompagnieEnvironnement);

      List<JeuDonneesControlEntity> res = null;

      try {
         res = ((ArrayList<JeuDonneesControlEntity>) requete.getResultList());
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }

      return res;
   }
}
