package com.avancial.app.utilitaire;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManager;

import com.avancial.app.service.IMultipleInsertRequestGenerator;
import com.avancial.app.traitement.TraitementMotrice;

/**
 * Map ayant pour clés les entités du package
 * {@link com.avancial.app.data.databean.importMotrice}, et contenant des objets
 * {@link AtomicLong} à utiliser comme id d'insertion.<br>
 * Elle est utilisée dans le {@link TraitementMotrice} pour le
 * {@link IMultipleInsertRequestGenerator}.
 * 
 * @author heloise.guillemaud
 *
 */
public class MapIdTablesMotriceRegime extends HashMap<Class<?>, AtomicLong> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	EntityManager entityManager;

	public MapIdTablesMotriceRegime(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Initisalise les id pour chaque entité, avant insetion en bdd.
	 */
	public void initMapIdTablesMotriceRegime(Class<?> entity) {

		this.put(entity, new AtomicLong(this.getNewIdRegime(entity)));
	}

	/**
	 * Calcule la valeur de l'auto incrément.
	 * 
	 * @return le prochain id à insérer
	 */
	private long getNewIdRegime(Class<?> entity) {
		Long lastId = this.entityManager
				.createNamedQuery(new StringBuilder(entity.getSimpleName()).append(".getLastId").toString(), Long.class)
				.getSingleResult();

		if (lastId == null) {
			return 1;
		} else {
			return lastId.longValue() + 1;
		}
	}

}
