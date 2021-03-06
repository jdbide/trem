package com.avancial.app.service.filtrePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumTrancheStatut;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;

/**
 * Filtre un plan de transport à partir d'un {@link IFiltre} sur les
 * {@link Train} : applique ce filtre sur tous les trains du plan de transport.
 * 
 * @author heloise.guillemaud
 *
 */
public class FiltreTrainPlanTransport implements IFiltre<PlanTransport> {

    private IFiltre<Train> filtreTrain;

    public FiltreTrainPlanTransport(IFiltre<Train> filtreTrain) {
        super();
        this.filtreTrain = filtreTrain;
    }
    
    /**
     * constructeur vide
     */
    public FiltreTrainPlanTransport() {
        super();
        this.filtreTrain = null;
    }

    @Override
    public PlanTransport filtreParCritere(PlanTransport object) {
        if (this.filtreTrain == null)
            return object;
        PlanTransport planTransport = new PlanTransport();
        planTransport.setCompagnie(object.getCompagnie());
        Train trainFiltre;

        /* Boucle sur les trains du plan de transport */
        for (Train train : object.getTrains()) {
            /*
             * On ajoute au résultat les trains filtrés, s'ils contiennent au
             * moins une tranche
             */
            trainFiltre = this.filtreTrain.filtreParCritere(train);
            if (!trainFiltre.getTranches().isEmpty()) {
                planTransport.getTrains().add(trainFiltre);
            }
        }
        return planTransport;
    }
    
    @Override
    public void setCritere(Object object) {
        try {
            this.filtreTrain = (IFiltre<Train>) object;
        }
        catch (Exception e) {
            this.filtreTrain = null;
        }
    }

}
