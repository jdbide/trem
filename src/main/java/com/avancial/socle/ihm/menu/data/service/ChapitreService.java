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
import com.avancial.socle.service.AService;

/**
 * @author bruno.legloahec
 *
 */
@RequestScoped
public class ChapitreService extends AService implements Serializable {
   /**
   * 
   */
   private static final long serialVersionUID = 1L;

   /**
    * @return
    */
   public List<ChapitreDataBean> getAll() {
      @SuppressWarnings("unchecked")
      List<ChapitreDataBean> liste = this.getEntityManager().createNamedQuery(ChapitreDataBean.QUERY_GET_ALL).getResultList();
      this.getEntityManager().close();
      return liste;
   }

   public List<ChapitreDataBean> getAllActif() {
      @SuppressWarnings("unchecked")
      List<ChapitreDataBean> liste = this.getEntityManager().createNamedQuery(ChapitreDataBean.QUERY_GET_ALL_ACTIF).getResultList();
      return liste;
   }

   /**
    * @param id
    * @return
    */
   @SuppressWarnings("unchecked")
   public List<Chapitre> getById(Long id) {
      List<Chapitre> liste = this.getEntityManager().createNamedQuery(ChapitreDataBean.QUERY_GET_BY_ID).setParameter("id", id).getResultList();
      return liste;
   }

   /**
    * @param chapitreDataBean
    */
   public void addChapitre(ChapitreDataBean chapitreDataBean) {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().persist(chapitreDataBean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();

      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         throw e;
      }

   }

   /**
    * @param id
    */
   public void deleteChapitre(Long id) throws Exception {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().createNamedQuery(ChapitreDataBean.QUERY_DELETE_BY_ID).setParameter("id", id).executeUpdate();
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();

      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         throw e;
      }
   }

   /**
    * @param chapitreDataBean
    */
   public void updateChapitre(ChapitreDataBean chapitreDataBean) {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().merge(chapitreDataBean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         throw e;
      }
   }

}
