package com.avancial.socle.data.controller.dao;

import java.util.List;

import com.avancial.socle.data.model.databean.User2RoleDataBean;
import com.avancial.socle.exceptions.SocleExceptionManager;
import com.avancial.socle.exceptions.impl.ASocleException;

/**
 * 
 * @author bruno.legloahec
 *
 */
public class User2RoleDao extends AbstractDao {
   @SuppressWarnings("unchecked")
   @Override
   public List<User2RoleDataBean> getAll() {
      return this.getEntityManager().createQuery("FROM User2RoleDataBean").getResultList();
   }

   public List<User2RoleDataBean> getUser2RoleByIdUser(long idUser) {
      return this.getEntityManager().createQuery("FROM User2RoleDataBean WHERE idUser = :idUser").setParameter("idUser", idUser).getResultList();
   }

   public void save(User2RoleDataBean bean) throws ASocleException {
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

   public void delete(User2RoleDataBean bean) throws ASocleException {
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

   public void update(User2RoleDataBean bean) throws ASocleException {
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