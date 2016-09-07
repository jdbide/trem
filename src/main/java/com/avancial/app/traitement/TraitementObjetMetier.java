package com.avancial.app.traitement;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.avancial.app.data.databean.Status;
import com.avancial.app.service.traiteObjetMetier.CreationObjetMetier;
import com.avancial.app.service.traiteObjetMetier.TraiteObjetMetierRegimeFactory;
import com.avancial.app.utilitaire.MapPlansDeTransport;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
import com.avancial.socle.traitement.ATraitementLogDetail;

public class TraitementObjetMetier extends ATraitementLogDetail implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	@Inject
	private TraiteObjetMetierRegimeFactory traiteObjetMetierRegimeFactory;

	private MapPlansDeTransport mapPlansDeTransport;

	private String environnementCompagnie;
	
	private static Logger                 logger                       = Logger.getLogger(TraitementObjetMetier.class);
	
   @Inject
   @Socle_PUSocle
   private EntityManager                 em;

	@Inject
	public TraitementObjetMetier() {
		super();

	}

	public void executeTraitement() throws Exception {
		this.logBean.setLibelleLogTraitement("TraitementObjetMetier");
		CreationObjetMetier creationObjetMetier = new CreationObjetMetier();
		
		try {
			/* Creation du plan de transport du Dataset actif */
			System.out.println("Creation du plan de transport ACTIF");
			logger.info("Creation du plan de transport ACTIF");
			this.log("Debut de la creation du plan de transport du JdD Actif");
			this.mapPlansDeTransport.setPlanTransportActive(creationObjetMetier.creationPlanTransport(
					this.environnementCompagnie, Status.ACTIVE, this.em, this.traiteObjetMetierRegimeFactory));
			this.log("Fin de la creation du plan de transport du JdD Actif");
			logger.info("Fin de la Creation du plan de transport ACTIF");
			/* Creation du plan de transport du Dataset draft */
			// System.out.println("Creation du plan de transport DRAFT");
			// this.mapPlansDeTransport.setPlanTransportDraft(creationObjetMetier.creationPlanTransport(this.environnementCompagnie,
			// Status.DRAFT, this.em, this.traiteObjetMetierRegimeFactory));
		} catch(Exception ex) {
			logger.error("Exception Creation du plan de transport ACTIF", ex);
			throw ex;
		} finally {
			this.em.clear();
			this.em.close();
		}
	}

	/**
	 * @return the mapPlansDeTransport
	 */
	public MapPlansDeTransport getMapPlansDeTransport() {
		return this.mapPlansDeTransport;
	}

	/**
	 * @param mapPlansDeTransport
	 *            the mapPlansDeTransport to set
	 */
	public void setMapPlansDeTransport(MapPlansDeTransport mapPlansDeTransport) {
		this.mapPlansDeTransport = mapPlansDeTransport;
	}

	/**
	 * @return the environnementCompagnie
	 */
	public String getEnvironnementCompagnie() {
		return this.environnementCompagnie;
	}

	/**
	 * @param environnementCompagnie
	 *            the environnementCompagnie to set
	 */
	public void setEnvironnementCompagnie(String environnementCompagnie) {
		this.environnementCompagnie = environnementCompagnie;
	}

   public void setEm(EntityManager em) {
      this.em = em;
   }

}
