package com.avancial.socle.data.controller.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.avancial.socle.data.model.databean.UserDataBean;
import com.avancial.socle.exceptions.SocleExceptionManager;
import com.avancial.socle.exceptions.impl.ASocleException;

/**
 * 
 * @author caglar.erdogan
 *
 */
@RequestScoped
public class UserDao extends AbstractDao {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   @SuppressWarnings("unchecked")
   @Override
   public List<UserDataBean> getAll() {

      String sql = "From UserDataBean";
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList();
   }

   public UserDataBean getUserByLogin(String login) {
      String sql = "SELECT user FROM UserDataBean user WHERE user.loginUser = :login";
      Query requete = this.getEntityManager().createQuery(sql).setParameter("login", login);
      return (UserDataBean) requete.getSingleResult();

   }

   public UserDataBean getUserById(Long idUser) {
       Query query = this.getEntityManager().createNamedQuery(UserDataBean.QUERY_GET_BY_ID, UserDataBean.class);
       query.setParameter("id", idUser);
       UserDataBean res = null;

       try {
          res = (UserDataBean) query.getSingleResult();
       }
       catch (NoResultException e) {
       } catch (Exception ex) {
          ex.printStackTrace();
          throw ex;
      } finally {
         return res;
      }
   }

   public void save(UserDataBean bean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().persist(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         throw SocleExceptionManager.getException(e);
      }
   }

   public void delete(UserDataBean bean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().remove(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         throw SocleExceptionManager.getException(e);
      }

   }

   public void update(UserDataBean bean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().merge(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         throw SocleExceptionManager.getException(e);
      }

   }
}