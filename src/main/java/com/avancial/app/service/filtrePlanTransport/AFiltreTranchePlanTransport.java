package com.avancial.app.service.filtrePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

/**
 * Filtre d'un plan de transport à partir d'un filtre de tranche : appelle le
 * {@link FiltreTranchePlanTransport} appliqué au {@link IFiltre} <
 * {@link Tranche} > donné au constructeur.
 * 
 * @author heloise.guillemaud
 *
 */
public abstract class AFiltreTranchePlanTransport implements IFiltre<PlanTransport> {

    /**
     * Filtre sur un plan de transport
     */
    private IFiltre<PlanTransport> filtrePdt;

    /**
     * 
     * @param filtreTranche
     *            Filtre sur une tranche
     */
    public AFiltreTranchePlanTransport(IFiltre<Tranche> filtreTranche) {
        this.filtrePdt = new FiltreTranchePlanTransport(filtreTranche);
    }

    @Override
    public PlanTransport filtreParCritere(PlanTransport object) {
        if (this.filtrePdt == null)
            return object;
        return this.filtrePdt.filtreParCritere(object);
    }

}
