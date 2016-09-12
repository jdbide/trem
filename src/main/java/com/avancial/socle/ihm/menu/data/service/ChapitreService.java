/**
 * 
 */
package com.avancial.socle.ihm.menu.data.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.avancial.socle.ihm.menu.Chapitre;
import com.avancial.socle.ihm.menu.model.databean.ChapitreDataBean;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * @author bruno.legloahec
 *
 */
@RequestScoped
public class ChapitreService implements Serializable {
   /**
   * 
   */
   private static final long serialVersionUID = 1L;
   @Inject
   @Socle_PUSocle
   EntityManager             em;

   /**
    * @return
    */
   public List<ChapitreDataBean> getAll() {
      @SuppressWarnings("unchecked")
      List<ChapitreDataBean> liste = em.createNamedQuery(ChapitreDataBean.QUERY_GET_ALL).getResultList();
      this.em.close();
      return liste;
   }

   public List<ChapitreDataBean> getAllActif() {
      @SuppressWarnings("unchecked")
      List<ChapitreDataBean> liste = em.createNamedQuery(ChapitreDataBean.QUERY_GET_ALL_ACTIF).getResultList();
      return liste;
   }

   /**
    * @param id
    * @return
    */
   @SuppressWarnings("unchecked")
   public List<Chapitre> getById(Long id) {
      List<Chapitre> liste = em.createNamedQuery(ChapitreDataBean.QUERY_GET_BY_ID).setParameter("id", id).getResultList();
      return liste;
   }

   /**
    * @param chapitreDataBean
    */
   public void addChapitre(ChapitreDataBean chapitreDataBean) {
      try {
         this.em.getTransaction().begin();
         this.em.persist(chapitreDataBean);
         this.em.flush();
         this.em.getTransaction().commit();

      } catch (Exception e) {
         this.em.getTransaction().rollback();
         throw e;
      }

   }

   /**
    * @param id
    */
   public void deleteChapitre(Long id) throws Exception {
      try {
         this.em.getTransaction().begin();
         this.em.createNamedQuery(ChapitreDataBean.QUERY_DELETE_BY_ID).setParameter("id", id).executeUpdate();
         this.em.flush();
         this.em.getTransaction().commit();

      } catch (Exception e) {
         this.em.getTransaction().rollback();
         throw e;
      }
   }

   /**
    * @param chapitreDataBean
    */
   public void updateChapitre(ChapitreDataBean chapitreDataBean) {
      try {
         this.em.getTransaction().begin();
         this.em.merge(chapitreDataBean);
         this.em.flush();
         this.em.getTransaction().commit();
      } catch (Exception e) {
         this.em.getTransaction().rollback();
         throw e;
      }
   }

}
