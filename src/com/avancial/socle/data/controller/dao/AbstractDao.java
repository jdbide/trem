/**
 * 
 */
package com.avancial.socle.data.controller.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import com.avancial.socle.data.model.databean.AbstractDataBean;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;

/**
 * Classe abstraite servant de base à tous les objets DAO
 * 
 * @author bruno.legloahec
 *
 */
public abstract class AbstractDao {
	private EntityManager entityManager;

	/**
	 * Constructeur
	 */
	public AbstractDao() {
		this.setEntityManager(AbstractEntityManager.getInstance().getEntityManager());
	}

	/**
	 * @return la liste contenant tous les enregistrements de l'entité concernée
	 */
	public abstract List<?> getAll();

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Session getSession() {
		// if(this.getEntityManager().unwrap(Session.class)==null)
		// System.out.println("erreur cration session");
		return this.getEntityManager().unwrap(Session.class);
	}

	/**
	 * Enregistrement d'un DataBean en base
	 * 
	 * @param dataBean
	 * @throws ASocleException
	 */
	public void save(AbstractDataBean dataBean) throws ASocleException {
		try {
			this.getEntityManager().getTransaction().begin();
			this.getEntityManager().persist(dataBean);
			this.getEntityManager().flush();
			this.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			this.getEntityManager().getTransaction().rollback();
			@SuppressWarnings("unused")
			SocleExceptionManager manager = new SocleExceptionManager(e);
			throw SocleExceptionManager.getException();
		}
	}

	public void delete(AbstractDataBean dataBean) throws ASocleException {
		try {
			this.getEntityManager().getTransaction().begin();
			this.getEntityManager().remove(dataBean);
			this.getEntityManager().flush();
			this.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			this.getEntityManager().getTransaction().rollback();
			@SuppressWarnings("unused")
			SocleExceptionManager manager = new SocleExceptionManager(e);
			throw SocleExceptionManager.getException();
		}

	}

	public void update(AbstractDataBean dataBean) throws ASocleException {
		try {
			this.getEntityManager().getTransaction().begin();
			this.getEntityManager().merge(dataBean);
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
