package com.avancial.app.service.comparePlanTransport;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;

/**
 * Comparaison entre deux objets métier d'un plan de transport.
 * 
 * @author heloise.guillemaud
 *
 */
public interface IComparePlanTransport {

    public List<IComparaisonPlanTransport> compare(IPlanTransport comparable1, IPlanTransport comparable2)
            throws Exception;
}
