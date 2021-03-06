package com.avancial.app.service.filtrePlanTransport;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;

/**
 * Filtre d'un {@link PlanTransport} par rapport à des numéros de {@link Train}
 * : on ne retient que les trains avec les numéros précisés.
 * 
 * @author heloise.guillemaud
 *
 */
public class FiltreNumeroTrainPlanTransport implements IFiltre<PlanTransport> {

    /**
     * Liste des numéros de trains pour le filtre
     */
    private List<String> numeroTrains;

    public FiltreNumeroTrainPlanTransport(List<String> numeroTrains) {
        super();
        this.numeroTrains = numeroTrains;
    }
    
    /**
     * constructeur vide
     */
    public FiltreNumeroTrainPlanTransport() {
        super();
        this.numeroTrains = null;
    }

    @Override
    public PlanTransport filtreParCritere(PlanTransport object) {
        if (this.numeroTrains == null)
            return new PlanTransport();
        PlanTransport planTransport = new PlanTransport();
        planTransport.setCompagnie(object.getCompagnie());
        for (Train train : object.getTrains()) {
            if (this.numeroTrains.contains(train.getNumeroTrain())) {
                planTransport.getTrains().add(train);
            }
        }
        return planTransport;
    }
    
    @Override
    public void setCritere(Object object) {
        try {
            this.numeroTrains = (List<String>) object;
        }
        catch (Exception e) {
            this.numeroTrains = null;
        }
    }

}
