package com.avancial.app.data.objetsMetier.PlanTransport;

/**
 * Classe abstraite pour les objets métier du plan de transport qui ont un
 * régime, qui peut être sous-régime du régime tranche.
 * 
 * @author heloise.guillemaud
 *
 */
public abstract class ASousRegimeTranche implements IPlanTransport {

    /**
     * 
     * @return Régime associé à l'objet, sous-régime du régime tranche.
     */
    public abstract Regime getRegime();

    public abstract ASousRegimeTranche clone();

}
