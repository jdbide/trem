package com.avancial.app.service.comparePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;

/**
 * Comparaison entre deux objets métier d'un plan de transport.
 * 
 * @author heloise.guillemaud
 *
 */
public interface IComparePlanTransport {

    public MapComparaisonPlanTransport compare(IPlanTransport comparable1, IPlanTransport comparable2)
            throws Exception;
}
