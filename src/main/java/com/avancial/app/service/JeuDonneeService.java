package com.avancial.app.service;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
@SessionScoped
public class JeuDonneeService implements Serializable {
  /**
    * 
    */
   private static final long serialVersionUID = 1L;
   
   @Inject
   @Socle_PUSocle
   EntityManager       em;

   /**
    * Sauvegarde le jeu de données dans la table.
    * @param jeuDonneeDataBean
    * @return
    */
   public JeuDonneeEntity save(JeuDonneeEntity jeuDonneeDataBean) {
      try {         
         this.em.getTransaction().begin();
         this.em.persist(jeuDonneeDataBean);
         this.em.flush();
         this.em.getTransaction().commit();
      } catch (Exception ex) {
         ex.printStackTrace();
         this.em.getTransaction().rollback();
      }
            
      return jeuDonneeDataBean;
   }

   /**
    * Update l'entité jeu de données dans la table associée.
    * @param jeuDonneeDataBean
    */
   public void update(JeuDonneeEntity jeuDonneeDataBean) {
      try {
         this.em.getTransaction().begin();  
         this.em.merge(jeuDonneeDataBean);   
         this.em.flush();
         this.em.getTransaction().commit();
      } catch (Exception e) {
         e.printStackTrace();      
         this.em.getTransaction().rollback();
      }
      
   }

   public JeuDonneeEntity initJeuDonnee(CompagnieEnvironnementEntity compagnieEnvironnement) {
   // création de l'entite
      JeuDonneeEntity jeuDonneeDataBean = new JeuDonneeEntity();
      jeuDonneeDataBean.setDateCreateJeuDonnees(new Date());
      jeuDonneeDataBean.setDateLastUpdateJeuDonnees(new Date());
      jeuDonneeDataBean.setIdUtilisateurCreateJeuDonnees(-1);
      jeuDonneeDataBean.setIdUtilisateurLastUpdateJeuDonnees(-1);
      jeuDonneeDataBean.setActifJeuDonnees(true);
      jeuDonneeDataBean.setCommentaireJeuDonnees("");
      jeuDonneeDataBean.setCompagnieEnvironnement(compagnieEnvironnement);
      
      return jeuDonneeDataBean;
   }
   
   public void desactiveJeuDonnee(JeuDonneeEntity jeuDonnee) {
      jeuDonnee.setDateLastUpdateJeuDonnees(new Date());
      jeuDonnee.setActifJeuDonnees(false);
      this.update(jeuDonnee);
   }

   public int deleteById(int idJeuDonnee) {
      int deleteEntity = 0;
      try {
         String hqlDelete = "delete JeuDonneEntity jd where jd.idJeuDonnee = :idJeudonnee";
         deleteEntity = this.em.createQuery(hqlDelete)
               .setParameter("idJeudonnee", idJeuDonnee)
               .executeUpdate();
      } catch (Exception e) {
         throw e;
      } finally {
         return deleteEntity;
      }
   }
   
}
