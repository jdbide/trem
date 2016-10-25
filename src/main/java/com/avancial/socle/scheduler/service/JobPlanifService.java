package com.avancial.socle.scheduler.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;

import com.avancial.socle.data.model.databean.JobPlanifDataBean;
import com.avancial.socle.exceptions.SocleExceptionManager;
import com.avancial.socle.exceptions.impl.ASocleException;
import com.avancial.socle.persistence.AutoCloseableEntityManager;
import com.avancial.socle.persistence.qualifiers.Socle_AutoCloseable;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * service de gestion des planifications de job.
 * @author raphael.cabaret
 */

public class JobPlanifService {
	
	/** entityManager. */
	@Inject
	@Socle_AutoCloseable(Socle_PUSocle.class)
	private AutoCloseableEntityManager em;

	/**
	 * retourne tous les bean.
	 * @return la liste des beans.
	 */
	@SuppressWarnings("unchecked")
	public List<JobPlanifDataBean> getAll() {
		try {
			String hql = "From JobPlanifDataBean";
			Query requete = this.em.createQuery(hql);
			return requete.getResultList();
		} finally {
			this.em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<JobPlanifDataBean> getAllActif() {
		try {
			String hql = "From JobPlanifDataBean where actifJobPlanif=1";
			Query requete = this.em.createQuery(hql);
			return requete.getResultList();
		} finally {
			this.em.close();
		}
	}
	
	public JobPlanifDataBean getJobPlanifById(Long idJobPlanif) {
	   try {
   		String hql = "SELECT jobPlanif FROM JobPlanifDataBean jobPlanif WHERE jobPlanif.idJobPlanif = :idJobPlanif";
   		Query requete = this.em.createQuery(hql).setParameter("idJobPlanif", idJobPlanif);
   
   		JobPlanifDataBean bean = (JobPlanifDataBean) requete.getSingleResult();
   		return bean;
	   } finally {
	      this.em.close();
	   }
	}
	
	/**
	 * ajoute un nouveau job.
	 * @param bean le job à ajouter.
	 * @throws ASocleException .
	 */
	public void save(JobPlanifDataBean bean) throws ASocleException {
		try {
			this.em.getTransaction().begin();
			this.em.persist(bean);
			this.em.flush();
			this.em.getTransaction().commit();
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			throw SocleExceptionManager.getException(e);
		} finally {
			this.em.close();
		}
	}

	/**
	 * supprime un job.
	 * @param bean le job à supprimer.
	 * @throws ASocleException .
	 */
	public void delete(JobPlanifDataBean bean) throws ASocleException {
		try {
			this.em.getTransaction().begin();
			this.em.remove(bean);
			this.em.flush();
			this.em.getTransaction().commit();
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			throw SocleExceptionManager.getException(e);
		} finally {
			this.em.close();
		}

	}

	/**
	 * met à jour un job.
	 * @param bean le job à mettre à jour.
	 * @throws ASocleException .
	 */
	public void update(JobPlanifDataBean bean) throws ASocleException {
		try {
			this.em.getTransaction().begin();
			this.em.merge(bean);
			this.em.flush();
			this.em.getTransaction().commit();
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			throw SocleExceptionManager.getException(e);
		} finally {
			this.em.close();
		}

	}
}
