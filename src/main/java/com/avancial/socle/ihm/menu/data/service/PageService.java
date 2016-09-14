/**
 * 
 */
package com.avancial.socle.ihm.menu.data.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import com.avancial.socle.ihm.menu.model.databean.PageDataBean;
import com.avancial.socle.service.AService;

/**
 * @author bruno.legloahec
 *
 */
@RequestScoped
public class PageService extends AService implements Serializable {
   /**
   * 
   */
   private static final long serialVersionUID = 1L;

   /**
    * @return
    */
   public List<PageDataBean> getAll() {
      @SuppressWarnings("unchecked")
      List<PageDataBean> liste = this.getEntityManager().createNamedQuery(PageDataBean.QUERY_GET_ALL).getResultList();
      this.getEntityManager().close();
      return liste;
   }

   public List<PageDataBean> getAllActif() {
      @SuppressWarnings("unchecked")
      List<PageDataBean> liste = this.getEntityManager().createNamedQuery(PageDataBean.QUERY_GET_ALL_ACTIF).getResultList();
      return liste;
   }

   /**
    * @param id
    * @return
    */
   @SuppressWarnings("unchecked")
   public List<PageDataBean> getById(Long id) {
      List<PageDataBean> liste = this.getEntityManager().createNamedQuery(PageDataBean.QUERY_GET_BY_ID).setParameter("id", id).getResultList();
      return liste;
   }

   /**
    * @param pageDataBean
    */
   public void addPage(PageDataBean pageDataBean) {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().persist(pageDataBean);
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
   public void deletePage(Long id) throws Exception {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().createNamedQuery(PageDataBean.QUERY_DELETE_BY_ID).setParameter("id", id).executeUpdate();
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();

      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         throw e;
      }
   }

   /**
    * @param pageDataBean
    */
   public void updatePage(PageDataBean pageDataBean) {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().merge(pageDataBean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         throw e;
      }
   }

}
