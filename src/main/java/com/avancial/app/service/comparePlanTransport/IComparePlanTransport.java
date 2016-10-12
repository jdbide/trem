package com.avancial.app.service.comparePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;

/**
 * Comparaison entre deux objets métier d'un plan de transport.
 * 
 * @author heloise.guillemaud
 *
 */
public interface IComparePlanTransport {

	/**
	 * Compare deux objets métiers d'un plan de transport.
	 * 
	 * @param comparable1
	 * @param comparable2
	 * @return
	 * @throws Exception
	 */
	public MapComparaisonPlanTransport compare(IPlanTransport comparable1, IPlanTransport comparable2) throws Exception;
}
