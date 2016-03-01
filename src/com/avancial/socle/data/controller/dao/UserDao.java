package com.avancial.socle.data.controller.dao;

import java.util.List;

import org.hibernate.Query;

import com.avancial.socle.data.model.databean.UserDataBean;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;

/**
 * 
 * @author caglar.erdogan
 *
 */
public class UserDao extends AbstractDao {

   @SuppressWarnings("unchecked")
   @Override
   public List<UserDataBean> getAll() {
      return this.getEntityManager().createQuery("FROM UserDataBean").getResultList();
   }

   public UserDataBean getUserByLogin(String login) {
	
      String hql = "SELECT user FROM UserDataBean user WHERE user.loginUser = :login";
      Query q = this.getSession().createQuery(hql).setParameter("login", login);
      
       UserDataBean  bean = (UserDataBean) q.uniqueResult();
      if (bean != null) return bean ; 
      return null ;
	}   
   
   public void save(UserDataBean bean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().persist(bean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         @SuppressWarnings("unused")
         SocleExceptionManager manager = new SocleExceptionManager(e);
         throw SocleExceptionManager.getException();
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
         @SuppressWarnings("unused")
         SocleExceptionManager manager = new SocleExceptionManager(e);
         throw SocleExceptionManager.getException();
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
         @SuppressWarnings("unused")
         SocleExceptionManager manager = new SocleExceptionManager(e);
         throw SocleExceptionManager.getException();
      }

   }
}