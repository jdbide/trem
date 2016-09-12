/**
 * 
 */
package com.avancial.socle.ihm.menu.data.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.avancial.socle.ihm.menu.model.databean.RubriqueDataBean;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * @author bruno.legloahec
 *
 */
@RequestScoped
public class RubriqueService implements Serializable {
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
   public List<RubriqueDataBean> getAll() {
      @SuppressWarnings("unchecked")
      List<RubriqueDataBean> listRubrique = em.createNamedQuery(RubriqueDataBean.QUERY_GET_ALL).getResultList();
      this.em.close();
      return listRubrique;
   }

   /**
    * @param ihmRubriqueDataBean
    */
   public void addRubrique(RubriqueDataBean ihmRubriqueDataBean) throws Exception {
      try {
         this.em.getTransaction().begin();
         this.em.persist(ihmRubriqueDataBean);
         this.em.flush();
         this.em.getTransaction().commit();

      } catch (Exception e) {
         this.em.getTransaction().rollback();
         throw e;
      }

   }

   public void deleteRubrique(Long id) {
      try {
         this.em.getTransaction().begin();
         this.em.createNamedQuery(RubriqueDataBean.QUERY_DELETE_BY_ID).setParameter("id", id).executeUpdate();
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
   public void updateRubrique(RubriqueDataBean rubriqueDataBean) {
      try {
         this.em.getTransaction().begin();
         this.em.merge(rubriqueDataBean);
         this.em.flush();
         this.em.getTransaction().commit();
      } catch (Exception e) {
         this.em.getTransaction().rollback();
         throw e;
      }
   }
}
