/**
 * 
 */
package com.avancial.socle.ihm.menu.data.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import com.avancial.socle.ihm.menu.model.databean.RubriqueDataBean;
import com.avancial.socle.service.AService;

/**
 * @author bruno.legloahec
 *
 */
@RequestScoped
public class RubriqueService extends AService implements Serializable {
   /**
   * 
   */
   private static final long serialVersionUID = 1L;

   /**
    * @return
    */
   public List<RubriqueDataBean> getAll() {
      @SuppressWarnings("unchecked")
      List<RubriqueDataBean> listRubrique = this.getEntityManager().createNamedQuery(RubriqueDataBean.QUERY_GET_ALL).getResultList();
      return listRubrique;
   }

   /**
    * @param ihmRubriqueDataBean
    */
   public void addRubrique(RubriqueDataBean ihmRubriqueDataBean) throws Exception {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().persist(ihmRubriqueDataBean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();

      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         throw e;
      }

   }

   public void deleteRubrique(Long id) {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().createNamedQuery(RubriqueDataBean.QUERY_DELETE_BY_ID).setParameter("id", id).executeUpdate();
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
   public void updateRubrique(RubriqueDataBean rubriqueDataBean) {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().merge(rubriqueDataBean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         throw e;
      }
   }
}
