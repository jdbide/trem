package com.avancial.socle.service;

import java.io.Serializable;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * Classe abstraite servant de base à  tous les services.
 * 
 * @author raphael.cabaret
 */
public abstract class AService implements Serializable {

	/** Automatic serial ID */
	private static final long serialVersionUID = 5280845974698170475L;

	/** entity manager. */
	@Inject
	@Socle_PUSocle
	private EntityManager     entityManager;

	/**
	 * méthode de pré-destruction.
	 */
	@PreDestroy
	public void preDestroy() {
		if(this.entityManager.isOpen()) {
			this.entityManager.close();
		}
	}

	/**
	 * retourne l'entity manager.
	 * @return l'entity manager.
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
}
