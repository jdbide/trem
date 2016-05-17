package com.avancial.socle.data.controller.dao;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

import com.avancial.socle.data.model.databean.Item2RoleDataBean;
import com.avancial.socle.data.model.databean.RoleDataBean;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;

/**
 * 
 * @author bruno.legloahec
 *
 */
@RequestScoped
public class Item2RoleDao extends AbstractDao {
   @SuppressWarnings("unchecked")
   @Override
   public List<Item2RoleDataBean> getAll() {
      this.getEntityManager().clear();
      Query query = this.getEntityManager().createQuery("FROM Item2RoleDataBean");
      return query.getResultList();
   }

   /**
    * @param roles
    * @return
    */
   public List<Item2RoleDataBean> getitemByListId(List<RoleDataBean> roles) throws Exception {
      this.getEntityManager().clear();
      Query query = this.getEntityManager().createQuery("FROM Item2RoleDataBean where idRole in (:roles) ");
      query.setParameter("roles", roles);
      return query.getResultList();
   }
   public void save(Item2RoleDataBean bean) throws ASocleException {
       try {
          this.getEntityManager().getTransaction().begin();
          this.getEntityManager().persist(bean);
          this.getEntityManager().flush();
          this.getEntityManager().getTransaction().commit();
       } catch (Exception e) {
          this.getEntityManager().getTransaction().rollback();
         @SuppressWarnings("unused")
         SocleExceptionManager manager=new SocleExceptionManager(e);
         throw SocleExceptionManager.getException();
       }
    }
   public void delete(Item2RoleDataBean bean) throws ASocleException {
       try {
          this.getEntityManager().getTransaction().begin();
          this.getEntityManager().remove(bean);
          this.getEntityManager().flush();
          this.getEntityManager().getTransaction().commit();
       } catch (Exception e) {
          this.getEntityManager().getTransaction().rollback();
          @SuppressWarnings("unused")
         SocleExceptionManager manager=new SocleExceptionManager(e);
          throw SocleExceptionManager.getException();
       }

    }
}