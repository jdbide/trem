package com.avancial.app.service;

import java.util.List;

import javax.persistence.Query;

import com.avancial.app.data.databean.RefCodeRmEntity;
import com.avancial.socle.service.AService;

/**
 * service permettant la gestion de la table de correspondance entre les codes RM et les codes rame (RefCodeRm).
 * @author raphael.cabaret
 */
public class RefCodeRmService extends AService {

	/** SERIAL ID */
	private static final long serialVersionUID = 6423279792458307710L;
	
	/**
     * Retourne le contenu de la table RefCodeRm
     * 
     * @return List<{@link RefCodeRmEntity}>
     */
    @SuppressWarnings("unchecked")
	public List<RefCodeRmEntity> getAllCodeRm() {
        Query query = this.getEntityManager().createNamedQuery("RefCodeRmEntity.getAll", RefCodeRmEntity.class);
        return ((List<RefCodeRmEntity>) query.getResultList());
    }

}
