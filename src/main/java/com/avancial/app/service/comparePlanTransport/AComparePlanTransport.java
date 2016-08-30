package com.avancial.app.service.comparePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.IChaineComparePlanTransport;

/**
 * Implémente la comparaison de deux objets métier plan de transport par
 * l'intermédiaire d'une chaîne de responsabilité
 * {@link IChaineComparePlanTransport}.
 * 
 * @author heloise.guillemaud
 *
 */
public abstract class AComparePlanTransport implements IComparePlanTransport {

    /**
     * Chaîne de responsabilité qui va effectuer les comparaisons
     */
    protected IChaineComparePlanTransport chaineComparePlanTransport;

    /**
     * Création de la chaîne de responsabilité (ajout des maillons dans l'ordre
     * adéquat)
     */
    protected abstract void initChaineComparePlanTransport();

    @Override
    public MapComparaisonPlanTransport compare(IPlanTransport comparable1, IPlanTransport comparable2)
            throws Exception {
        this.initChaineComparePlanTransport();
        return this.chaineComparePlanTransport.compare(comparable1, comparable2);
    }

}
