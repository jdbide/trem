package com.avancial.app.service.filtrePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;

/**
 * Filtre d'un plan de transport à partir d'un filtre de train : appelle le
 * {@link FiltreTrainPlanTransport} appliqué au {@link IFiltre} < {@link Train}
 * > donné au constructeur.
 * 
 * @author heloise.guillemaud
 *
 */
public abstract class AFiltreTrainPlanTransport implements IFiltre<PlanTransport> {

    /**
     * Filtre sur un plan de transport
     */
    private IFiltre<PlanTransport> filtrePdt;

    /**
     * 
     * @param filtreTrain
     *            Filtre sur un train
     */
    public AFiltreTrainPlanTransport(IFiltre<Train> filtreTrain) {
        this.filtrePdt = new FiltreTrainPlanTransport(filtreTrain);
    }

    @Override
    public PlanTransport filtreParCritere(PlanTransport object) {
        if (this.filtrePdt == null)
            return object;
        return this.filtrePdt.filtreParCritere(object);
    }

}
