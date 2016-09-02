/**
 * 
 */
package com.avancial.socle.ihm.menu.data.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import com.avancial.socle.data.model.databean.UserDataBean;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * @author HachemBenAyed
 *
 */
@RequestScoped
public class UserService implements Serializable {
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
   public List<UserDataBean> getAll() {
      @SuppressWarnings("unchecked")
      List<UserDataBean> listUser = em.createNamedQuery(UserDataBean.QUERY_GET_ALL).getResultList();
      return listUser;
   }

   /**
    * @param ihmUserDataBean
    */
   public void addUser(UserDataBean userDataBean) throws Exception {
      try {
         this.em.getTransaction().begin();
         this.em.persist(userDataBean);
         this.em.flush();
         this.em.getTransaction().commit();

      } catch (Exception e) {
         this.em.getTransaction().rollback();
         throw e;
      }

   }

   public void deleteUser(Long id) {
      try {
         this.em.getTransaction().begin();
         this.em.createNamedQuery(UserDataBean.QUERY_DELETE_BY_ID).setParameter("id", id).executeUpdate();
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
   public void updateUser(UserDataBean userDataBean) {
      try {
         this.em.getTransaction().begin();
         this.em.merge(userDataBean);
         this.em.flush();
         this.em.getTransaction().commit();
      } catch (Exception e) {
         this.em.getTransaction().rollback();
         throw e;
      }
   }
}
