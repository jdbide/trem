/**
 * 
 */
package com.avancial.socle.ihm.menu.data.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.avancial.socle.ihm.menu.model.databean.PageDataBean;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * @author bruno.legloahec
 *
 */
@RequestScoped
public class PageService implements Serializable {
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
   public List<PageDataBean> getAll() {
      @SuppressWarnings("unchecked")
      List<PageDataBean> liste = em.createNamedQuery(PageDataBean.QUERY_GET_ALL).getResultList();
      return liste;
   }

   public List<PageDataBean> getAllActif() {
      @SuppressWarnings("unchecked")
      List<PageDataBean> liste = em.createNamedQuery(PageDataBean.QUERY_GET_ALL_ACTIF).getResultList();
      return liste;
   }

   /**
    * @param id
    * @return
    */
   @SuppressWarnings("unchecked")
   public List<PageDataBean> getById(Long id) {
      List<PageDataBean> liste = em.createNamedQuery(PageDataBean.QUERY_GET_BY_ID).setParameter("id", id).getResultList();
      return liste;
   }

   /**
    * @param pageDataBean
    */
   public void addPage(PageDataBean pageDataBean) {
      try {
         this.em.getTransaction().begin();
         this.em.persist(pageDataBean);
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
   public void deletePage(Long id) throws Exception {
      try {
         this.em.getTransaction().begin();
         this.em.createNamedQuery(PageDataBean.QUERY_DELETE_BY_ID).setParameter("id", id).executeUpdate();
         this.em.flush();
         this.em.getTransaction().commit();

      } catch (Exception e) {
         this.em.getTransaction().rollback();
         throw e;
      }
   }

   /**
    * @param pageDataBean
    */
   public void updatePage(PageDataBean pageDataBean) {
      try {
         this.em.getTransaction().begin();
         this.em.merge(pageDataBean);
         this.em.flush();
         this.em.getTransaction().commit();
      } catch (Exception e) {
         this.em.getTransaction().rollback();
         throw e;
      }
   }

}
