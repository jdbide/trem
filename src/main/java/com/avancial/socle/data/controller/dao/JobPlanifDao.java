package com.avancial.socle.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import com.avancial.socle.data.model.databean.JobPlanifDataBean;
import com.avancial.socle.exceptions.SocleExceptionManager;
import com.avancial.socle.exceptions.impl.ASocleException;

/**
 * Classe DAO pour l'objet JobPlanif
 * 
 * @author bruno.legloahec
 * 
 */
public class JobPlanifDao extends AbstractDao {
   @SuppressWarnings("unchecked")
   @Override
   public List<JobPlanifDataBean> getAll() {

      String sql = "From JobPlanifDataBean";
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList();
   }

   /**
    * @return
    */
   @SuppressWarnings("unchecked")
   public List<JobPlanifDataBean> getAllActif() {
      String sql = "From JobPlanifDataBean where actifJobPlanif=1";
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList();
   }

   public JobPlanifDataBean getJobPlanifById(Long idJobPlanif) {

      String hql = "SELECT jobPlanif FROM JobPlanifDataBean jobPlanif WHERE jobPlanif.idJobPlanif = :idJobPlanif";
      org.hibernate.Query q = this.getSession().createQuery(hql).setParameter("idJobPlanif", idJobPlanif);

      JobPlanifDataBean bean = (JobPlanifDataBean) q.uniqueResult();
      return bean;
   }

   public void save(JobPlanifDataBean bean) throws ASocleException {
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

   public void delete(JobPlanifDataBean bean) throws ASocleException {
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

   public void update(JobPlanifDataBean bean) throws ASocleException {
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
