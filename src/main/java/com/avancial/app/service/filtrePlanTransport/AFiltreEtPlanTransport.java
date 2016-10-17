package com.avancial.app.service.filtrePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;

/**
 * Filtre un plan de transport en appliquant plusieurs filtres à la suite.
 * 
 * @author heloise.guillemaud
 *
 */
public abstract class AFiltreEtPlanTransport implements IFiltre<PlanTransport> {

    /**
     * Liste des filtres à appliquer à la suite
     */
    private IFiltre<PlanTransport>[] filtres;

    /**
     * 
     * @param filtres
     *            Liste des filtres à appliquer à la suite
     */
    public AFiltreEtPlanTransport(IFiltre<PlanTransport>... filtres) {
        super();
        this.filtres = filtres;
    }

    @Override
    public PlanTransport filtreParCritere(PlanTransport object) {
        if (this.filtres == null)
            return object;
        PlanTransport planTransport = object;
        for (IFiltre<PlanTransport> iFiltre : this.filtres) {
            planTransport = iFiltre.filtreParCritere(planTransport);
        }
        return planTransport;
    }

}
