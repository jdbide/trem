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
import com.avancial.socle.service.AService;

/**
 * @author HachemBenAyed
 *
 */
@RequestScoped
public class UserService extends AService implements Serializable {
   /**
   * 
   */
   private static final long serialVersionUID = 1L;

   /**
    * @return
    */
   public List<UserDataBean> getAll() {
      @SuppressWarnings("unchecked")
      List<UserDataBean> listUser = this.getEntityManager().createNamedQuery(UserDataBean.QUERY_GET_ALL).getResultList();
      return listUser;
   }

   /**
    * @param ihmUserDataBean
    */
   public void addUser(UserDataBean userDataBean) throws Exception {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().persist(userDataBean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();

      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         throw e;
      }

   }

   public void deleteUser(Long id) {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().createNamedQuery(UserDataBean.QUERY_DELETE_BY_ID).setParameter("id", id).executeUpdate();
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
   public void updateUser(UserDataBean userDataBean) {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().merge(userDataBean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         throw e;
      }
   }
}
