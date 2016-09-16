package com.avancial.socle.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import com.avancial.socle.exceptions.SocleExceptionManager;
import com.avancial.socle.exceptions.impl.ASocleException;
import com.avancial.socle.scheduler.entity.JobDataBean;

/**
 * Classe DAO pour l'objet Role
 * 
 * @author bruno.legloahec
 * 
 */
public class JobDao extends AbstractDao {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   @SuppressWarnings("unchecked")
   @Override
   public List<JobDataBean> getAll() {

      String sql = "From JobDataBean";
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList();
   }

   /**
    * @param valueOf
    * @return
    */
   public JobDataBean getJobById(Long id) {
      String sql = "From JobDataBean where id=:id";
      Query requete = this.getEntityManager().createQuery(sql).setParameter("id", id);
      return (JobDataBean) requete.getSingleResult();
   }

   public JobDataBean getJobByName(String name) {
      String sql = "From JobDataBean where libelleJob =:name";
      Query requete = this.getEntityManager().createQuery(sql).setParameter("name", name);
      return (JobDataBean) requete.getSingleResult();
   }

   public void save(JobDataBean bean) throws ASocleException {
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

   public void delete(JobDataBean bean) throws ASocleException {
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

   public void update(JobDataBean bean) throws ASocleException {
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
