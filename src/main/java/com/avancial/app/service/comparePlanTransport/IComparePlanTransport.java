package com.avancial.app.service.comparePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;

/**
 * Comparaison de deux plans de transport
 * 
 * @author heloise.guillemaud
 *
 */
public interface IComparePlanTransport {

    /**
     * Fait la comparaison entre deux plans de transport correspondant à deux
     * jeux de données différents.
     * 
     * @param ancien
     *            Plan de transport le moins récent
     * @param nouveau
     *            Plan de transport le plus récent
     */
    public void compare(PlanTransport ancien, PlanTransport nouveau);

    public void genereRapportDifferentiel();
}
