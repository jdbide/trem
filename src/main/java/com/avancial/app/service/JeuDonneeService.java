package com.avancial.app.service;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.Status;
import com.avancial.socle.persistence.EntityManagerFactoryProvider;
import com.avancial.socle.resources.constants.SOCLE_constants;

@RequestScoped
public class JeuDonneeService implements Serializable {
   /**
   * 
   */
   private static final long serialVersionUID = 1L;

   /**
    * Sauvegarde le jeu de données dans la table.
    * 
    * @param jeuDonneeDataBean
    * @return
    */
   public JeuDonneeEntity save(JeuDonneeEntity jeuDonneeDataBean) {
      EntityManager em = EntityManagerFactoryProvider.getInstance().getEntityManagerFactory(SOCLE_constants.PERSISTENCE_UNIT_NAME.toString())
            .createEntityManager();

      try {

         if (em.isOpen()) {
            em.getTransaction().begin();
            em.persist(jeuDonneeDataBean);
            em.flush();
            em.getTransaction().commit();
         }
      } catch (Exception ex) {
         ex.printStackTrace();
         em.getTransaction().rollback();
         throw ex;
      } finally {
         em.close();
      }

      return jeuDonneeDataBean;
   }

   /**
    * Update l'entité jeu de données dans la table associée.
    * 
    * @param jeuDonneeDataBean
    */
   public void update(JeuDonneeEntity jeuDonneeDataBean) {
      EntityManager em = EntityManagerFactoryProvider.getInstance().getEntityManagerFactory(SOCLE_constants.PERSISTENCE_UNIT_NAME.toString())
            .createEntityManager();

      try {
         em.getTransaction().begin();
         em.merge(jeuDonneeDataBean);
         em.flush();
         em.getTransaction().commit();
      } catch (Exception e) {
         e.printStackTrace();
         em.getTransaction().rollback();
         throw e;
      } finally {
         em.close();
      }

   }

   public JeuDonneeEntity initJeuDonnee(CompagnieEnvironnementEntity compagnieEnvironnement) {
      // création de l'entite
      JeuDonneeEntity jeuDonneeDataBean = new JeuDonneeEntity();
      jeuDonneeDataBean.setDateCreateJeuDonnees(new Date());
      jeuDonneeDataBean.setDateLastUpdateJeuDonnees(new Date());
      jeuDonneeDataBean.setDateDebutPeriode(new Date());
      jeuDonneeDataBean.setIdUtilisateurCreateJeuDonnees(-1);
      jeuDonneeDataBean.setIdUtilisateurLastUpdateJeuDonnees(-1);
      jeuDonneeDataBean.setCompagnieEnvironnement(compagnieEnvironnement);

      return jeuDonneeDataBean;
   }

   public void desactiveJeuDonnee(JeuDonneeEntity jeuDonnee) {
      jeuDonnee.setDateLastUpdateJeuDonnees(new Date());
      this.update(jeuDonnee);
   }

   @SuppressWarnings("finally")
   public int deleteById(int idJeuDonnee) {
      EntityManager em = EntityManagerFactoryProvider.getInstance().getEntityManagerFactory(SOCLE_constants.PERSISTENCE_UNIT_NAME.toString())
            .createEntityManager();
      int deleteEntity = 0;
      try {
         String hqlDelete = "delete JeuDonneEntity jd where jd.idJeuDonnee = :idJeudonnee";
         deleteEntity = em.createQuery(hqlDelete).setParameter("idJeudonnee", idJeuDonnee).executeUpdate();
      } catch (Exception e) {
         throw e;
      } finally {
         em.close();
         return deleteEntity;
      }
   }

   /**
    * Charge un jeu de données en fonction du compagnie/environnement et du status.
    * 
    * @param compagnieEnvironnement
    * @param statusJeuDonnees
    * @return jeu de données
    */
   public JeuDonneeEntity getJeuDonneeParIdCompagnieEtStatus(CompagnieEnvironnementEntity compagnieEnvironnement, Status statusJeuDonnees) {
      EntityManager em = EntityManagerFactoryProvider.getInstance().getEntityManagerFactory(SOCLE_constants.PERSISTENCE_UNIT_NAME.toString())
            .createEntityManager();
      TypedQuery<JeuDonneeEntity> query = em.createQuery(
            "SELECT t FROM JeuDonneeEntity t WHERE t.compagnieEnvironnement = :compagnieEnvironnement AND t.statusJeuDonnees = :statusJeuDonnees",
            JeuDonneeEntity.class);
      query.setParameter("compagnieEnvironnement", compagnieEnvironnement);
      query.setParameter("statusJeuDonnees", statusJeuDonnees);

      JeuDonneeEntity jeuDonneeEntity = null;

      try {
         if (!query.getResultList().isEmpty())
            jeuDonneeEntity = query.getSingleResult();
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      } finally {
         em.close();
      }

      return jeuDonneeEntity;
   }

   /**
    * Charge un jeu de données par son id.
    * 
    * @param idJeuDonnees
    * @return
    */
   public JeuDonneeEntity getById(int idJeuDonnees) {
      EntityManager em = EntityManagerFactoryProvider.getInstance().getEntityManagerFactory(SOCLE_constants.PERSISTENCE_UNIT_NAME.toString())
            .createEntityManager();
      JeuDonneeEntity res = null;
      try {
         Query query = em.createNamedQuery("JeuDonneeEntity.getById", JeuDonneeEntity.class);
         query.setParameter("idJeuDonnees", idJeuDonnees);
         res = (JeuDonneeEntity) query.getSingleResult();
      } catch (Exception ex) {
         ex.printStackTrace();
         throw ex;
      } finally {
         em.close();
      }

      return res;
   }
}
